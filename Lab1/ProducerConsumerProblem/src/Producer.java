import java.util.Random;

public class Producer extends Thread {

	static int id = 0; // used to identify Consumer
	Buffer buffer;

	Producer(Buffer buffer) {
		this.buffer = buffer;
		id++;
	}

	public void run() {
		while (Main.p < Main.n) {
			while (buffer.isFull()) {
			}
			if (!buffer.inUse()) {
				Random r = new Random();
				int data = r.nextInt(100);
				buffer.produce(data);
				System.out.println("Data " + data + " produced by id " + id);
				Main.p++;
				buffer.inUse = false;

			}
		}
	}
}
