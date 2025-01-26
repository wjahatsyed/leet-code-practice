public class GCDOfStrings {
    /*
    String1 = "ABCABC"
    String2 = "ABC"
    Output; ABC

    Input: str1 = "ABABAB", str2 = "ABAB"
    Output: "AB"

    Input: str1 = "LEET", str2 = "CODE"
    Output:
     */
    public static String gcdOfStrings(String str1, String str2) {
        String answer = "";
        if (str1.length() > str2.length()) {
            String tempString = str1.substring(0, str2.length());
            if (str2.equals(tempString)) {
                String tempString2 = str1.substring(tempString.length());
                int tempLength_2 = tempString2.length();
                if (tempString2.equals(str2.substring(0, tempLength_2))) {
                    answer = tempString2;
                }
            }
        } else {
            if (str1.equals(str2)) {
                answer = str2;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        /*
        Input: str1 = "ABCABC", str2 = "ABC"
        Output: "AB"
         */
        System.out.println(gcdOfStrings("ABCABC", "ABC"));
    }
}
