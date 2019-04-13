// Marko Golovko
// Pracownia PO, piątek, s. 137
// L 2, z 1, IntStream 
// RandomWordStream
// ciąg napisów o dlugościach liczb pierwszuch  
// 2018-03-16


using System;
using System.Collections.Generic;
using System.Text;


namespace Lista2Zadanie1
{
    class RandomWordStream 
    {
        PrimeStream ps = new PrimeStream();
        int length;

        //zwraca kolejny element ciagu
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
