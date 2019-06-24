import java.util.Arrays;
import java.util.Random;

public class HeapSort<E extends Comparable<? super E>> {
	private static final int DEFAULT_SIZE = 8;
	private int currentSize;
	private E[] heap;
	public HeapSort()
	{
		this(DEFAULT_SIZE);
	}
	public HeapSort(int size)
	{
		currentSize = 0;
		heap = (E[]) new Comparable[ size + 1 ];
	}
	public HeapSort(E[] list)
	{
		currentSize = list.length;
		heap = (E[]) new Comparable[ currentSize*2 + 1 ];
		for(int i = 0; i < list.length; i++)
			heap[i+1] = list[i];
		buildHeap();
	}

	public void buildHeap()
	{
		for(int i = currentSize / 2; i > 0; i--)
		{
			percolateDown(i);
		}
	}
	public boolean isEmpty()
	{
		return (currentSize == 0);
	}
	public void makeEmpty()
	{
		currentSize = 0;
	}
	private void growArray(int newSize)
	{
		E[] old = heap;
		heap = (E []) new Comparable[ newSize ];
        for( int i = 0; i < old.length; i++ )
        	heap[ i ] = old[ i ];
	}
	private void growArray()
	{
		this.growArray(heap.length<<1);
	}
	public void insert(E val)
	{
		if( currentSize == heap.length - 1 )
			growArray();

		currentSize++;
		int hole = currentSize;
		heap[0] = val;
		percolateUp(hole);
	}
	private void percolateUp(int pos)
	{
		E item = heap[0];
		for(; item.compareTo(heap[pos/2]) < 0; pos = pos/2)
			heap[pos] = heap[pos/2];
		heap[pos] = item;
	}
	public E findMin()
	{
		if(currentSize == 0)
			return null;
		return heap[1];
	}
	public E deleteMin()
	{
		if(currentSize == 0)
			return null;
		E temp = heap[1];

		heap[1] = heap[currentSize];
		currentSize--;

		percolateDown(1);

		return temp;
	}
	private void percolateDown(int pos)
	{
		int child;
		E temp = heap[pos];
		for(; pos*2 <= currentSize; pos = child)
		{
			child = pos*2;
			if(child != currentSize &&
					heap[child+1].compareTo(heap[child]) < 0)
				child++;
			if(heap[child].compareTo(temp) < 0)
				heap[pos] = heap[child];
			else
				break;
		}
		heap[pos] = temp;
	}

	public static void main(String[] args){
		HeapSort myHeap = new HeapSort();
		
		int randArrayLength = 10;
		int[] randArray = new int[randArrayLength];
		for(int i = 0; i < randArrayLength; i++){
			randArray[i] = (int) (Math.random()*50);
		}
		System.out.println("Here is a random array: "+Arrays.toString(randArray));
		for(int i = 0; i < randArray.length; i++){
			myHeap.insert(randArray[i]);
		}
		
		System.out.print("Sorted array: ");
		for(int i = 0; i < randArrayLength; i++){
			System.out.print(myHeap.deleteMin()+", ");
		}
		
		
	}

}