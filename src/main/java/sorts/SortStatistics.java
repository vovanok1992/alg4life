package sorts;

import annotations.Sort;
import utils.AnnotationHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SortStatistics {
    public static void printStatistics(int arraySize, int minValue, int maxValue) {

        Collection<Method> methods = AnnotationHelper.findAllMethodsWithAnnotation(Sort.class, "sorts");

        if (methods.isEmpty()) {
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

        Map<String, Long> results = new HashMap<String, Long>();
        methods.stream().forEach(method -> results.put(method.getName(), sortStatistic(method, testArray)));

        System.out.println("\nResults:");
        results.entrySet().stream().forEach(
                entry -> System.out.println("[" + entry.getKey() + "] = " + entry.getValue() + "ms")
        );

        if (results.size() > 1) {
            System.out.println("\n-----------");
            printInfo(results);
        }

        System.out.println("--- END --- ");
    }

    private static void printInfo(Map<String, Long> results) {
        Map.Entry<String, Long> min = results.entrySet().iterator().next();
        Map.Entry<String, Long> max = min;

        for (Map.Entry<String, Long> entry : results.entrySet()) {
            if (entry.getValue() < min.getValue()) {
                min = entry;
            } else if (entry.getValue() > max.getValue()) {
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

    private static int[] getCopy(int[] src) {
        int[] dest = new int[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }

    public static void main(String[] args) {
        printStatistics(SortsConstants.DEFAULT_RANGE,
                SortsConstants.DEFAULT_MIN_VALUE,
                SortsConstants.DEFAULT_MAX_VALUE);
    }
}