package Tasks;

public class Task2 implements Runnable {

    public void run() {
        someLogic();
    }

    private static void someLogic() {
        System.out.println("StartTask2");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("FinishTask2");
    }


}