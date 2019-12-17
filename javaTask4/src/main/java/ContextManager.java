import Interfaces.Context;
import Interfaces.ExecutionStatistics;

public class ContextManager implements Context {
    int getCompletedTaskCount();
    int getFailedTaskCount();
    int getInterruptedTaskCount();
    void interrupt();
    boolean isFinished();
    void onFinish(Runnable callback);
    ExecutionStatistics getStatistics();
    void awaitTermination(); // TODO: Навешивать join !!!
}
