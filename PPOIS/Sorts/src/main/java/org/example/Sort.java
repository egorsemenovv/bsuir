package org.example;

import org.example.exceptions.ContainerIsNullException;
import org.example.exceptions.ElementIsNullException;

import java.util.*;

public class Sort {

    private static final int RADIX = 10;

    /**
     * "Shell" sort for array
     * @param array input array to be sorted
     * @param <T> Type of array
     * @throws ElementIsNullException if one or more elements is null
     * @throws ContainerIsNullException if container is empty or null
     */
    public static <T extends Comparable<T>> void shellSort(T[] array) throws ElementIsNullException, ContainerIsNullException {
        if(array == null){
            throw new ContainerIsNullException("Array is null");
        }
        List<T> convertedArrayToList = arrayToListConverter(array);
        shellSort(convertedArrayToList);
        listToArrayConverter(array, convertedArrayToList);
    }

    /**
     * "Shell" sort for list
     * @param list input list to be sorted
     * @param <T> Type of list
     * @throws ElementIsNullException if one or more elements is null
     * @throws ContainerIsNullException if container is empty or null
     */
    public static <T extends Comparable<T>> void shellSort(List<T> list) throws ElementIsNullException, ContainerIsNullException{

        if(list == null){
            throw new ContainerIsNullException("List is null");
        }

        int d = 1;

        while (d <= list.size() / 3) {
            d = d * 3 + 1;
        }

        while (d > 0) {
            for (int outer = d; outer < list.size(); outer++) {
                T temp = list.get(outer);
                int inner = outer;
                if(list.get(inner-d) == null)
                    throw new ElementIsNullException("Element with index " + (inner-d) + " is null");
                while (inner > d - 1 && list.get(inner - d).compareTo(temp) > 0) {
                    list.set(inner, list.get(inner - d));
                    inner -= d;
                }
                list.set(inner, temp);
            }
            d = (d - 1) / 3;
        }
    }

    /**
     * "LSD Radix" sor for array
     * @param array input array to be sorted
     * @param <T> Type of array
     * @throws ElementIsNullException if one or more elements is null
     * @throws ContainerIsNullException if container is empty or null
     */
    public static <T extends Number> void lsdRadixSort(T[] array) throws ElementIsNullException, ContainerIsNullException {
        if(array == null){
            throw new ContainerIsNullException("Array is null");
        }
        List<T> convertedArrayToList = arrayToListConverter(array);
        lsdRadixSort(convertedArrayToList);
        listToArrayConverter(array, convertedArrayToList);
    }

    /**
     *
     * @param list input list to be sorted
     * @param <T> Type of List
     * @throws ElementIsNullException if one or more elements is null
     * @throws ContainerIsNullException if container is empty or null
     */
    public static <T extends Number> void lsdRadixSort(List<T> list) throws ElementIsNullException, ContainerIsNullException
    {
        if(list == null){
            throw new ContainerIsNullException("List is null");
        }
        Queue<T>[] buckets = new Queue[RADIX];
        for (int i = 0; i < RADIX; i++)
            buckets[i] = new LinkedList<>();

        boolean sorted = false;
        int expo = 1;

        while (!sorted) {
            sorted = true;

            for (T item : list) {
                if(item == null){
                    throw new ElementIsNullException("Element is null");
                }
                int bucket = (item.intValue() / expo) % RADIX;
                if (bucket > 0) {
                    sorted = false;
                }
                buckets[bucket].add(item);
            }

            expo *= RADIX;
            int index = 0;

            for (Queue<T> bucket : buckets)
                while (!bucket.isEmpty())
                    list.set(index++, bucket.remove());
        }
    }

    /**
     * converts array to list
     * @param array massive to be represented as list
     * @return list
     * @param <T> Type of array
     */
    private static <T> List<T> arrayToListConverter(T[] array){
        return new ArrayList<>(Arrays.asList(array));
    }

    /**
     * rewrites array`s data from list
     * @param array to be rewritten from list
     * @param list from which data is copied
     * @param <T> Type of list
     */
    private static <T> void listToArrayConverter(T[] array, List<T> list){
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
    }
}
