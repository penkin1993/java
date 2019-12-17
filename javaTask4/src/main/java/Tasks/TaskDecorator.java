package Tasks;

public class TaskDecorator implements Runnable{
    private Runnable task;

    public TaskDecorator(Runnable task){
        this.task = task;
    }

    public void run() {
        task.run();
    }




    // TODO: Добавть методы для управления !!!!
}
