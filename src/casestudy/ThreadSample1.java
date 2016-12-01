package casestudy;

public class ThreadSample1 {
    public static void main(String[] args) {
        Counter count = new Counter();
        System.out.println("Count Start!!");
        count.start();
        for (int i = 0; i < 15; i++) {
            System.out.println("あ");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
