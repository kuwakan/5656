package casestudy;

public class Counter extends Thread {
    public void run() {
        for (int i = 0; i <= 15; i++) {
            System.out.println(i + ",");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
        }
    }
}
