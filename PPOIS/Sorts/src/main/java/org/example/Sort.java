package org.example;

import org.example.exceptions.ContainerIsNullException;
import org.example.exceptions.ElementIsNullException;

import java.util.*;

public class Sort {
    /**
     * "Shell" sort for array
     * @param array input array to be sorted
     * @param <T> Type of array
     * @throws ElementIsNullException if one or more elements is null
     * @throws ContainerIsNullException if container is empty or null
     */
    public static <T extends Comparable<T>> void shellSort(T[] array) throws ElementIsNullException, ContainerIsNullException {

        if(array == null || array.length == 0){
            throw new ContainerIsNullException("Array is null");
        }

        int d = 1;

        while (d <= array.length / 3) {
            d = d * 3 + 1;
        }

        while (d > 0) {
            for (int outer = d; outer < array.length; outer++) {
                T temp = array[outer];
                int inner = outer;
                if(array[inner-d] == null)
                    throw new ElementIsNullException("Element with index " + (inner-d) + " is null");
                while (inner > d - 1 && array[inner - d].compareTo(temp) > 0) {
                    array[inner] = array[inner - d];
                    inner -= d;
                }

                array[inner] = temp;
            }

            d = (d - 1) / 3;
        }
    }

    /**
     * "Shell" sort for list
     * @param list input list to be sorted
     * @param <T> Type of list
     * @throws ElementIsNullException if one or more elements is null
     * @throws ContainerIsNullException if container is empty or null
     */
    public static <T extends Comparable<T>> void shellSort(List<T> list) throws ElementIsNullException, ContainerIsNullException{

        if(list == null || list.isEmpty()){
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
    public static <T extends Number> void lsdRadixSort(T[] array) throws ElementIsNullException, ContainerIsNullException
    {
        if(array == null || array.length == 0){
            throw new ContainerIsNullException("Array is null");
        }

        Queue<T>[] buckets = new Queue[10];
        for (int i = 0; i < 10; i++)
            buckets[i] = new LinkedList<T>();

        boolean sorted = false;
        int expo = 1;

        while (!sorted) {
            sorted = true;

            for (T item : array) {
                if(item == null){
                    throw new ElementIsNullException("Element is null");
                }
                int bucket = (item.intValue() / expo) % 10;
                if (bucket > 0) {
                    sorted = false;
                }
                buckets[bucket].add(item);
            }

            expo *= 10;
            int index = 0;

            for (Queue<T> bucket : buckets)
                while (!bucket.isEmpty())
                    array[index++] = bucket.remove();
        }
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
        if(list == null || list.isEmpty()){
            throw new ContainerIsNullException("List is null");
        }

        Queue<T>[] buckets = new Queue[10];
        for (int i = 0; i < 10; i++)
            buckets[i] = new LinkedList<T>();

        boolean sorted = false;
        int expo = 1;

        while (!sorted) {
            sorted = true;

            for (T item : list) {
                if(item == null){
                    throw new ElementIsNullException("Element is null");
                }
                int bucket = (item.intValue() / expo) % 10;
                if (bucket > 0) {
                    sorted = false;
                }
                buckets[bucket].add(item);
            }

            expo *= 10;
            int index = 0;

            for (Queue<T> bucket : buckets)
                while (!bucket.isEmpty())
                    list.set(index++, bucket.remove());
        }
    }
}
