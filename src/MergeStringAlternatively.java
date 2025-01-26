public class MergeStringAlternatively {
    public static String mergeAlternately(String word1, String word2) {
       /*
       Three cases:
       1. Either the lengths of the strings are same.
       2. Or the length of word1 < word2.
       3. Or the length of word1 > word2.
        */
        String answer = "";
        String temp = "";
        int length = 0;
        //boolean isSame = true;
        if (word1.length() < word2.length()) {
            temp = word2.substring(word1.length());
            word2 = word2.substring(0, word1.length());
            length = word1.length();

        } else if(word1.length() > word2.length()) {
            temp = word1.substring(word2.length());
            word1 = word1.substring(0, word2.length());
            length = word2.length();
        } else{
            length = word1.length();
        }

        for (int i = 0; i < length; i++) {
            answer = answer.concat(word1.charAt(i) + "");
            answer = answer.concat(word2.charAt(i) + "");
        }
        answer = answer.concat(temp);
        return answer;
    }

    public static void main(String[] args) {
        String name1 = "abc";
        String name2 = "pqr";
        System.out.println(mergeAlternately(name1, name2));
    }
}
