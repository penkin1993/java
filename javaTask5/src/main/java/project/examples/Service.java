package project.examples;

import java.util.Date;
import java.util.List;

public interface Service {
    List<String> run(String item, double value, Date date);

    List<String> work(String item);
}






