public class Buffer {
	// buffer for the data produced - to be consumed by the consumer
	public int[] arrayOfData;

	public boolean inUse = false;

	public boolean inUse() {
		//checks if the 
		if (inUse)
			return true;
		inUse = true;
		return false;
	}

	// maximum size of the array
	private int maxSize;

	// the front of the array
	private int front = 0;
	// to check that the buffer is full
	int currentSize = 0;

	public Buffer(int maxSize) {
		this.maxSize = maxSize;// init the size of buffer
		arrayOfData = new int[maxSize];
	}

	public boolean isEmpty() {
		return (currentSize == 0);
	}

	public boolean isFull() {
		return (currentSize == maxSize);
	}

	public void produce(int a) {
		// add data to the front of the array.
		// increase the array currentSize
		arrayOfData[(front + currentSize) % maxSize] = a;
		currentSize++;
	}

	public int consume() {
		// remove int at the front of the array
		// reduce currentSize
		int data = arrayOfData[front];
		// reset the front to be the previous
		front = (front + 1) % maxSize;
		currentSize--;
		return data;
	}
}
