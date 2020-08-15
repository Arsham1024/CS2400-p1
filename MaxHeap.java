
public class MaxHeap implements MaxHeapInterface {

   private int[] heap;
   private int lastIndex;
   private boolean integrityOk = false;
   private static int countOfReheaps = 0;

   private static final int DEFAULT_SIZE = 25;
   private static final int MAX_SIZE = 1000;

   public MaxHeap (){
      this(DEFAULT_SIZE);
   }
   public MaxHeap (int capacity){

      //making sure the size is within the limits.
      if (capacity > MAX_SIZE) {
         throw new ArrayIndexOutOfBoundsException("The capacity is too large.");
      }
      else if (capacity < DEFAULT_SIZE)
         capacity = DEFAULT_SIZE;

      //creating the heap.
      heap = new int [capacity];

      //Setting the security measures to ok.
      lastIndex = 0;
      integrityOk = true;
   }

   //smart constructor
   public MaxHeap (int [] inputs) {
      this(inputs.length+1);

      lastIndex = inputs.length;


      for (int i = 0; i < inputs.length; i++)
         heap [i+1] = inputs[i];
      for (int i = lastIndex/2 ; i>1 ; --i)
         reheap(i);
   }



   @Override
   public void add(int newEntry) {
      checkIntegrity();
      checkCapacityAndResize();

      int newIndex = lastIndex + 1;
      int parentIndex = newIndex/2;

      while (parentIndex>0 && newEntry < heap[parentIndex]){
         heap [newIndex] = heap [parentIndex];
         newIndex = parentIndex;
         parentIndex = newIndex/2;

      }

      heap [parentIndex] = newEntry;
      lastIndex++;

   }

   @Override
   public int removeMax() {
      checkIntegrity();

      int result = 0;
      if (!isEmpty()){
         result = heap [1];
         heap [1] = heap [lastIndex];
         lastIndex--;
         reheap(1);
      }
      return result;
   }


   @Override
   public void clear() {
      checkIntegrity();
      while (lastIndex>0){
         heap [lastIndex] = 0;
         lastIndex--;
      }
      lastIndex = 0;
   }

   @Override
   public void reheap(int rootIndex) {
      boolean done = false;
      int root = heap[rootIndex];
      int leftChildIndex = 2 * rootIndex;

      while (!done && (leftChildIndex <= lastIndex)) {
         int largerChild = leftChildIndex;
         int rightChildIndex = leftChildIndex + 1;

         if ((rightChildIndex <= lastIndex) &&
            heap[rightChildIndex] > heap[largerChild]) {
            largerChild = rightChildIndex;
         }

         if (root < heap[largerChild]) {
            heap[rootIndex] = heap[largerChild];
            rootIndex = largerChild;
            leftChildIndex = 2 * rootIndex;
            countOfReheaps++;
         }

         else
            done = true;
      }
      heap [rootIndex] = root;

      //to count the number of times this operation has run.
   }


   @Override
   public int getMax() {
      checkIntegrity();
      int root = 0;
      if (!isEmpty())
         root = heap [1];
      return root;
   }

   /** helper methods*/

   @Override
   public int getSize() {
      int size=0;
      for (int i:heap) {
         if(i!=0)
            size++;
      }
      return size;
   }
   @Override
   public boolean isEmpty() {
      return lastIndex <1;
   }
   private void checkIntegrity (){
      if (!integrityOk)
         throw new IllegalStateException("The heap was not properly initialized.");
   }
   private void checkCapacityAndResize (){
      if (lastIndex+1 >= heap.length)
         heap = resize();

   }
   private int [] resize (){
      int [] result = new int [(heap.length)*2];
      for (int i =0 ; i<heap.length; i++)
         result [i] = heap [i];
      return result;
   }
   public int [] toArray (){
      int [] copy = new int [heap.length];
      copy=heap;
      return copy;
   }
   public int [] getFirstTen (){
      int [] firstTen = new int[10];
      int [] copy = toArray();
      for (int i = 1; i < 10 ; i++) {
         firstTen [i] = copy [i];
      }
      return firstTen;
   }

   //getters
   public int getCountOfReheaps(){
      int copy = countOfReheaps;
      return copy;
   }
}
