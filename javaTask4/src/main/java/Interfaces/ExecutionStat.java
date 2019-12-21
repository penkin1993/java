package Interfaces;

import java.util.ArrayList;
import java.util.List;

public class ExecutionStat implements ExecutionStatistics {
    private long[] startTime;
    private Boolean[] isFinished;

    public ExecutionStat(long[] startTime, Boolean[] isFinished) {
        this.startTime = startTime;
        this.isFinished = isFinished;
    }

    private List<Long> getStat() {
        List<Long> stat = new ArrayList<>();
        for (int i = 0; i < isFinished.length; i++) {
            if (isFinished[i]) {
                stat.add(startTime[i]);
            }
        }
        return stat;
    }

    public int getMinExecutionTimeInMs() {
        List<Long> stat = getStat();
        int min = 1000000000;
        for (int i = 0; i < stat.size(); i++) {
            if (startTime[i] < min) {
                min = (int) startTime[i];
            }
        }
        return min;
    }

    public int getMaxExecutionTimeInMs() {
        List<Long> stat = getStat();
        int max = 0;
        for (int i = 0; i < stat.size(); i++) {
            if (startTime[i] > max) {
                max = (int) startTime[i];
            }
        }
        return max;
    }


    public int getAverageExecutionTimeInMs() {
        List<Long> stat = getStat();
        int mean = 0;
        for (int i = 0; i < stat.size(); i++) {
            mean = (int) startTime[i];
        }
        return mean / stat.size();
    }

}
