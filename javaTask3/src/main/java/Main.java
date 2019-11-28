
import java.awt.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;

import classes.serClasses.SerClassType;

class Address {
    private final String city = "CITY1";
    private final String postalCode = "postalCode1";
}

class Person {
    private final String firstName = "firstName1";
    private final String lastName = "lastName1";
    private final Address address = new Address();
    //private final List<String> phoneNumbers;
}

public class Main {

    public static void main(String[] args) {

        Person person = new Person();
        //String person = "sadasdas";

        SerClassType ser = new SerClassType();

        System.out.print(ser.run(0, person));

    }
}




