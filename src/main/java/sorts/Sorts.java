package sorts;

import annotations.Sort;
import utils.AnnotationHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/*
 *
 */
public class Sorts {

    private Sorts() {
    }

    @Sort
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && key < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }

    }

    @Sort
    public static void mergeSort(int[] array) {
        int[] sortedArray = mergeSortRec(array);
        System.arraycopy(sortedArray, 0, array, 0, array.length);
    }

    private static int[] mergeSortRec(int[] array) {
        if (array.length < 2) return array;
        return merge(mergeSortRec(Arrays.copyOfRange(array, 0, array.length / 2)),
                mergeSortRec(Arrays.copyOfRange(array, (array.length / 2) , array.length)));
    }

    private static int[] merge(int[] arr, int[] arr2) {
        int[] result = new int[arr.length + arr2.length];
        int count1 = 0;
        int count2 = 0;
        int resultCount = 0;

        while (resultCount != result.length) {
            if (count1 < arr.length && count2 < arr2.length) {
                if (arr[count1] < arr2[count2]) {
                    result[resultCount++] = arr[count1++];
                } else {
                    result[resultCount++] = arr2[count2++];
                }
            } else if (count1 < arr.length) {
                result[resultCount++] = arr[count1++];
            } else if (count2 < arr2.length) {
                result[resultCount++] = arr2[count2++];
            }
        }
        return result;
    }


    public static void printStatistics(int arraySize, int minValue, int maxValue){

        Collection<Method> methods = AnnotationHelper.findAllMethodsWithAnnotation(Sort.class, "sorts");

        if(methods.isEmpty()){
            System.out.println("No available sorts!");
            return;
        }

        System.out.println("Available sorts: ");
        methods.stream().forEach(method -> System.out.println("* " + method.getName()));

        System.out.println("\nParameters:");
        System.out.println("Array size -> " + arraySize);
        System.out.println("Min value -> " + minValue);
        System.out.println("Max value -> " + maxValue);

        int[] testArray = new Random().ints(arraySize, minValue, maxValue).toArray();

        Map<String, Long> results = new HashMap<>();
        methods.stream().forEach(method -> results.put(method.getName(), sortStatistic(method, testArray)));

        System.out.println("\nResults:");
        results.entrySet().stream().forEach(
                entry -> System.out.println("[" + entry.getKey() + "] = " + entry.getValue() + "ms" )
        );

        if(results.size() > 1){
            System.out.println("\n-----------");
            printInfo(results);
        }

        System.out.println("--- END --- ");
    }

    private static void printInfo(Map<String, Long> results) {
        Map.Entry<String, Long> min = results.entrySet().iterator().next();
        Map.Entry<String, Long> max = min;

        for(Map.Entry<String, Long> entry: results.entrySet()){
            if(entry.getValue() < min.getValue()){
                min = entry;
            } else if(entry.getValue() > max.getValue()){
                max = entry;
            }
        }

        System.out.println("Fastest: [" + min.getKey() + "] = " + min.getValue());
        System.out.println("Slowest: [" + max.getKey() + "] = " + max.getValue());
    }

    private static long sortStatistic(Method method, int[] testArray) {
        int[] copyOfTestArray = getCopy(testArray);

        long time = 0;

        try {
            time = System.currentTimeMillis();
            method.invoke(new Object(), copyOfTestArray);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis() - time;
    }

    private static int[] getCopy(int[] src){
        int[] dest = new int[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }

    public static void main(String[] args) {
        printStatistics(10000, 0, 1000);
    }
}
