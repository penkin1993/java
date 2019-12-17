package Tasks;

public class Task2 implements Runnable{

    public void run(){
        someLogic();
    }

    private static void someLogic() {
        System.out.println("StartTask1");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace(); // TODO: Отправлять наверх !!!
        }
        System.out.println("FinishTask1");
    }


}