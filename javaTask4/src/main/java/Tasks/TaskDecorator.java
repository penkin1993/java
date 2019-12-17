package Tasks;

public class TaskDecorator implements Runnable{
    private Runnable task;
    private long startTime = -1; // TODO: Сделать видимыми главному потоку !!!!
    boolean isFinished = false; // TODO: Сделать видимыми главному потоку !!!!

    public TaskDecorator(Runnable task){
        this.task = task;
    }

    public void run() {
        startTime = System.nanoTime();

        task.run();
        // TODO: Пилить через notify ????

        startTime = System.nanoTime() - startTime; // TODO: В одной секции !!!
        isFinished = true; // TODO:

    }



    // TODO: Добавть методы для управления !!!!
}
