package Tasks;

public class Task1 implements Runnable {

    public void run() {
        someLogic();
    }

    private static void someLogic() {
        System.out.println("StartTask1");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("FinishTask1");
    }


}