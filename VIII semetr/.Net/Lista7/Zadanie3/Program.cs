using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Collections;
using System.IO;
using System.Security.Cryptography;
using System.IO.Compression;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        { 
            string original = "";

            string pathSource = @"text.txt";
            string pathNew = @"newfile.gz";

            original = System.Text.Encoding.UTF8.GetString(ReadFromFile(pathSource));

            byte[] encrypted;
            using (Aes myAes = Aes.Create())
            {
                encrypted = EncryptStringToBytes_Aes(original, myAes.Key, myAes.IV);

                using (MemoryStream stream = new MemoryStream(encrypted))
                {
                    using (FileStream fsNew = new FileStream(pathNew,
                        FileMode.Create, FileAccess.ReadWrite))
                    {
                        using (GZipStream compressionStream = new GZipStream(fsNew,
                                CompressionMode.Compress))
                        {
                            stream.CopyTo(compressionStream);
                        }

                    }

                }
                    byte[] fromFile;

                    using (FileStream originalFileStream = new FileStream(pathNew, FileMode.Open, FileAccess.Read))
                    {
                        using (MemoryStream resultStream = new MemoryStream())
                        {
                            using (GZipStream decompressionStream = new GZipStream(originalFileStream, CompressionMode.Decompress))
                            {
                                decompressionStream.CopyTo(resultStream);
                                fromFile = resultStream.ToArray();
                            }

                        }
                    }

                    string roundtrip = DecryptStringFromBytes_Aes(fromFile, myAes.Key, myAes.IV);

                    Console.WriteLine("Original:   {0}", original);
                    Console.WriteLine("Round Trip: {0}", roundtrip);
                }
            Console.ReadLine();
        }

        static byte[] ReadFromFile(String pathSource)
        {
            using (FileStream fsSource = new FileStream(pathSource,
                FileMode.Open, FileAccess.Read))
            {
                byte[] bytes = new byte[fsSource.Length];
                int numBytesToRead = (int)fsSource.Length;
                int numBytesRead = 0;
                while (numBytesToRead > 0)
                {
                    int n = fsSource.Read(bytes, numBytesRead, numBytesToRead);

                    if (n == 0)
                        break;

                    numBytesRead += n;
                    numBytesToRead -= n;
                }
                return bytes;
            }
        }

        static byte[] EncryptStringToBytes_Aes(string plainText, byte[] Key, byte[] IV)
        {
        if (plainText == null || plainText.Length <= 0)
            throw new ArgumentNullException("plainText");
        if (Key == null || Key.Length <= 0)
            throw new ArgumentNullException("Key");
        if (IV == null || IV.Length <= 0)
            throw new ArgumentNullException("IV");
        byte[] encrypted;

        using (Aes aesAlg = Aes.Create())
        {
            aesAlg.Key = Key;
            aesAlg.IV = IV;

            ICryptoTransform encryptor = aesAlg.CreateEncryptor(aesAlg.Key, aesAlg.IV);

            using (MemoryStream msEncrypt = new MemoryStream())
            {
                using (CryptoStream csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write))
                {
                    using (StreamWriter swEncrypt = new StreamWriter(csEncrypt))
                    {
                        swEncrypt.Write(plainText);
                    }
                    encrypted = msEncrypt.ToArray();
                }
            }
        }

        return encrypted;
        }

    static string DecryptStringFromBytes_Aes(byte[] cipherText, byte[] Key, byte[] IV)
    {
        if (cipherText == null || cipherText.Length <= 0)
            throw new ArgumentNullException("cipherText");
        if (Key == null || Key.Length <= 0)
            throw new ArgumentNullException("Key");
        if (IV == null || IV.Length <= 0)
            throw new ArgumentNullException("IV");

        string plaintext = null;

        using (Aes aesAlg = Aes.Create())
        {
            aesAlg.Key = Key;
            aesAlg.IV = IV;

            ICryptoTransform decryptor = aesAlg.CreateDecryptor(aesAlg.Key, aesAlg.IV);

            using (MemoryStream msDecrypt = new MemoryStream(cipherText))
            {
                using (CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read))
                {
                    using (StreamReader srDecrypt = new StreamReader(csDecrypt))
                    {
                         plaintext = srDecrypt.ReadToEnd();
                    }
              }
            }
        }

        return plaintext;
        }
    }

}
