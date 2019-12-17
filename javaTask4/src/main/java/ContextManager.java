import Interfaces.Context;
import Interfaces.ExecutionStatistics;

class ContextManager implements Context {
    int getCompletedTaskCount();
    int getFailedTaskCount();
    int getInterruptedTaskCount();
    void interrupt();
    boolean isFinished();
    void onFinish(Runnable callback);
    ExecutionStatistics getStatistics();
    void awaitTermination(); // TODO: Навешивать join !!!
}
