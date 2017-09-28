/*
    Float all zeros to the left of an array
*/

#include <stdio.h>
#include <stdlib.h>

void float_zeros(int *arr, int len);
void print_array(int *arr, int len);

int main(void) {
    int n = 0, *arr = NULL, i = 0;
    scanf("%d", &n);

    if(n <= 0) {
        fprintf(stderr, "The array size can't be zero or negative\n");
        return 1;
    }

    arr = (int*)calloc(1, n * sizeof(int));
    for(i = 0; i < n; i++)
        scanf("%d", &arr[i]);

    float_zeros(arr, n);
    print_array(arr, n);

    free(arr);
    return 0;
}

void float_zeros(int *arr, int len) {
    /*
        Find all the 0s in arr, and float them to the left end of arr.
    */
    int i = 0, prev = 0, j = 0, k = 0, temp = 0;
    while(i < len) {
        // Find zeros
        if(*(arr + i) == 0) {
            // Found a zero. Now float it.
            // To do that, we need where to float it to.
            // That would be prev.
            for(j = i; j > prev; j--) {
                k = j - 1;
                temp = *(arr + j);
                *(arr + j) = *(arr + k);
                *(arr + k) = temp;
            }
            prev++;
        }
        i++;
    }
}

void print_array(int *arr, int len) {
    /*
        Prints an int array of length len
    */

    int i = 0;
    while(i < len) {
        printf("%d", *(arr + i));
        if(i != len - 1)
            printf(" ");
        i++;
    }
    printf("\n");
}
