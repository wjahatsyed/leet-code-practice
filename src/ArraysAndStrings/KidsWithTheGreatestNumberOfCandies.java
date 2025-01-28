package ArraysAndStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

public class KidsWithTheGreatestNumberOfCandies {
    public static void main(String[] args) {
        int[] candies = {2, 3, 5, 1, 3};
        System.out.println(
                kidsWithCandies(candies, 3));
    }

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        /*
        Input: candies = [2,3,5,1,3], extraCandies = 3
        Output: [true,true,true,false,true]
         */

        List<Boolean> booleanList = new ArrayList<>();
        OptionalInt max = Arrays.stream(candies).max();
        for (int candy : candies) {
            boolean bool = false;
            if (candy + extraCandies >= max.getAsInt()) bool = true;
            booleanList.add(bool);
        }
        return booleanList;
    }
}
