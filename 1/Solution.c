/*
    Rotate k elements of an array.
*/

#include <stdio.h>
#include <stdlib.h>

void rotate_k(int *arr, int n, int k);
void reverse_upto(int *arr, int from, int upto);
void print_array(int *arr, int len);

int main(void) {
    int n = 0, k = 0, *arr = NULL, i = 0;
    scanf("%d %d", &n, &k);

    arr = (int*)calloc(1, n * sizeof(int));
    for(i = 0; i < n; i++)
        scanf("%d", &arr[i]);

    // Make sure k < n
    if(k > n)
        k = k % n;

    if(k > 0)
        rotate_k(arr, n, k);

    print_array(arr, n);

    free(arr);
    return 0;
}

void rotate_k(int *arr, int n, int k) {
    int x = 0;
    while((x + 1) * k - 1 < n) {
        // printf("x = %d\n", x);
        // print_array(arr, n);
        reverse_upto(arr, x * k, (x + 1) * k - 1);
        x++;
    }
}

void reverse_upto(int *arr, int from, int upto) {
    /*
        Reverse elements of arr starting with from upto and including upto
    */

    int i = 0, temp = 0, j = 0;
    for(i = from; i <= (from + upto)/2; i++) {
        j = (from + upto) -i;
        temp = *(arr + j);
        *(arr + j) = *(arr + i);
        *(arr + i) = temp;
    }
}

void print_array(int *arr, int len) {
    /*
        Prints an int array of length len
    */

    int i = 0;
    printf("[");
    while(i < len) {
        printf("%d", *(arr + i));
        if(i != len - 1)
            printf(", ");
        i++;
    }
    printf("]\n");
}
