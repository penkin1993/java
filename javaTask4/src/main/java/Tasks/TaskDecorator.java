package Tasks;

public class TaskDecorator implements Runnable {
    private Runnable task;
    private final long[] startTime;
    private final Boolean[] isFinished;
    private final Boolean[] isFailed;
    private final Boolean[] isInterrupt;
    private int id;

    public TaskDecorator(Runnable task, long[] startTime, Boolean[] isFinished,
                         Boolean[] isFailed, Boolean[] isInterrupt, int id) {
        this.task = task;
        this.startTime = startTime;
        this.isFinished = isFinished;
        this.isFailed = isFailed;
        this.isInterrupt = isInterrupt;
        this.id = id;
    }

    public void run() {
        synchronized (startTime) {
            startTime[id] = System.currentTimeMillis();
            if (isInterrupt[id]) {
                startTime[id] = -1;
                return;
            }
        }

        try {
            task.run();
        } catch (Exception e) {
            synchronized (startTime) {
                this.isFailed[id] = true;
            }
        }


        synchronized (startTime) {
            startTime[id] = System.currentTimeMillis() - startTime[id];
            isFinished[id] = true;
        }

    }
}
