package org.petrov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

public class MyArrayListTest {

    private MyArrayList<Integer> myArrayList;

    @BeforeEach
    public void setUp() {
        myArrayList = new MyArrayList<>();
    }

    @Test
    public void testAdd() {
        for (int i = 1; i <= 5; i++) {
            myArrayList.add(i);
            assertEquals(i, myArrayList.size());
        }
    }

    @Test
    public void testAddAtIndex() {
        myArrayList.add(1);

        for (int i = 2; i <= 5; i++) {
            myArrayList.addAtIndex(0, i);
            assertEquals(i, myArrayList.size());
            assertEquals(i, myArrayList.get(0));
        }
    }

    @Test
    public void testAddAtIndexShift() {
        for (int i = 1; i <= 5; i++) {
            myArrayList.add(i);
        }

        myArrayList.addAtIndex(2, 10);

        assertEquals(6, myArrayList.size());

        assertEquals(1, myArrayList.get(0));
        assertEquals(2, myArrayList.get(1));
        assertEquals(10, myArrayList.get(2));
        assertEquals(3, myArrayList.get(3));
        assertEquals(4, myArrayList.get(4));
        assertEquals(5, myArrayList.get(5));
    }


    @Test
    public void testUpdate() {
        myArrayList.add(1);

        for (int i = 2; i <= 5; i++) {
            myArrayList.update(0, i);
            assertEquals(1, myArrayList.size());
            assertEquals(i, myArrayList.get(0));
        }
    }

    @Test
    public void testDeleteAtIndex() {
        for (int i = 1; i <= 5; i++) {
            myArrayList.add(i);
        }

        for (int i = 4; i >= 0; i--) {
            myArrayList.deleteAtIndex(i);
            assertEquals(i, myArrayList.size());
        }
    }

    @Test
    public void testQuickSort() {
        int[] expected = {1, 2, 3, 4, 5};

        for (int i = 5; i >= 1; i--) {
            myArrayList.add(i);
        }

        myArrayList.quickSort();

        for (int i = 0; i < 5; i++) {
            assertEquals(expected[i], myArrayList.get(i));
        }
    }

    @Test
    public void testQuickSortWithComparator() {
        int[] expected = {5, 4, 3, 2, 1};

        for (int i = 1; i <= 5; i++) {
            myArrayList.add(i);
        }

        Comparator<Integer> comparator = (a, b) -> b - a;
        myArrayList.quickSort(comparator);

        for (int i = 0; i < 5; i++) {
            assertEquals(expected[i], myArrayList.get(i));
        }
    }

    @Test
    public void testClear() {
        for (int i = 1; i <= 5; i++) {
            myArrayList.add(i);
        }

        myArrayList.clear();

        assertEquals(0, myArrayList.size());
    }

    @Test
    public void testSize() {
        assertEquals(0, myArrayList.size());

        for (int i = 1; i <= 5; i++) {
            myArrayList.add(i);
            assertEquals(i, myArrayList.size());
        }
    }


}
