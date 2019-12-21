package Tasks;

public class Task3 implements Runnable {

    public void run() {
        someLogic();
    }

    private static void someLogic() {
        System.out.println("StartTask3");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("FinishTask3");
    }


}