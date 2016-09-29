
public class Consumer extends Thread {

	static int id = 0; // used to identify Consumer
	Buffer buffer;

	Consumer(Buffer buffer) {
		this.buffer = buffer;
		id++;
	}

	public void run() {
		while (Main.c < Main.n) {
			while (buffer.isEmpty()) {
			}
			if (!buffer.inUse()) {
				int data = buffer.consume();
				System.out.println(data + " consumed by Consumer" + id);
				Main.c++;
				buffer.inUse = false;
			}
		}
	}
}