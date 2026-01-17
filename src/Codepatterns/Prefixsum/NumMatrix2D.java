package Codepatterns.Prefixsum;
/*
304. Range Sum Query 2D - Immutable



RangeSumQuery2D :mathamatically represented as:

Given a 2D matrix matrix, handle multiple queries of the following type:
Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1)
and lower right corner (row2, col2).
Implement the NumMatrix class:
NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the
rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
Example 1:
Input
["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
[[[[3, 0, 1, 4, 2], [-5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
Output
[null, 8, 11, 12]
Explanation
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [-5, 6, 3, 2, 1], [1, 2, 0, 1, 5],
[4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e., 2 + 0 + 1 + 1 + 0 + 3)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e., 6 + 3 + 2 + 0)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e., 3 + 2 + 1 + 0 + 1 + 5)
 */
class NumMatrix2D {

    private int[][] prefix;

    // Constructor: build 2D prefix sum matrix
    public NumMatrix2D(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        prefix = new int[m + 1][n + 1];

        // Build prefix matrix
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] =
                        matrix[i - 1][j - 1]
                                + prefix[i - 1][j]
                                + prefix[i][j - 1]
                                - prefix[i - 1][j - 1];
            }
        }
    }

    // Query sum of submatrix (row1,col1) to (row2,col2)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefix[row2 + 1][col2 + 1]
                - prefix[row1][col2 + 1]
                - prefix[row2 + 1][col1]
                + prefix[row1][col1];
    }

    // ✅ MAIN METHOD INSIDE NumMatrix
    public static void main(String[] args) {

        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        NumMatrix2D obj = new NumMatrix2D(matrix);
        System.out.println(obj.sumRegion(2, 1, 4, 3)); // 8
        System.out.println(obj.sumRegion(1, 1, 2, 2)); // 11
        System.out.println(obj.sumRegion(1, 2, 2, 4)); // 12
    }
}
/*
best youtube explanation: https://www.youtube.com/watch?v=_KASQfRHOws&t=42s
*/
