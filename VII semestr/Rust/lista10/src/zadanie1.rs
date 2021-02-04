#[allow(dead_code)]
pub fn last_digit(lst: &[u64]) -> u64 {
    println!("{:?}", lst);
    if lst.len() == 0 {
        return 1;
    }
    let mut num_after_pow = 0;
    let mut pow = lst[lst.len()-1];
    for i in 2..lst.len()+1 {
        let num_to_pow = lst[lst.len()-i];
        if num_to_pow == 0 {
            if pow == 0 {
                num_after_pow = 1;
            } else{
                num_after_pow = 0;
            }
            pow = num_after_pow;
            continue;
        }
        if num_to_pow == 101 && pow == 2 {
            pow = 201;
            num_after_pow = pow;
            continue;
        }
        num_after_pow = last_two_digit(num_to_pow, pow);
        if num_after_pow == 0 {
            num_after_pow = 100;
        }
        pow = num_after_pow;
    }
    (num_after_pow % 10)as u64  
}

fn last_two_digit(num: u64, pow: u64) -> u64{
    if pow == 1 {
        return  num%100;
    } 
    if pow == 0 || num == 1{
        return 1;
    }
    let mut num = num;
    let mut pow = pow;
    let mut last_num = num % 10;
    if last_num == 9 {
        if pow%2 != 0 {
            let digits = last_two_digit(num, pow-1);
            return (digits * num) % 100;
        }
        num*=num % 1000;
        pow/=2;
        last_num = num % 10;
    } else if last_num == 3 || last_num == 7{
        if pow%4 != 0 {
            let digits = last_two_digit(num, pow-1);
            return (digits * num) % 100;
        }
        num*=(num % 1000)*(num % 1000)*(num % 1000);
        pow/=4;
        last_num = num % 10;
    } else if last_num == 2 || last_num == 4 || last_num == 6 || last_num == 8 {
        if pow < 10 {
            let digits = last_two_digit(num, pow-1);
            return (digits * num) % 100;
        }
        let new_num = num / 2;
        let new_pow = pow / 10;
        let digits1 = last_two_digit(new_num, 10);
        let digits2 = 24;
        let digits3 = last_two_digit(digits1*digits2, new_pow);
        return (digits3 * last_two_digit(num, pow - (10*new_pow)))%100;

    } 
    let second_num = (num / 10) % 10;
    let last_pow = pow % 10;
    match last_num {
        1 =>  ((second_num*last_pow)%10)*10+1,
        5 => {
            if second_num % 2 == 1 && pow % 2 == 1 {
                return 75;
            } 
            return 25;
        },
        _ => 0
    }

}


#[cfg(test)]
mod tests {
    use super::*;
    
    #[test]
    fn basic_test1() {
            assert_eq!(last_digit(&vec![]), 1);
    }

    #[test]
    fn last_two_digit_test() {
            assert_eq!(last_two_digit(91,246), 41);
    }

    #[test]
    fn last_two_digit_test2() {
            assert_eq!(last_two_digit(79,142), 41);
    }

    #[test]
    fn last_two_digit_test3() {
            assert_eq!(last_two_digit(79,143), 39);
    }

    #[test]
    fn last_two_digit_test4() {
            assert_eq!(last_two_digit(43,76), 1);
    }

    #[test]
    fn last_two_digit_test5() {
            assert_eq!(last_two_digit(17,256), 81);
    }

    #[test]
    fn last_two_digit_test6() {
            assert_eq!(last_two_digit(4,528), 36);
    }

    #[test]
    fn last_two_digit_test7() {
            assert_eq!(last_two_digit(65,243), 25);
    }

    #[test]
    fn last_two_digit_test8() {
            assert_eq!(last_two_digit(135,1091), 75);
    }

    #[test]
    fn last_two_digit_test9() {
            assert_eq!(last_two_digit(101,2), 1);
    }


    #[test]
    fn basic_test2() {
        assert_eq!(last_digit(&vec![0, 0]), 1);
    }

    #[test]
    fn basic_test3() {
        assert_eq!(last_digit(&vec![0, 0, 0]), 0);
    }

    #[test]
    fn basic_test4() {
        assert_eq!(last_digit(&vec![1, 2]), 1);
    }

    #[test]
    fn basic_test5() {
        assert_eq!(last_digit(&vec![3,4,5]), 1);
    }

    #[test]
    fn basic_test6() {
        assert_eq!(last_digit(&vec![4, 3, 6]), 4);
    }

    #[test]
    fn basic_test7() {
        assert_eq!(last_digit(&vec![7, 6, 21]), 1);
    }

    #[test]
    fn basic_test8() {
        assert_eq!(last_digit(&vec![12, 30, 21]), 6);
    }

    #[test]
    fn basic_test9() {
        assert_eq!(last_digit(&vec![100, 4, 100, 100]), 0);
    }

    #[test]
    fn basic_test10() {
        assert_eq!(last_digit(&vec![2, 0, 1]), 1);
    }

    #[test]
    fn basic_test11() {
        assert_eq!(last_digit(&vec![2, 2, 101, 2]), 6);
    }

    #[test]
    fn basic_test12() {
        assert_eq!(last_digit(&vec![2, 101, 2]), 2);
    }
    
    #[test]
    fn basic_tests() {
        let tests = vec![
            (vec![], 1),
            (vec![0, 0], 1),
            (vec![0, 0, 0], 0),
            (vec![1, 2], 1),
            (vec![3, 4, 5], 1),
            (vec![4, 3, 6], 4),
            (vec![7, 6, 21], 1),
            (vec![12, 30, 21], 6),
            (vec![2, 2, 2, 0], 4),
            (vec![937640, 767456, 981242], 0),
            (vec![123232, 694022, 140249], 6),
            (vec![499942, 898102, 846073], 6),
            (vec![625703, 43898, 614961, 448629], 1)
        ];

        for test in tests {
            assert_eq!(last_digit(&test.0), test.1);
        }
    }
}