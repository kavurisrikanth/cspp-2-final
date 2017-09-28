import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int n = 0, k = 0;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        int arr[] = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        sc.close();

        if(k > n)
            k = k % n;

        if(k > 0)
            arr = rotate_k(arr, n, k);

        print_array(arr, n);
    }

    public static int[] rotate_k(int[] arr, int n, int k) {
        int x = 0;
        while((x + 1) * k - 1 < n) {
            // printf("x = %d\n", x);
            // print_array(arr, n);
            arr = reverse_upto(arr, x * k, (x + 1) * k - 1);
            x++;
        }

        return arr;
    }

    public static int[] reverse_upto(int[] arr, int from, int upto) {
        int i = 0, temp = 0, j = 0;
        for(i = from; i <= (from + upto)/2; i++) {
            j = (from + upto) -i;
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }

        return arr;
    }

    public static void print_array(int[] arr, int len) {
        System.out.print("[");
        for(int i = 0; i < len; i++) {
            System.out.print(arr[i]);
            if(i != len - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }
}
