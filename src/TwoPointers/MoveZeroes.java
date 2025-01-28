package TwoPointers;

import java.util.Arrays;
import java.util.List;

public class MoveZeroes {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        //moveZeroes(nums);
        moveZeroesO1(nums);

    }

    // O(1)
    public static void moveZeroesO1(int[] nums) {
         /*
        Input: nums = [0,1,0,3,12]
        Output: [1,3,12,0,0]
         */
        int nonzero = 0; // Pointer to place the next non-zero element

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // Swap non-zero element with the element at `nonzero`
                int temp = nums[i];
                nums[i] = nums[nonzero];
                nums[nonzero] = temp;
                nonzero++; // Increment the nonzero pointer
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    // O(N*2)
    public static void moveZeroes(int[] nums) {
        /*
        Input: nums = [0,1,0,3,12]
        Output: [1,3,12,0,0]
         */
        if (nums.length == 1 && nums[0] == 0) {
            System.out.println("[" + nums[0] + "]");
            return;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1; j++) {
                int temp = nums[j];
                int temp2 = nums[j + 1];
                if (temp == 0 && temp2 != 0) {
                    nums[j] = temp2;
                    nums[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
