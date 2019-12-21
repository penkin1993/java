import Interfaces.Context;
import Tasks.Task1;
import Tasks.Task2;
import Tasks.Task3;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Runnable[] runnableArray = new Runnable[3];

        runnableArray[0] = new Task1();
        runnableArray[1] = new Task2();
        runnableArray[2] = new Task3();

        Manager manager = new Manager();

        Context contextManager = manager.execute(runnableArray);
        contextManager.onFinish(new Task1()); // TODO


        

        while (!contextManager.isFinished()) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(contextManager.isFinished());
        }
    }
}
