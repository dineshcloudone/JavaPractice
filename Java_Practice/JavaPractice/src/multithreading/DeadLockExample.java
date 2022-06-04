package multithreading;

public class DeadLockExample {
	public static void main(String[] args) {
		String resource1 = "Dinesh";
		String resource2 = "Kumar";

		Thread t1 = new Thread() {
			public void run() {
				synchronized (resource1) {
					System.out.println("Thread1 : resource1 is locked");

					try {
						Thread.sleep(300);
					} catch (Exception e) {
						System.out.println(e);
					}

					synchronized (resource2) {
						System.out.println("Thread1 : resource2 is locked");
					}
				}
			}
		};

		Thread t2 = new Thread() {
			public void run() {
				synchronized (resource2) {
					System.out.println("Thread2 : resource2 is locked");

					try {
						Thread.sleep(300);
					} catch (Exception e) {
						System.out.println(e);
					}

					synchronized (resource1) {
						System.out.println("Thread2 : resource1 is locked");
					}
				}
			}
		};

		t1.start();
		t2.start();
	}
}
