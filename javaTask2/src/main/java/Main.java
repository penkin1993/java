import hashmap.HashMap;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList array = new ArrayList();
        array.add("USSR");
        array.add(null);
        array.add("China");
        array.add("Voronezh");

        for (int i = 0; i < array.size(); i++){
            System.out.println(array.get(i));
        }
    }
}
