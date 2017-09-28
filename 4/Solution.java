/*
    Finding the shortest palindromes in a string.
*/

import java.util.Scanner;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();

        ArrayList<String> ans = shortest_pal(str);
        int len = ans.size();

        if(len > 0) {
            System.out.print("[");
            for(int i = 0; i < len; i++) {
                System.out.print(ans.get(i));
                if(i != len - 1)
                    System.out.print(", ");
            }
            System.out.println("]");
        } else {
            System.out.println("[]");
        }
    }

    public static ArrayList<String> shortest_pal(String str) {
        /*
            Finds the shortest palindromes in str.
            To do this, we start by making k-grams, and checking if they are
            palindromes. Since the smallest possible k is 2, we go from 2 to
            string length - 1.
            If we find something at length 2, then that's the answer. Else, we
            keep going.
        */

        ArrayList<String> ans = new ArrayList<String>();
        for(int k = 2; k < str.length(); k++) {
            for(int i = 0; i <= str.length() - k; i++) {
                String temp = str.substring(i, i + k);
                if(isPal(temp))
                    ans.add(temp);
            }

            if(ans.size() > 0) {
                Collections.sort(ans);
                return ans;
            }
        }

        return ans;
    }

    public static boolean isPal(String str) {
        // Checks if a string is a palindrome.
        int len = str.length(), j = 0;
        // System.out.println("string: " + str);
        for(int i = 0; i <= len/2; i++) {
            j = len - 1 - i;
            // System.out.println("i = " + i + ", j = " + j);
            if(str.charAt(i) != str.charAt(j))
                return false;
        }

        return true;
    }
}
