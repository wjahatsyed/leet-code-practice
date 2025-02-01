package ArraysAndStrings;

public class IncreasingTripletSubsequence {
    public static void main(String[] args) {
        IncreasingTripletSubsequence increasingTripletSubsequence = new IncreasingTripletSubsequence();
        increasingTripletSubsequence.increasingTriplet(new int[]{20, 100, 10, 12, 5, 13});
    }

    public boolean increasingTriplet(int[] nums) {
        /*
        Example 1:
        Input: nums = [1,2,3,4,5]
        Output: true
        Explanation: Any triplet where i < j < k is valid.
        Example 2:

        Input: nums = [5,4,3,2,1]
        Output: false
        Explanation: No triplet exists.

        [20,100,10,12,5,13] Output: True.
         */
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= first) {
                first = n;
            } else if (n <= second) {
                second = n;
            } else {
                return true;
            }
        }
        return false;
    }
}
