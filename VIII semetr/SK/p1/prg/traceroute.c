// Marko Golovko 297354

#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <netinet/ip_icmp.h>
#include <time.h>
#include <fcntl.h>
#include <signal.h>
#include <time.h>
#include <assert.h>

u_int16_t compute_icmp_checksum(const void *buff, int length)
{
    u_int32_t sum;
    const u_int16_t *ptr = buff;
    assert(length % 2 == 0);
    for (sum = 0; length > 0; length -= 2)
        sum += *ptr++;
    sum = (sum >> 16) + (sum & 0xffff);
    return (u_int16_t)(~(sum + (sum >> 16)));
}

int traceroute(int sockfd, struct sockaddr_in *send_addr, char *send_ip, int ttl_val)
{
    int msg_count = 0, msg_received_count = 0;
    socklen_t addr_len;
    struct icmp pckt;
    struct timeval tv_out;
    struct sockaddr_in r_addr;
    long double t_msec = 0;
    tv_out.tv_sec = 1;
    tv_out.tv_usec = 0;
    struct timespec time_start, time_end;
    char ip_adrr_from[3][100] = {"", "", ""};
    int total_msec = 0;
    u_int8_t buffer[IP_MAXPACKET];
    pid_t pid = getpid();

    if (setsockopt(sockfd, IPPROTO_IP, IP_TTL, &ttl_val, sizeof(int)) != 0)
    {
        printf("\nSetting socket options to TTL failed!\n");
        return 0;
    }

    for (int packages_amount = 0; packages_amount < 3; packages_amount++)
    {
        bzero(&pckt, sizeof(pckt));

        pckt.icmp_type = ICMP_ECHO;
        pckt.icmp_id = pid;
        pckt.icmp_seq = ttl_val;
        pckt.icmp_code = 0;
        pckt.icmp_cksum = compute_icmp_checksum(&pckt, sizeof(pckt));

        clock_gettime(CLOCK_MONOTONIC, &time_start);
        if (sendto(sockfd, &pckt, sizeof(pckt), 0, (struct sockaddr *)send_addr, sizeof(*send_addr)) <= 0)
        {
            printf("\nPacket Sending Failed!\n");
        }
    }

    while (msg_received_count < 3)
    {
        addr_len = sizeof(r_addr);

        fd_set descriptors;
        FD_ZERO(&descriptors);
        FD_SET(sockfd, &descriptors);
        int ready = select(sockfd + 1, &descriptors, NULL, NULL, &tv_out);
        if (ready == -1)
        {
            printf("\nselect() Failed!\n");
        }
        if (FD_ISSET(sockfd, &descriptors))
        {
            if (recvfrom(sockfd, buffer, IP_MAXPACKET, 0, (struct sockaddr *)&r_addr, &addr_len) <= 0 && msg_count > 1)
            {
                printf("\nPacket receive failed!\n");
            }
            else
            {
                struct ip *buff_ip = (struct ip *)buffer;
                struct icmp *buff_icmp = (struct icmp *)((u_int8_t *)buff_ip + (*buff_ip).ip_hl * 4);
                if (buff_icmp->icmp_type == ICMP_ECHOREPLY || buff_icmp->icmp_type == ICMP_TIME_EXCEEDED)
                {
                    clock_gettime(CLOCK_MONOTONIC, &time_end);
                    double nanoSec = ((double)(time_end.tv_nsec - time_start.tv_nsec)) / 1000000.0;
                    t_msec = (time_end.tv_sec - time_start.tv_sec) * 1000.0 + nanoSec;
                    char sender_ip_str[20];
                    inet_ntop(AF_INET, &(r_addr.sin_addr), sender_ip_str, sizeof(sender_ip_str));
                    int duplicate = 0;
                    for (int i = 0; i < msg_received_count; i++)
                    {
                        if (!strcmp(ip_adrr_from[i], sender_ip_str))
                        {
                            duplicate = 1;
                        }
                    }
                    if (!duplicate)
                    {
                        strcpy(ip_adrr_from[msg_received_count], sender_ip_str);
                    }
                    total_msec += (int)t_msec;
                    msg_received_count++;
                }
            }
        }
        else
        {
            break;
        }
    }
    printf("%d. ", ttl_val);
    int done = 0;
    for (int i = 0; i < 3; i++)
    {
        if (strcmp(ip_adrr_from[i], ""))
        {
            printf("%s ", ip_adrr_from[i]);
            if (!strcmp(ip_adrr_from[i], send_ip))
            {
                done = 1;
            }
        }
    }
    if (msg_received_count == 3)
    {
        printf("%dms", total_msec / 3);
    }
    else if (msg_received_count > 0)
    {
        printf("???");
    }
    else
    {
        printf("*");
    }
    printf("\n");
    return done;
}

int main(int argc, char *argv[])
{
    struct sockaddr_in addr_con;
    char *ip_addr = argv[1];
    int sockfd = socket(AF_INET, SOCK_RAW, IPPROTO_ICMP);

    if (argc != 2)
    {
        printf("\nFormat %s <ip address>\n", argv[0]);
        return 0;
    }

    addr_con.sin_family = AF_INET;
    addr_con.sin_port = htons(0);

    if (inet_aton(ip_addr, &addr_con.sin_addr) == 0)
    {
        fprintf(stderr, "Invalid address\n");
        exit(EXIT_FAILURE);
    }
    if (sockfd < 0)
    {
        printf("\nSocket file descriptor not received!!\n");
        return 0;
    }
    for (int ttl = 1; ttl <= 30; ttl++)
    {
        int is_done = traceroute(sockfd, &addr_con, ip_addr, ttl);
        if (is_done)
        {
            return 0;
        }
    }
}
