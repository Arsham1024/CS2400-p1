import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        final int MAX_CAP =  101;

        /**file input*/
        File file = new File("C:\\Users\\arsha\\Desktop\\Desktop\\Project2\\Read_In\\data.txt");
        Scanner readIn = new Scanner(file);

        //array to store the ints from the file.
        int [] entries = new int[MAX_CAP];

        //reading the data into the array
        int i =0;
        while (readIn.hasNext()) {
            entries [i+1] = readIn.nextInt();
            i++;
        }


        /** not the smart way*/
        MaxHeap myHeap = new MaxHeap();
        /** the smart way*/
        MaxHeap myHeap2 = new MaxHeap(entries);


//        sequentially adding elements to the heap.
        for (int p: entries) {
            myHeap.add(p);
        }
        myHeap.reheap(1);



        /** sequential insertions */
        System.out.println("Heap built using sequential insertions: " + Arrays.toString(myHeap.getFirstTen()));
        System.out.println("Number of swaps in the heap creation: " );
        System.out.println("Heap after 10 removals: " );

        //space
        System.out.println();

        /** optimal method */
        System.out.println("Heap built using optimal method: " + Arrays.toString(myHeap2.getFirstTen()));
        System.out.println("Number of swaps in the heap creation: " + myHeap2.getCountOfReheaps());
        System.out.println("Heap after 10 removals: " );

    }
}
