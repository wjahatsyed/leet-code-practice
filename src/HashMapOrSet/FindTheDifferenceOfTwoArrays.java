package HashMapOrSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Example 1:

Input: nums1 = [1,2,3], nums2 = [2,4,6]
Output: [[1,3],[4,6]]
Explanation:
For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1
and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].

For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4
and nums2[2] = 6 are not present in nums2. Therefore, answer[1] = [4,6].
 */
public class FindTheDifferenceOfTwoArrays {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        Set<Integer> set1 = new HashSet();
        Set<Integer> set2 = new HashSet();

        for (int i : nums1) {
            set1.add(i);
        }
        for (int j : nums2) {
            set2.add(j);
        }

        for (int k : set1) {
            if (!set2.contains(k)) {
                l1.add(k);
            }
        }

        for (int l : set2) {
            if (!set1.contains(l)) {
                l2.add(l);
            }
        }

        list.add(l1);
        list.add(l2);

        return list;
    }
}
