import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* 
 * ReEntrantLock Java Doc :
 *  http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReentrantLock.html
 * */

public class Main {

	// We can use any non null object to guard
	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();

	public static void main(String args[]) {
		ThreadA a = new ThreadA();
		ThreadB b = new ThreadB();
		a.start();
		b.start();
	}

	static class ThreadA extends Thread {

		public void run() {
			//while (true) {
				try {
					  // Wait 10 seconds before giving up
					// returns true if the lock was acquired,
					if (lock1.tryLock(10, TimeUnit.SECONDS)) {
						System.out.println("A is locked with lock 1");
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
						}

						System.out.println("A is waiting for lock 2");
						synchronized (Main.lock2) {
							System.out.println("A is locked with lock 1 and lock 2");
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					//UNLOCK those locks
	                if (lock1.isHeldByCurrentThread()) lock1.unlock();
	                if (lock2.isHeldByCurrentThread()) lock2.unlock();
	                System.out.println("A UNLOCKED lock 1 and lock 2 after TIMEOUT");
	            }
		//	}
		}
	}

	static class ThreadB extends Thread {

		public void run() {
			//while (true) {
				try {
					//timeout of 10 seconds
					if (lock2.tryLock(10, TimeUnit.SECONDS)) {
						System.out.println("B is locked with lock 2");

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
						}

						System.out.println("B is waiting for lock 1");
						synchronized (lock1) {
							System.out.println("B is locked with lock 1 and lock 2");
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
	                if (lock2.isHeldByCurrentThread()) lock2.unlock();
	                if (lock1.isHeldByCurrentThread()) lock1.unlock();
	                System.out.println("B UNLOCKED lock 1 and lock 2 after TIMEOUT");
	            }
				
			//}
		}
	}
}
