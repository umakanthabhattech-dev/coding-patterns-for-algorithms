package Codepatterns.TopKElements;

/*
Kth smallest Element in an Array
 */
public class KthSmallestElement {

    // Main function to find the k-th smallest element
    public static int quickSelect(int[] array, int low, int high, int k) {
        if (low <= high) {
            // Partition the array and get the pivot index
            int pivotIndex = partition(array, low, high);

            // If the pivot index is the k-th index, return the element
            if (pivotIndex == k) {
                return array[pivotIndex];
            }
            // If k is less than the pivot index, search in the left subarray
            else if (k < pivotIndex) {
                return quickSelect(array, low, pivotIndex - 1, k);
            }
            // If k is greater than the pivot index, search in the right subarray
            else {
                return quickSelect(array, pivotIndex + 1, high, k);
            }
        }
        // Return -1 if the k-th element is not found (should not happen in valid cases)
        return -1;
    }

    // Function to partition the array
    private static int partition(int[] array, int low, int high) {
        // Choosing the first element as pivot
        int pivot = array[low];
        int left = low + 1;
        int right = high;

        while (left <= right) {
            // Increment left index while the element is less than or equal to the pivot
            while (left <= high && array[left] <= pivot) {
                left++;
            }
            // Decrement right index while the element is greater than the pivot
            while (right >= low && array[right] > pivot) {
                right--;
            }
            // Swap elements if left is less than right
            if (left < right) {
                swap(array, left, right);
            }
        }
        // Swap the pivot element with the right index
        swap(array, low, right);
        return right; // Return the pivot index
    }

    // Function to swap two elements in the array
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Main method to test the QuickSelect implementation
    public static void main(String[] args) {
        int[] array = {15, 19, 34, 41, 2, 8, 23, 1};
        int k = 3; // Looking for the 3rd smallest element (0-based index)
        int result = quickSelect(array, 0, array.length - 1, k);
        System.out.println("The " + (k + 1) + "rd smallest element is: " + result);
    }
}
/*
Time
Average case O(N) partitioning process divides the array into two roughly equal halves, leading to a linear number of comparisons.
bestcase becomes O(N) - which occurs when the pivot divides the array into two equal halves.
worst case = O(N2) This occurs when the pivot chosen is consistently the smallest or largest element, leading to unbalanced partitions
Space
The space complexity of QuickSelect is O(log n) in the average case due to the recursive call stack
In the worst case, the space complexity can be O(n) if the recursion goes as deep as the number of elements in the array,
which happens when the pivot is poorly chosen.
 */