package sort;

import java.util.*;
import java.util.Comparator;
import sortusagejava.*;

/**
 *
 * @author Costamagna Alberto e Gianotti Damiano
 * @param <T>: type of the sorted list of elements
 */
public class Sort<T> {

    /**
     * Method that order a generic list of elements using the comparator
     *
     * @param list: generic list of items to be sorted
     * @param comparator: a comparator implementing the precedence relation between
     *        the list elements.
     * @throws sort.SortException iff the parameter is null
     */
    public void insertionSort(List<T> list, Comparator<T> comparator) throws SortException {
        if (list == null || comparator == null)
            throw new SortException("Invalid parameters");

        for (int i = 1; i < list.size(); i++) {
            int j = i;
            while ((j > 0) && (comparator.compare(list.get(j), list.get(j - 1)) < 0)) {
                swap(list, j, j - 1);
                j--;
            }
        }
    }

    /**
     * Method that swap two elements of the list
     *
     * @param list: generic list of items
     * @param i,j : the two index of the elemnts of the list to swap
     */
    private void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * Method that prints the array on screen
     * 
     * @param list : list to print
     */

    public void printArray(List<T> list) // TODO perche' era static ?
    {
        for (int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
    }

    /**
     * Method that sort the list
     * 
     * @param list: generic list of items to sort
     * @param left,right : two index for the algorithm
     * @param comparator: comparator: a comparator implementing the precedence
     *        relation between the list elements.
     * @throws sort.SortException iff the parameter is null
     */

    public void mergeSort(List<T> list, int left, int right, Comparator<T> comparator) throws SortException {
        if (list == null || comparator == null)
            throw new SortException("Errore parametri");
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(list, left, center, comparator);
            mergeSort(list, center + 1, right, comparator);
            merge(list, left, center, right, comparator);
        }
    }

    /**
     * Method that merge two parts of the list after they have been ordered
     * 
     * @param list: generic list of items to merge
     * @param left,center,right: tree index for the algorithm
     * @param comparator: a comparator implementing the precedence relation between
     *        the list elements.
     */

    private void merge(List<T> list, int left, int center, int right, Comparator<T> comparator) {
        int i = left;
        int j = center + 1;
        ArrayList<T> subList = new ArrayList<T>();

        while (i <= center && j <= right) {
            if (comparator.compare(list.get(i), list.get(j)) < 0) {
                subList.add(list.get(i));
                i++;
            } else {
                subList.add(list.get(j));
                j++;
            }
        }

        while (i <= center) {
            subList.add(list.get(i));
            i++;
        }

        while (j <= right) {
            subList.add(list.get(j));
            j++;
        }

        int p = 0;
        int q = left;

        while (p < subList.size()) {
            list.set(q, subList.get(p++));
            q++;
        }

    }

    /**
     * Method that check if the list is already sorted
     * 
     * @param list : list to check
     */
    private boolean isSorted(ArrayList<Long> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that checks if there are two number in the list that if they are
     * summed the result is n
     * 
     * @param list: list to check
     * @param n: the number to check
     */
    public boolean isSumContained(ArrayList<Long> list, long n) throws SortException {
        if (list == null)
            throw new SortException("Invalid parameters");

        if (isSorted(list) == false) {
            try {
                Sort<Long> order = new Sort<Long>();
                order.mergeSort(list, 0, list.size() - 1, new LongComparator());
            } catch (SortException e) {
                System.out.println("Error while sorting the array");
            }
        }

      return ricBinaria(list,n);
    }

    /**
     * Method that checks if there are two number in the list that if they are
     * summed the result is n
     * 
     * @param list: the list to check
     * @param n: the number to check
     */
    private boolean ricBinaria(ArrayList<Long> list, long n) {
        int i = 0;
        int j = list.size()-1;
        long somma = 0;
        while(i<j)
        {
            somma = list.get(i) + list.get(j);
            if (somma == n)
                return true;
            else if (somma > n)
                j--;
            else 
                i++;
        }
        return false;

    }

}
