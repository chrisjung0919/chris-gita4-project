package fruitvegsort;

import javax.swing.*;
import java.util.Arrays;

public class FruitVegetableSortMergeSearchGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fruit and Vegetable Sorting");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTextArea outputArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(outputArea);
            frame.add(scrollPane);

            // Input for fruit names
            int fruitCount = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of fruit names: "));
            String[] fruits = new String[fruitCount];
            for (int i = 0; i < fruitCount; i++) {
                fruits[i] = JOptionPane.showInputDialog("Enter fruit name #" + (i + 1) + ": ");
            }

            // Input for vegetable names
            int veggieCount = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of vegetable names: "));
            String[] vegetables = new String[veggieCount];
            for (int i = 0; i < veggieCount; i++) {
                vegetables[i] = JOptionPane.showInputDialog("Enter vegetable name #" + (i + 1) + ": ");
            }

            // Sort arrays using Selection Sort
            selectionSort(fruits);
            selectionSort(vegetables);

            // Merge arrays using Mergesort
            String[] mergedArray = mergeSortAndMerge(fruits, vegetables);

            // Output sorted arrays
            outputArea.append("Sorted Fruit Names: " + Arrays.toString(fruits) + "\n");
            outputArea.append("Sorted Vegetable Names: " + Arrays.toString(vegetables) + "\n");

            // Output merged sorted array
            outputArea.append("Merged and Sorted Array: " + Arrays.toString(mergedArray) + "\n");

            // Search for a particular fruit or vegetable using binary search
            String searchTerm = JOptionPane.showInputDialog("Enter the name to search: ").toLowerCase();
            int resultIndex = binarySearch(mergedArray, searchTerm);

            if (resultIndex != -1) {
                outputArea.append("Found at index " + resultIndex);
            } else {
                outputArea.append("Not found.");
            }

            // Display the frame
            frame.setVisible(true);
        });
    }

    private static void selectionSort(String[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            String temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    private static String[] mergeSortAndMerge(String[] array1, String[] array2) {
        String[] mergedArray = new String[array1.length + array2.length];
        System.arraycopy(array1, 0, mergedArray, 0, array1.length);
        System.arraycopy(array2, 0, mergedArray, array1.length, array2.length);
        mergeSort(mergedArray);
        return mergedArray;
    }

    private static void mergeSort(String[] array) {
        if (array.length > 1) {
            int mid = array.length / 2;
            String[] left = Arrays.copyOfRange(array, 0, mid);
            String[] right = Arrays.copyOfRange(array, mid, array.length);

            mergeSort(left);
            mergeSort(right);

            int i = 0, j = 0, k = 0;
            while (i < left.length && j < right.length) {
                if (left[i].compareTo(right[j]) < 0) {
                    array[k++] = left[i++];
                } else {
                    array[k++] = right[j++];
                }
            }

            while (i < left.length) {
                array[k++] = left[i++];
            }

            while (j < right.length) {
                array[k++] = right[j++];
            }
        }
    }

    private static int binarySearch(String[] array, String target) {
        int low = 0, high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int compareResult = target.compareTo(array[mid]);
            if (compareResult == 0) {
                return mid;
            } else if (compareResult < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1; // Return -1 if not found
    }
}
