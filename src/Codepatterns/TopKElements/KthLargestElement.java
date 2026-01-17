package Codepatterns.TopKElements;

/*
leetcode 215. Kth Largest Element in an Array
 */
public class KthLargestElement {

    // Main function to find the k-th largest element
    public static int quickSelect(int[] array, int low, int high, int k) {
        if (low <= high) {
            // Partition the array and get the pivot index
            int pivotIndex = partition(array, low, high);

            // If the pivot index is the (n-k) index, return the element
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
        //can use pivote randomly
//        Random random = new Random();
//        int pivotIndex = low + random.nextInt(high - low + 1); // Random pivot
//        swap(array, pivotIndex, low); // Move pivot to start

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

    // Main method to test the Kth Largest implementation
    public static void main(String[] args) {
        int[] array = {15, 19, 34, 41, 2, 8, 23};
        int k = 3; // Looking for the 3rd largest element (0-based index)
        int n = array.length;
        int result = quickSelect(array, 0, n - 1, n - k); // Convert k-th largest to (n-k)th smallest
        System.out.println("The " + k + "rd largest element is: " + result);
    }
}
/*
Benefits of Random Pivot Selection

Reduced Risk of Worst Case: By randomly selecting the pivot,
the likelihood of encountering the worst-case scenario is minimized, making the algorithm more robust.
Improved Performance on Average: Random pivot selection generally leads to better average-case performance,
especially on diverse datasets where certain patterns might lead to poor performance with fixed pivots.
 */