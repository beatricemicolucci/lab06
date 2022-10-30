package it.unibo.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */

        final int START = 1000;
        final int END = 2000;
        final int FIRST = 0;
        final int ELEMS = 100000;
        final int NUM_READS = 1000;

        final ArrayList<Integer> arraylist= new ArrayList<>();
        for (int i = START; i < END; i++) {
            arraylist.add(i);
        }

        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addAll(arraylist);

        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */

        int tmp = arraylist.get(FIRST);
        arraylist.set(FIRST, arraylist.get(arraylist.size()-1));
        arraylist.set(arraylist.size()-1, tmp);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */

        for (final int i: arraylist) {
            System.out.print(i + " ");
        }
        System.out.println("");

        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */

        long time = System.nanoTime();

        for (int i = 1; i <= ELEMS; i++) {
            arraylist.add(0, i);
        }

        time = System.nanoTime() - time;
        var millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("INSERT TIME(array): " + time + "ns " + millis + "ms");

        time = System.nanoTime();

        for (int i = 0; i <= ELEMS; i++) {
            linkedList.add(0, i);
        }

        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("INSERT TIME(linkedlist): " + time + "ns " + millis + "ms");

        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         */

        time = System.nanoTime() - time;

        for (int i = 0; i < NUM_READS; i++) {
            arraylist.get(arraylist.size()/2);
        }

        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("READING TIME(arraylist): " + time + "ns " + millis + "ms");

        
        time = System.nanoTime() - time;

        for (int i = 0; i < NUM_READS; i++) {
            linkedList.get(linkedList.size()/2);
        }

        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
         System.out.println("READING TIME(linkedList): " + time + "ns " + millis + "ms");


        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */

        final Map<String , Long> map = new HashMap<>();

        map.put("Africa", 1110635000L);
        map.put("Americas", 972005000L);
        map.put("Antarctica", 0L);
        map.put("Asia", 4298723000L);
        map.put("Europe", 742452000L);
        map.put("Oceania", 38304000L);

        /*
         * 8) Compute the population of the world
         */

        long count = 0;

         for(final Long i: map.values()) {
            count = count + i;
         }

         System.out.println("Population of the world: " + count);
    }
}
