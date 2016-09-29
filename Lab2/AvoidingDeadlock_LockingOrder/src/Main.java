/* 
 * Obtain locks in a constant, predefined, order!
 * A better way to do this is to centralize the lock acquisition 
 * and release process to a single class (singleton).
 *  In this example I just made sure to order the locks correctly.
 *  */

public class Main {

	public static Object Lock1 = new Object();
	public static Object Lock2 = new Object();

	public static void main(String args[]) {
		ThreadA threadA = new ThreadA();
		ThreadB threadB = new ThreadB();
		threadA.start();
		threadB.start();
	}

	private static class ThreadA extends Thread {
		public void run() {
			while (true) {
				// lock 1 then lock 2
				synchronized (Lock1) {
					System.out.println("A is locked with lock 1.");

					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
					}
					System.out.println("A is waiting for lock 2.");

					synchronized (Lock2) {
						System.out.println("A Holding lock 1 & 2.");
					}
				}
			}
		}
	}

	private static class ThreadB extends Thread {
		public void run() {
			while (true) {
				// lock 1 then lock 2 (same as ThreadA)
				synchronized (Lock1) {
					System.out.println("B is locked with lock 1.");

					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
					}

					System.out.println("B is waiting for lock 2.");
					synchronized (Lock2) {
						System.out.println("B is locked with lock 1 & 2.");
					}
				}
			}
		}
	}
}
