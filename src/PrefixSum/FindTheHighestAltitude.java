package PrefixSum;

import java.util.Arrays;

public class FindTheHighestAltitude {
    public static void main(String[] args) {
        /*
        Input: gain = [-5,1,5,0,-7]
        Output: 1
        Explanation: The altitudes are [0,-5,-4,1,1,-6]. The highest is 1.
         */
        FindTheHighestAltitude findTheHighestAltitude = new FindTheHighestAltitude();
        int[] gain = {-4,-3,-2,-1,4,3,2};
        System.out.println(
                findTheHighestAltitude.largestAltitude(gain));
    }

    public int largestAltitude(int[] gain) {
        int[] array = new int[gain.length + 1];
        for (int i = 0; i < gain.length; i++) {
            array[i + 1] = array[i] + gain[i];
        }

        Arrays.sort(array);
        return array[array.length - 1];
    }
}
