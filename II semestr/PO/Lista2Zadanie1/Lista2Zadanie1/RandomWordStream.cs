using System;
using System.Collections.Generic;
using System.Text;


namespace Lista2Zadanie1
{
    class RandomWordStream 
    {
        PrimeStream ps = new PrimeStream();
        int length;
        public string next()
        {
            length = ps.next();
            var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            var stringChars = new char[length];
            var random = new RandomStream();

            for (int i = 0; i < stringChars.Length; i++)
            {
                stringChars[i] = chars[random.next()%(chars.Length)];
            }

            var finalString = new String(stringChars);
            return finalString;
        }
    }
}
