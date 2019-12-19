package Interfaces;
import java.util.ArrayList;

public class ExecutionStat implements ExecutionStatistics {
    long[] startTime;
    boolean[] isFinished;

    public ExecutionStat(long[] startTime, boolean[] isFinished){
        this.startTime = startTime;
        this.isFinished = isFinished;
    }

    ArrayList<Long> getStat() {
        ArrayList<Long> stat = new ArrayList();
        for (int i = 0; i < isFinished.length; i++) {
            if (isFinished[i]) {
                stat.add(startTime[i]);
            }
        }
        return stat;
    }

    public int getMinExecutionTimeInMs(){
        ArrayList<Long> stat = getStat();
        int min = 1000000000;
        for (int i = 0; i < stat.size(); i++){
            if(startTime[i] < min){
                min = (int)startTime[i];
            }
        }
        return min;
    }

    public int getMaxExecutionTimeInMs(){
        ArrayList<Long> stat = getStat();
        int max = 0;
        for (int i = 0; i < stat.size(); i++){
            if(startTime[i] > max){
                max = (int)startTime[i];
            }
        }
        return max;
    }


    public int getAverageExecutionTimeInMs(){
        ArrayList<Long> stat = getStat();
        int mean = 0;
        for (int i = 0; i < stat.size(); i++){
            mean = (int)startTime[i];
        }
        return mean / stat.size();
    }

}
