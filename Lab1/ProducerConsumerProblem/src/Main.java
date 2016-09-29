public class Main {

	static int n = 20;
	static int p = 0;
	static int c = 0;

	public static Object lock = new Object();
	
	public static void main(String[] args) {
		final Buffer buffer = new Buffer(5);// buffer of maxSize array 5

		Thread p = new Producer(buffer);
		p.start();

		Thread p1 = new Producer(buffer);
		p1.start();

		Thread p2 = new Producer(buffer);
		p2.start();
		
		Thread c = new Consumer(buffer);
		c.start();
		
		Thread c1 = new Consumer(buffer);
		c1.start();
		
		Thread c2 = new Consumer(buffer);
		c2.start();
		
		Thread c3 = new Consumer(buffer);
		c3.start();
	}
}
