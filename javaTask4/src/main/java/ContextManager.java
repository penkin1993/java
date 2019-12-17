import Interfaces.Context;
import Interfaces.ExecutionStatistics;
import Tasks.TaskDecorator;

class ContextManager implements Context {
    private TaskDecorator[] runnableTasks;

    ContextManager(TaskDecorator[] runnableTasks){
        this.runnableTasks = runnableTasks;
    }


    int getCompletedTaskCount();


    int getFailedTaskCount();


    int getInterruptedTaskCount();


    void interrupt();
    

    boolean isFinished();


    void onFinish(Runnable callback);


    ExecutionStatistics getStatistics();


    void awaitTermination(); // TODO: Навешивать join !!!


}
