import java.lang.*;
import java.util.ArrayList;

import classes.serClasses.JsonSerializer;
import classes.serClasses.ReflectionSerializer;
import classes.serClasses.XmlSerializer;


class Address {
    private final String city = "CITY1";
    private final String postalCode = "postalCode1";
}

class Person {
    private final String firstName = "firstName1";
    private final String lastName = "lastName1";
    private final Address address = new Address();
    ArrayList phoneNumbers = new ArrayList(3);
}

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person();
        person.phoneNumbers.add("Russia");
        person.phoneNumbers.add("China");
        person.phoneNumbers.add("USSR");

        ReflectionSerializer ser = new ReflectionSerializer(new JsonSerializer());

        System.out.print(ser.serialize(person));

    }
}