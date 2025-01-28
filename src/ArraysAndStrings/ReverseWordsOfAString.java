package ArraysAndStrings;

import java.util.ArrayList;
import java.util.List;

public class ReverseWordsOfAString {
    public String reverseWords(String s) {
        /*
        Input: s = "the sky is blue"
        Output: "blue is sky the"
         */
        s = s.trim();
        String reversed = "";
        String[] words = s.split("\s+");
        for (int i = words.length - 1; i > 0; i--) {
            reversed += words[i] + " ";
        }

        return reversed + words[0];
    }

    public static void main(String[] args) {
        ReverseWordsOfAString reverseWordsOfAString = new ReverseWordsOfAString();
        System.out.println(
                reverseWordsOfAString.reverseWords("F R  I   E    N     D      S  "));
    }
}
