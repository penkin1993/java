import Interfaces.Context;
import Interfaces.ExecutionStat;
import Interfaces.ExecutionStatistics;
import Tasks.TaskDecorator;

class ContextManager implements Context {
    private Thread[] threads;
    private TaskDecorator[] runnableTasks;
    private long[] startTime;
    private boolean[] isFinished;
    private boolean[] isFailed;

    ContextManager(Thread[] threads, TaskDecorator[] runnableTasks, long[] startTime, boolean[] isFinished,
                   boolean[] isFailed) {
        this.threads = threads;
        this.runnableTasks = runnableTasks;
        this.startTime = startTime;
        this.isFinished = isFinished;
        this.isFailed = isFailed;
    }

    public int getCompletedTaskCount() {
        int sum = 0;
        synchronized (isFinished) {
            for (boolean b : isFinished) {
                sum += b ? 1 : 0;
            }
            return sum;
        }
    }

    public void interrupt() {
        synchronized (startTime) {
            for (int i = 0; i < isFinished.length; i++) {
                isFinished[i] = true;
            }
        }
    }

    public int getInterruptedTaskCount() {
        synchronized (startTime) {
            int sum = 0;
            for (long item : startTime) {
                if (item == -1) {
                    sum++;
                }
            }
            return sum;
        }
    }


    public boolean isFinished() {
        int sum = getInterruptedTaskCount();
        if (sum == startTime.length) {
            return true;
        }
        synchronized (isFinished) {
            sum = getCompletedTaskCount();
            if (sum == isFinished.length) {
                return true;
            }
        }
        return false;
    }


    public int getFailedTaskCount() {
        synchronized (isFailed) {
            int sum = 0;
            for (boolean b : isFailed) {
                sum += b ? 1 : 0;
            }
            return sum;
        }
    }

    public ExecutionStatistics getStatistics() {
        ExecutionStat executionStat = new ExecutionStat(startTime, isFinished);
        return executionStat;
    }

    public void awaitTermination() throws InterruptedException {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(runnableTasks[i]);
            threads[i].join();
        }
    }


    public void onFinish(Runnable callback) {
        while (!isFinished()) {
            try {
                callback.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        callback.run();
    }
}








