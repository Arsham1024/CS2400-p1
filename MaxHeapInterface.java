public interface MaxHeapInterface {
   public void add (int newEntry);
   public int removeMax();
   public int getMax();
   public boolean isEmpty();
   public int getSize ();
   public void clear ();
   public void reheap(int rootIndex);
}
