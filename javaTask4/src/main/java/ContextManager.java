import Interfaces.Context;
import Interfaces.ExecutionStat;
import Interfaces.ExecutionStatistics;

import java.util.Arrays;

class ContextManager implements Context {
    private final Thread[] threads;
    private final long[] startTime;
    private final Boolean[] isFinished;
    private final Boolean[] isFailed;

    ContextManager(Thread[] threads, long[] startTime, Boolean[] isFinished,
                   Boolean[] isFailed) {
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
            Arrays.stream(isFinished).forEach(item -> item = true);
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
        sum = getCompletedTaskCount();
        return (sum == isFinished.length);
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

    public void awaitTermination() {
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        });
    }

    public void onFinish(Runnable callback) {
        this.awaitTermination();
        callback.run();
    }
}








