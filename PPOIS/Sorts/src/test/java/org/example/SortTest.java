package org.example;

import org.example.exceptions.ContainerIsNullException;
import org.example.exceptions.ElementIsNullException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    void shellSortUsingArray() {

        Person[] personArray = {
                new Person("A", 11),
                new Person("E", 15),
                new Person("B", 36),
                new Person("C", 42),
                new Person("D", 17),
                new Person("F", 39),
                new Person("G", 56),
                new Person("I", 22),
                new Person("W", 5),
                new Person("S", 7)
        };

        Person[] personArraySorted = {
                new Person("W", 5),
                new Person("S", 7),
                new Person("A", 11),
                new Person("E", 15),
                new Person("D", 17),
                new Person("I", 22),
                new Person("B", 36),
                new Person("F", 39),
                new Person("C", 42),
                new Person("G", 56)
        };
        Sort.shellSort(personArray);
        assertArrayEquals(personArray, personArraySorted);

        Person[] personArrayIsNull = null;

        ContainerIsNullException thrown1 = assertThrows(ContainerIsNullException.class ,() ->{
            Sort.shellSort(personArrayIsNull);
        });

        assertEquals("Array is null", thrown1.getMessage());

        Person[] personArrayWithNullElement = new Person[10];
        personArrayWithNullElement[0] = new Person("A", 11);
        personArrayWithNullElement[1] = null;
        personArrayWithNullElement[2] = new Person("B", 36);
        personArrayWithNullElement[3] = new Person("C", 42);
        personArrayWithNullElement[4] = new Person("D", 17);
        personArrayWithNullElement[5] = new Person("F", 39);
        personArrayWithNullElement[6] = new Person("G", 56);
        personArrayWithNullElement[7] = new Person("I", 22);
        personArrayWithNullElement[8] = new Person("W", 5);
        personArrayWithNullElement[9] = new Person("S", 7);

        ElementIsNullException thrown2 = assertThrows(ElementIsNullException.class ,() ->{
            Sort.shellSort(personArrayWithNullElement);
        });

        assertEquals("Element with index 1 is null", thrown2.getMessage());

        Integer[] integersArray = new Integer[5];
        integersArray[0] = 2;
        integersArray[1] = -2;
        integersArray[2] = 0;
        integersArray[3] = 5;
        integersArray[4] = 1;

        Integer[] integersArraySorted = new Integer[5];
        integersArraySorted[0] = -2;
        integersArraySorted[1] = 0;
        integersArraySorted[2] = 1;
        integersArraySorted[3] = 2;
        integersArraySorted[4] = 5;

        Sort.shellSort(integersArray);
        assertArrayEquals(integersArray, integersArraySorted);
    }

    @Test
    void ShellSortUsingList() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("A", 11));
        personList.add(new Person("E", 15));
        personList.add(new Person("B", 36));
        personList.add(new Person("C", 42));
        personList.add(new Person("D", 17));
        personList.add(new Person("F", 39));
        personList.add(new Person("G", 56));
        personList.add(new Person("I", 22));
        personList.add(new Person("W", 5));
        personList.add(new Person("S", 7));

        List<Person> personListSorted = new ArrayList<>();
        personListSorted.add(new Person("W", 5));
        personListSorted.add(new Person("S", 7));
        personListSorted.add(new Person("A", 11));
        personListSorted.add(new Person("E", 15));
        personListSorted.add(new Person("D", 17));
        personListSorted.add(new Person("I", 22));
        personListSorted.add(new Person("B", 36));
        personListSorted.add(new Person("F", 39));
        personListSorted.add(new Person("C", 42));
        personListSorted.add(new Person("G", 56));
        Sort.shellSort(personList);
        assertEquals(personList, personListSorted);

        List<String> stringList = new ArrayList<>();
        stringList.add("ggg");
        stringList.add("ee");
        stringList.add("eea");
        stringList.add("a");

        List<String> stringListSorted = new ArrayList<>();
        stringListSorted.add("a");
        stringListSorted.add("ee");
        stringListSorted.add("eea");
        stringListSorted.add("ggg");

        Sort.shellSort(stringList);
        assertEquals(stringList, stringListSorted);

        List<Person> personListIsNull = null;
        ContainerIsNullException thrown1 = assertThrows(ContainerIsNullException.class ,() ->{
            Sort.shellSort(personListIsNull);
        });

        assertEquals("List is null", thrown1.getMessage());

        List<Person> personListWithNullElement = new ArrayList<>();
        personListWithNullElement.add(null);
        personListWithNullElement.add(null);

        ElementIsNullException thrown2 = assertThrows(ElementIsNullException.class ,() ->{
            Sort.shellSort(personListWithNullElement);
        });

        assertEquals("Element with index 0 is null", thrown2.getMessage());
    }

    @Test
    void lsdRadixSortUsingArray() {
        Money[] moneyArray = {
                new Money(11),
                new Money(15),
                new Money(36),
                new Money(42),
                new Money(17),
                new Money(39),
                new Money(56),
                new Money(22),
                new Money(5),
                new Money(7)
        };
        Money[] moneyArraySorted = {
                new Money(5),
                new Money(7),
                new Money(11),
                new Money(15),
                new Money(17),
                new Money(22),
                new Money(36),
                new Money(39),
                new Money(42),
                new Money(56)
        };

        Sort.lsdRadixSort(moneyArray);
        assertArrayEquals(moneyArray, moneyArraySorted);

        Integer[] integersArray = new Integer[5];
        integersArray[0] = 5;
        integersArray[1] = 4;
        integersArray[2] = 3;
        integersArray[3] = 2;
        integersArray[4] = 1;

        Integer[] integersArraySorted = new Integer[5];
        integersArraySorted[0] = 1;
        integersArraySorted[1] = 2;
        integersArraySorted[2] = 3;
        integersArraySorted[3] = 4;
        integersArraySorted[4] = 5;

        Sort.lsdRadixSort(integersArray);
        assertArrayEquals(integersArray, integersArraySorted);

        Money[] moneyArrayIsNull = null;

        ContainerIsNullException thrown1 = assertThrows(ContainerIsNullException.class ,() ->{
            Sort.lsdRadixSort(moneyArrayIsNull);
        });

        assertEquals("Array is null", thrown1.getMessage());

        Money[] moneyArrayWithNullElement = new Money[2];
        moneyArrayWithNullElement[0] = null;
        moneyArrayWithNullElement[1] = null;

        ElementIsNullException thrown2 = assertThrows(ElementIsNullException.class ,() ->{
            Sort.lsdRadixSort(moneyArrayWithNullElement);
        });

        assertEquals("Element is null", thrown2.getMessage());
    }

    @Test
    void LsdRadixSortUsingList() {
        List<Money> moneyList = new ArrayList<>();
        moneyList.add(new Money(11));
        moneyList.add(new Money(15));
        moneyList.add(new Money(36));
        moneyList.add(new Money(42));
        moneyList.add(new Money(17));
        moneyList.add(new Money(39));
        moneyList.add(new Money(56));
        moneyList.add(new Money(22));
        moneyList.add(new Money(5));
        moneyList.add(new Money(7));

        List<Money> moneyListSorted = new ArrayList<>();
        moneyListSorted.add(new Money(5));
        moneyListSorted.add(new Money(7));
        moneyListSorted.add(new Money(11));
        moneyListSorted.add(new Money(15));
        moneyListSorted.add(new Money(17));
        moneyListSorted.add(new Money(22));
        moneyListSorted.add(new Money(36));
        moneyListSorted.add(new Money(39));
        moneyListSorted.add(new Money(42));
        moneyListSorted.add(new Money(56));

        Sort.lsdRadixSort(moneyList);
        assertEquals(moneyList, moneyListSorted);

        List<Money> moneyListIsNull = null;
        ContainerIsNullException thrown1 = assertThrows(ContainerIsNullException.class ,() ->{
            Sort.lsdRadixSort(moneyListIsNull);
        });

        assertEquals("List is null", thrown1.getMessage());

        List<Money> moneyListWithNullElement = new ArrayList<>();
        moneyListWithNullElement.add(null);
        moneyListWithNullElement.add(null);

        ElementIsNullException thrown2 = assertThrows(ElementIsNullException.class ,() ->{
            Sort.lsdRadixSort(moneyListWithNullElement);
        });

        assertEquals("Element is null", thrown2.getMessage());
    }
}