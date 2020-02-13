package project.examples;

import project.examples.Service;

import java.util.Date;
import java.util.List;

public class ServiceImpl implements Service {

    public List<String> run(String item, double value, Date date) {
        return null;
    }

    public List<String> work(String item) {
        System.out.println(item);
        return null;
    }
}
