package org.example;

import org.example.exceptions.ContainerIsNullException;
import org.example.exceptions.ElementIsNullException;

import java.util.*;

public class Sort {
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

    public static <T extends Numerable & Comparable<T>> void lsdRadixSort(T[] array) throws ElementIsNullException, ContainerIsNullException
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
                int bucket = (item.getValue() / expo) % 10;
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

    public static <T extends Numerable & Comparable<T>> void lsdRadixSort(List<T> list) throws ElementIsNullException, ContainerIsNullException
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
                int bucket = (item.getValue() / expo) % 10;
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

    public static void lsdRadixSort(Number[] array) throws ElementIsNullException, ContainerIsNullException
    {
        if(array == null || array.length == 0){
            throw new ContainerIsNullException("Array is null");
        }

        Queue<Number>[] buckets = new Queue[10];
        for (int i = 0; i < 10; i++)
            buckets[i] = new LinkedList<Number>();

        boolean sorted = false;
        int expo = 1;

        while (!sorted) {
            sorted = true;

            for (Number item : array) {
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

            for (Queue<Number> bucket : buckets)
                while (!bucket.isEmpty())
                    array[index++] = bucket.remove();
        }
    }

}
