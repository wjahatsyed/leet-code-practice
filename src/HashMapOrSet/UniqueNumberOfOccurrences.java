package HashMapOrSet;

import java.util.*;

public class UniqueNumberOfOccurrences {
    public static void main(String[] args) {
        //[1,2,2,1,1,3]
        int[] arr = {1, 2};
        System.out.println(uniqueOccurrences(arr));
    }

    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> integerMap = new HashMap<>();
        for (int j : arr) {
            if (integerMap.containsKey(j)) {
                integerMap.replace(j, integerMap.get(j) + 1);
            } else {
                integerMap.put(j, 1);
            }
        }
        boolean b = false;
        Set<Integer> integerSet = new HashSet<>();
        List<Integer> integerList = integerMap.values().stream().toList();
        for (Integer integer : integerList) {
            b = integerSet.add(integer);
            if (!b) {
                break;
            }
        }
        return b;

    }
}
