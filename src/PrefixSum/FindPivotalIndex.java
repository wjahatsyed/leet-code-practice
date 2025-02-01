package PrefixSum;

public class FindPivotalIndex {
    public static void main(String[] args) {
        FindPivotalIndex findPivotalIndex = new FindPivotalIndex();
        System.out.println(
                findPivotalIndex.pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
    }

    public int pivotIndex(int[] nums) {
        int lsum = 0, rsum = 0;
        for (int i : nums) {
            rsum += i;
        }
        for (int i = 0; i < nums.length; i++) {
            rsum -= nums[i];
            if (rsum == lsum) {
                return i;
            }
            lsum += nums[i];
        }
        return -1;
    }
}
