package TwoPointers;

import java.util.HashMap;
import java.util.Map;

public class MaxNumberOfKSumPairs {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        int operations = 0;
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int num = entry.getKey();
            int compliment = k - num;


            if (num > compliment) continue;
            if (num == compliment) {
                operations += entry.getValue() / 2;
            } else if (frequencyMap.containsKey(compliment)) {
                operations += Math.min(entry.getValue(), frequencyMap.get(compliment));

            }
        }
        return operations;
    }
}
