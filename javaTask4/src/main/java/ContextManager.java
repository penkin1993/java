import Interfaces.Context;
import Interfaces.ExecutionStat;
import Interfaces.ExecutionStatistics;

class ContextManager implements Context {
    private final Thread[] threads;
    private final long[] startTime;
    private final boolean[] isFinished;
    private final boolean[] isFailed;

    ContextManager(Thread[] threads, long[] startTime, boolean[] isFinished,
                   boolean[] isFailed) {
        this.threads = threads;
        this.startTime = startTime;
        this.isFinished = isFinished;
        this.isFailed = isFailed;
    }

    public int getCompletedTaskCount() {
        synchronized (startTime) {
            int sum = 0;
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
        synchronized (startTime) {
            sum = getCompletedTaskCount();
            if (sum == isFinished.length) {
                return true;
            }
        }
        return false;
    }


    public int getFailedTaskCount() {
        synchronized (startTime) {
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
        for (int i = 0; i < threads.length; i++) { // TODO
            threads[i].join();
        }
    }

    public void onFinish(Runnable callback) throws InterruptedException {
        this.awaitTermination();

        callback.run();
    }
}








