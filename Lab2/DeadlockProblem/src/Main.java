/* Threads are deadlocked when each thread is waiting 
 * for an event that only another process in the set can cause.
 * 
 * How to avoid Deadlocks helpful links :
 * 
 * http://www.javaworld.com/article/2075692/java-concurrency/avoid-synchronization-deadlocks.html 
 * http://www.javacreed.com/what-is-deadlock-and-how-to-prevent-it/
 * */

public class Main {

	// We can use any non null object to guard
	public static Object lock1 = new Object();
	public static Object lock2 = new Object();

	public static void main(String args[]) {
		ThreadA a = new ThreadA();
		ThreadB b = new ThreadB();
		a.start();
		b.start();
	}
	
	static class ThreadA extends Thread {

		public void run() {
			while (true) {
				// intrinsic lock
				// The synchronize block will automatically release the lock when complete
				synchronized (Main.lock1) {
					System.out.println("A is locked with lock 1");
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
					}
					
					System.out.println("A is waiting for lock 2");
					synchronized (Main.lock2) {
						System.out.println("A is locked with lock 1 and lock 2");
					}
				}
			}
		}
	}

	static class ThreadB extends Thread {

		public void run() {
			while (true) {
				synchronized (lock2) {
					System.out.println("B is locked with lock 2");

					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
					}
					
					System.out.println("B is waiting for lock 1");
					synchronized (lock1) {
						System.out.println("B is locked with lock 1 and lock 2");
					}
				}
			}
		}
	}
}
