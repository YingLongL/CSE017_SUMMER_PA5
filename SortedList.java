import java.util.ArrayList;
import java.util.Comparator;

/**
 * A generic class that implements a sorted list using an ArrayList. 
 * This list can be sorted either by a natural ordering of its elements or by a specified Comparator.
 *
 * @param <E> the type of elements in this list, which must extend Comparable<E>
 */
public class SortedList<E extends Comparable<E>> {
    private ArrayList<E> list;
    private Comparator<E> comparator;

    /**
     * Constructs an empty SortedList with no comparator. 
     * The natural ordering of its elements will be used.
     */
    public SortedList() {
        list = new ArrayList<>();
        comparator = null;
    }

    /**
     * Constructs an empty SortedList with the specified comparator.
     *
     * @param comparator the Comparator to be used for sorting this list
     */
    public SortedList(Comparator<E> comparator) {
        list = new ArrayList<>();
        this.comparator = comparator;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return list.size();
    }

    /**
     * Adds an item to the list in the sorted position.
     *
     * @param item the item to be added to the list
     */
    public void add(E item) {
        int index = 0;
        while (index < list.size()) {
            if (comparator != null) {
                if (comparator.compare(item, list.get(index)) < 0) {
                    break;
                }
            } else {
                if (item.compareTo(list.get(index)) < 0) {
                    break;
                }
            }
            index++;
        }
        list.add(index, item);
    }

    /**
     * Public method to find an item in the list using binary search.
     *
     * @param target the item to be searched in the list
     * @return the item if found, or null if not found
     */
    public E find(E target) {
        return find(target, 0, list.size() - 1);
    }

    /**
     * Private recursive method to find an item in the list using binary search.
     *
     * @param target the item to be searched in the list
     * @param low the lower bound index for the current search
     * @param high the upper bound index for the current search
     * @return the item if found, or null if not found
     */
    private E find(E target, int low, int high) {
        if (low > high) {
            return null;
        }

        int mid = (low + high) / 2;
        int comparison;
        if (comparator != null) {
            comparison = comparator.compare(target, list.get(mid));
        } else {
            comparison = target.compareTo(list.get(mid));
        }

        if (comparison == 0) {
            return list.get(mid);
        } else if (comparison < 0) {
            return find(target, low, mid - 1);
        } else {
            return find(target, mid + 1, high);
        }
    }

    /**
     * Sets a new Comparator for this list and sorts the list using the new Comparator.
     *
     * @param comparator the new Comparator to set
     */
    public void setComparator(Comparator<E> comparator) {
        this.comparator = comparator;
        sort();
    }

    /**
     * Sorts the list using insertion sort based on the current Comparator or natural ordering.
     */
    private void sort() {
        for (int i = 1; i < list.size(); i++) {
            E key = list.get(i);
            int j = i - 1;

            while (j >= 0) {
                if (comparator != null) {
                    if (comparator.compare(list.get(j), key) <= 0) {
                        break;
                    }
                } else {
                    if (list.get(j).compareTo(key) <= 0) {
                        break;
                    }
                }
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }

    /**
     * Returns a string representation of this list, where each element is followed by a newline.
     *
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E item : list) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }
}