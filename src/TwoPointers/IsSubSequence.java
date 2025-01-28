package TwoPointers;
/*
Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string
by deleting some (can be none) of the characters without disturbing the relative positions
 of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 */
public class IsSubSequence {
    public static void main(String[] args) {
        /*
        Example 1:
        Input: s = "abc", t = "ahbgdc"
        Output: true
        Example 2:
        Input: s = "axc", t = "ahbgdc"
        Output: false
         */
        /*System.out.println(
                isSubsequence("axc", "ahbdc"));*/
        System.out.println(
                isSubsequence("acb", "ahbgdc"));
    }

    public static boolean isSubsequence(String s, String t) {
        int sp = 0;
        int tp = 0;
        while ((sp < s.length() && tp < t.length())) {
            if (s.charAt(sp) == t.charAt(tp)) {
                sp++;
            }
            tp++;
        }
        return sp == s.length();

    }
}
