import Interfaces.Context;
import Interfaces.ExecutionManager;
import Tasks.TaskDecorator;

public class Manager implements ExecutionManager {

    public Context execute(Runnable[] tasks) {
        long[] startTime = new long[tasks.length];
        boolean[] isFinished = new boolean[tasks.length];
        boolean[] isFailed = new boolean[tasks.length];
        boolean[] isInterrupt = new boolean[tasks.length];
        Thread[] threads = new Thread[tasks.length];

        TaskDecorator[] runnableTasks = new TaskDecorator[tasks.length];
        for (int id = 0; id < runnableTasks.length; id++) {
            startTime[id] = -1;
            isFinished[id] = false;
            isFailed[id] = false;
            isInterrupt[id] = false;
            runnableTasks[id] = new TaskDecorator(tasks[id], startTime, isFinished, isFailed, isInterrupt, id);
        }

        for (int i = 0; i < tasks.length; i++) {
            threads[i] = new Thread(runnableTasks[i]);
            threads[i].start();
        }

        return new ContextManager(threads, startTime, isFinished, isFailed);
    }
}
