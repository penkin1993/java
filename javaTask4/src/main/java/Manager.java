import Interfaces.Context;
import Interfaces.ExecutionManager;
import Tasks.TaskDecorator;

public class Manager implements ExecutionManager {

    public Context execute(Runnable[] tasks){
        long[] startTime = new long[tasks.length];
        boolean[] isFinished = new boolean[tasks.length];
        boolean[] isFailed = new boolean[tasks.length];
        boolean[] isInterrupt = new boolean[tasks.length];

        TaskDecorator[] runnableTasks = new TaskDecorator[tasks.length];
        for (int id = 0; id < runnableTasks.length; id++){
            startTime[id] = -1;
            isFinished[id] = false;
            isFailed[id] = false;
            isInterrupt[id] = false;
            runnableTasks[id] = new TaskDecorator(tasks[id], startTime, isFinished, isFailed, isInterrupt, id);
        }

        for(TaskDecorator item : runnableTasks) // Запускаем исполнение всех тасков
            new Thread(item).start();


        return new ContextManager(runnableTasks, startTime, isFinished, isFailed);
    }
}
