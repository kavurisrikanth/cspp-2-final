/*
    Check if a given number is happy,
*/

import java.util.*;

public class Solution {

    public static int depth;

    public static void main(String[] args) {
        int test = 0;
        Scanner sc = new Scanner(System.in);
        test = sc.nextInt();

        int[] nums = new int[test];
        for(int i = 0; i < test; i++)
            nums[i] = sc.nextInt();

        for(int i = 0; i < test; i++) {
            depth = 0;
            if(nums[i] != 0 && is_happy(nums[i]))
                System.out.println("Happy Number");
            else
                System.out.println("Not a Happy Number");
        }

        sc.close();
    }

    public static boolean is_happy(int num) {
        if(depth == 100)
            return false;

        int new_num = 0, digit = 0;
        while(num > 0) {
            digit = num % 10;
            new_num += (digit * digit);
            num /= 10;
        }

        if(new_num == 1)
            return true;

        depth++;
        return is_happy(new_num);
    }
}
