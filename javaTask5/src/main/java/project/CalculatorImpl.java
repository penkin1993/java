package project;

import java.util.HashMap;
import java.util.Map;

public class CalculatorImpl implements Calculator{
    @Override
    public int run(String arg){
        try{
            Thread.sleep(1_000);
        } catch (InterruptedException e){

        }
        return arg.hashCode();
    }

}
