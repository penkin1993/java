package project.examples;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceImpl implements Service {

    @Override
    public List<String> run(String item, double value, Date date) {
        return null;
    }

    @Override
    public List<String> work(String item) {
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {

        }
        ArrayList<String> list = new ArrayList<>();
        list.add("ad");
        return list; // TODO: Не должен возврашать null ?????
    }
}
