package Tasks;

public class Task3 implements Runnable{

    public void run(){
        someLogic();
    }

    private static void someLogic() {
        System.out.println("StartTask1");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace(); // TODO: Отправлять наверх !!!
        }
        System.out.println("FinishTask1");
    }


}