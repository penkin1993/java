import Interfaces.Context;
import Interfaces.ExecutionManager;
import Tasks.TaskDecorator;

public class Manager implements ExecutionManager {

    public Context execute(Runnable[] tasks){

        TaskDecorator[] runnableTasks = new TaskDecorator[tasks.length];
        for (int i = 0; i < runnableTasks.length; i++){
            runnableTasks[i] = new TaskDecorator(tasks[i]); // обертываем все таски !!!
        }

        for(TaskDecorator item : runnableTasks) // Запускаем исполнение всех тасков
            new Thread(item).start();


        return new ContextManager(runnableTasks);
    }
}
