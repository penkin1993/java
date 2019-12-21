package Interfaces;

public interface Context {
    int getCompletedTaskCount();

    int getFailedTaskCount();

    int getInterruptedTaskCount();

    void interrupt();

    boolean isFinished();

    void onFinish(Runnable callback) throws InterruptedException;

    ExecutionStatistics getStatistics();

    void awaitTermination() throws InterruptedException;
}
