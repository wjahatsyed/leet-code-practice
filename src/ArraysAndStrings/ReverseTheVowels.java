package ArraysAndStrings;

public class ReverseTheVowels {
    public static void main(String[] args) {
        ReverseTheVowels reverseTheVowels = new ReverseTheVowels();
        System.out.println(
                reverseTheVowels.reverseVowels("IceCreAm"));
    }

    public String reverseVowels(String s) {
        /*
        Input: s = "IceCreAm"
        Output: "AceCreIm
        Explanation:

        The vowels in s are ['I', 'e', 'e', 'A']. On reversing the vowels, s becomes "AceCreIm".
         */
        String vowels = "AEIOUaeiou";
        String reversedVowels = "";
        for (int i = 0; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i) + "")) {
                reversedVowels = reversedVowels.concat(s.charAt(i) + "");
                s = s.replaceFirst(s.charAt(i) + "", "V-");
            }
        }
        StringBuilder stringBuilder = new StringBuilder(reversedVowels);
        stringBuilder.reverse();
        reversedVowels = stringBuilder.toString();

        for (int i = 0; i < reversedVowels.length(); i++) {
            s = s.replaceFirst("V-", reversedVowels.charAt(i) + "");
        }
        return s;
    }

    public String reverserVowelsOpt(String s) {
        //Using two pointers
        char[] word = s.toCharArray();
        String vowels = "AEIOUaeiou";
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            while (start < end && vowels.indexOf(word[start]) == -1) {
                start++;
            }
            while (start < end && vowels.indexOf(word[end]) == -1) {
                end--;
            }

            char temp = word[start];
            word[start] = word[end];
            word[end] = temp;
        }
        return new String(word);
    }
}
