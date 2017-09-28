/*
    Check if a given number is Happy or not.
*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>

bool is_happy(int num, int *depth);

int main(void) {
    int test = 0;
    scanf("%d", &test);

    int *nums = (int*)calloc(1, test * sizeof(int)), i = 0, depth = 0;
    for(i = 0; i < test; i++)
        scanf("%d", &nums[i]);

    for(i = 0; i < test; i++) {
        depth = 0;
        if(*(nums + i) != 0 && is_happy(*(nums + i), &depth))
            printf("Happy Number\n");
        else
            printf("Not a Happy Number\n");
    }

    free(nums);
    return 0;
}

bool is_happy(int num, int *depth) {
    if(*depth == 100)
        return false;

    int new_num = 0;
    while(num > 0) {
        new_num += (int)pow(num % 10, 2);
        num /= 10;
    }

    if(new_num == 1)
        return true;

    *depth += 1;
    return is_happy(new_num, depth);
}
