package HashMapOrSet;
 /*
    Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.

A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).



Example 1:


Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
Output: 1
Explanation: There is 1 equal row and column pair:
- (Row 2, Column 1): [2,7,7]
Example 2:


Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
Output: 3
Explanation: There are 3 equal row and column pairs:
- (Row 0, Column 0): [3,1,2,2]
- (Row 2, Column 2): [2,4,2,2]
- (Row 3, Column 2): [2,4,2,2]


Constraints:

n == grid.length == grid[i].length
1 <= n <= 200
1 <= grid[i][j] <= 105
     */

import java.util.*;

public class EqualRowAndColumnPairs {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<String, Integer> rowCount = new HashMap<>();
        int count = 0;

        // Store row counts in HashMap
        for (int[] row : grid) {
            String key = Arrays.toString(row); // Convert row to a string representation
            rowCount.put(key, rowCount.getOrDefault(key, 0) + 1);
        }

        // Check for matching columns in rowCount
        for (int col = 0; col < n; col++) {
            int[] colArray = new int[n];
            for (int row = 0; row < n; row++) {
                colArray[row] = grid[row][col]; // Construct column array
            }
            String key = Arrays.toString(colArray); // Convert column to string
            count += rowCount.getOrDefault(key, 0); // Add count if exists in rowCount
        }

        return count;
    }

    public static void main(String[] args) {
        EqualRowAndColumnPairs solution = new EqualRowAndColumnPairs();

        int[][] grid1 = {
                {3, 2, 1},
                {1, 7, 6},
                {2, 7, 7}
        };
        System.out.println(solution.equalPairs(grid1)); // Output: 1

        int[][] grid2 = {
                {3, 1, 2, 2},
                {1, 4, 4, 5},
                {2, 4, 2, 2},
                {2, 4, 2, 2}
        };
        System.out.println(solution.equalPairs(grid2)); // Output: 3
    }
}

