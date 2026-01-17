package Codepatterns.ModifiedBinarySearch;

/*
240. Search a 2D Matrix II
Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
Output: true
 */
public class Search2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0;
        int col = cols - 1;

        while (row < rows && col >= 0) {
            int current = matrix[row][col];
            if (current == target) {
                return true;
            } else if (current > target) {
                col--; // Move left
            } else {
                row++; // Move down
            }
        }

        return false; // Target not found
    }

    public static void main(String[] args) {
        Search2DMatrixII solution = new Search2DMatrixII();
        int[][] matrix = {
                {1,    4,  7, 11},
                {2,   5,  8, 12},
                {3,   6,  9, 16},
                {10, 13, 14, 20},
                {18, 21, 23, 30}
        };
        int target = 5;
        boolean result = solution.searchMatrix(matrix, target);
        System.out.println("Target " + target + " found: " + result); // Output: true

        target = 20;
        result = solution.searchMatrix(matrix, target);
        System.out.println("Target " + target + " found: " + result); // Output: true

        target = 15;
        result = solution.searchMatrix(matrix, target);
        System.out.println("Target " + target + " found: " + result); // Output: false
    }
}
/*
trace input
1,4,7,11
2,5,8,12
3,6,9,16
10,13,14,20
18,21,23,30
target=5
row=0, col=3
current=11 > target => col=2
row=0, col=2
current=7 > target => col=1
row=0, col=1
current=4 < target => row=1
row=1, col=1
current=5 == target => return true

 */
/*
Time Complexity: O(N+M), where N = given row number, M = given column number.
Reason: We are starting traversal from (0, M-1), and at most, we can end up being in the cell (M-1, 0). So,
the total distance can be at most (N+M). So, the time complexity is O(N+M).
This is because in the worst case, you may traverse either all rows or all columns.

Space Complexity: O(1) as we are not using any extra space.
 */