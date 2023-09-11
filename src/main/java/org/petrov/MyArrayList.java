package org.petrov;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MyArrayList is a generic dynamic array implementation that allows you to store and manipulate elements of any type.
 * It provides methods for adding, updating, deleting, sorting, and other common operations on the array.
 *
 * @param <E> the type of elements stored in the array
 */
public class MyArrayList<E> {

    /**
     * Internal array to store elements.
     */
    private Object[] array;

    /**
     * Default capacity of the MyArrayList.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Current number of elements in the array.
     */
    private int size;

    /**
     * Constructs an empty MyArrayList with the default initial capacity.
     */
    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Constructs an empty MyArrayList with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the MyArrayList
     * @throws IllegalArgumentException if the specified initial capacity is not positive
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            array = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * Adds an element to the end of the MyArrayList.
     *
     * @param element the element to be added
     */
    public void add(E element) {
        if (size == array.length - 1) {
            ensureCapacity(DEFAULT_CAPACITY);
        }
        array[size] = element;
        size++;
    }

    /**
     * Inserts an element at the specified index in the MyArrayList.
     *
     * @param index   the index at which the element will be inserted
     * @param element the element to be inserted
     */
    public void addAtIndex(int index, E element) {
        rangeCheck(index);
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = element;
        size++;
    }

    /**
     * Ensures that the MyArrayList can hold at least the specified number of elements.
     *
     * @param minCapacity the minimum required capacity
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = Math.max(array.length * 2, minCapacity);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    /**
     * Returns the current number of elements in the MyArrayList.
     *
     * @return the size of the MyArrayList
     */
    public int size() {
        return size;
    }

    /**
     * Gets the element at the specified index in the MyArrayList.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E get(int index) {
        rangeCheck(index);
        return (E) array[index];
    }

    /**
     * Clears all elements from the MyArrayList, setting it back to the default capacity.
     */
    public void clear() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    
    private void rangeCheck(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Недопустимый индекс : " + index);
    }

    /**
     * Updates the element at the specified index in the MyArrayList.
     *
     * @param index   the index of the element to update
     * @param element the new element to set at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void update(int index, E element) {
        rangeCheck(index);
        array[index] = element;
    }

    /**
     * Deletes the element at the specified index in the MyArrayList.
     *
     * @param index the index of the element to delete
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void deleteAtIndex(int index) {
        rangeCheck(index);
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    /**
     * Sorts the elements in the MyArrayList using the QuickSort algorithm.
     */
    public void quickSort() {
        quickSort(0, size - 1, null);
    }

    /**
     * Sorts the elements in the MyArrayList using the QuickSort algorithm with a custom comparator.
     *
     * @param comparator the comparator used for sorting
     */
    public void quickSort(Comparator<E> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Sorts a portion of the MyArrayList using the QuickSort algorithm.
     *
     * @param low        the starting index of the portion to be sorted
     * @param high       the ending index of the portion to be sorted
     * @param comparator the comparator used for sorting (can be null if elements are Comparable)
     */
    private void quickSort(int low, int high, Comparator<E> comparator) {
        if (low < high) {
            int partitionIndex = partition(low, high, comparator);
            quickSort(low, partitionIndex - 1, comparator);
            quickSort(partitionIndex + 1, high, comparator);
        }
    }

    /**
     * Partitions a portion of the MyArrayList for the QuickSort algorithm.
     *
     * @param low        the starting index of the portion to be partitioned
     * @param high       the ending index of the portion to be partitioned
     * @param comparator the comparator used for sorting (can be null if elements are Comparable)
     * @return the index of the pivot element after partitioning
     */
    private int partition(int low, int high, Comparator<E> comparator) {
        E pivot = get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(get(j), pivot, comparator) <= 0) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    /**
     * Swaps two elements in the MyArrayList.
     *
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private void swap(int i, int j) {
        E temp = (E) array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Compares two elements using the specified comparator or their natural ordering if elements are Comparable.
     *
     * @param a          the first element to compare
     * @param b          the second element to compare
     * @param comparator the comparator used for comparison (can be null if elements are Comparable)
     * @return a negative integer, zero, or a positive integer as the first element is less than, equal to, or greater than the second
     * @throws UnsupportedOperationException if elements are not Comparable and no comparator is provided
     */
    private int compare(E a, E b, Comparator<E> comparator) {
        if (comparator != null) {
            return comparator.compare(a, b);
        } else if (a instanceof Comparable) {
            return ((Comparable<E>) a).compareTo(b);
        } else {
            throw new UnsupportedOperationException("Элементы не сравнимы");
        }
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "array=" + Arrays.toString(array) + '}';
    }
}
