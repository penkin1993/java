import Tasks.Task1;
import Tasks.Task2;
import Tasks.Task3;

public class Main {
    public void main(String[] args) {
    Runnable[] runnableArray = new Runnable[3];

    runnableArray[0] = new Task1();
    runnableArray[1] = new Task2();
    runnableArray[2] = new Task3();

    Manager manager = new Manager();

    manager.execute(runnableArray);
    }
}
