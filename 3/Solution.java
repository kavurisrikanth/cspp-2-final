/*
    Float zeros
*/

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        int n = 0;

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        if(n <= 0)
            System.err.println("The array size can't be zero or negative");

        int arr[] = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        sc.close();

        // Changing the array elements here.
        int i = 0, prev = 0;
        while(i < n) {
            // Find a zero
            if(arr[i] == 0) {
                // Found zero.
                // Float it
                for(int j = i; j > prev; j--) {
                    int k = j - 1, temp = arr[k];
                    arr[k] = arr[j];
                    arr[j] = temp;
                }
                prev++;
            }
            i++;
        }

        for(i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if(i != n - 1)
                System.out.print(" ");
        }
        System.out.println("");
    }
}
