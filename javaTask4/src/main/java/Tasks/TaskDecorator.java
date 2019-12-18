package Tasks;

public class TaskDecorator implements Runnable {
    private Runnable task;
    final private long[] startTime;
    final private boolean[] isFinished;
    final private boolean[] isFailed;
    final private boolean[] isInterrupt;
    private int id;

    public TaskDecorator(Runnable task, long[] startTime, boolean[] isFinished,
                         boolean[] isFailed, boolean[] isInterrupt, int id) {
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
            synchronized (isInterrupt){
                if(isInterrupt[id]){
                    startTime[id] = -2;
                    return;
                }
            }
        }

        try {
            task.run();
        }catch (Exception e){
            synchronized (isFailed){
                this.isFailed[id] = true;
            }
        }


        synchronized (startTime) {
            startTime[id] = System.currentTimeMillis() - startTime[id];
            synchronized (isFinished) {
                isFinished[id] = true;
            }
        }
    }
}
