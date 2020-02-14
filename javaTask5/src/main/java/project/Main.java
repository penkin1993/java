package project;

import project.examples.Calculator;
import project.examples.CalculatorImpl;
import project.examples.Service;
import project.examples.ServiceImpl;

public class Main {
    public static void main(String[] args) {
        CacheProxy cacheHandler = new CacheProxy("./");

        Service serviceImpl = cacheHandler.cache(new ServiceImpl()); // TODO: Работает не так ???!!!
        System.out.println(serviceImpl.work("World1"));
        System.out.println(serviceImpl.work("World2"));
        System.out.println(serviceImpl.work("World3"));
        System.out.println(serviceImpl.work("World3"));
        System.out.println(serviceImpl.work("World3"));

        Calculator calculator = cacheHandler.cache(new CalculatorImpl());
        System.out.println(calculator.run("Hello"));
        System.out.println(calculator.run("Hello1"));
        System.out.println(calculator.run("Hello2"));
        System.out.println(calculator.run("World"));
        System.out.println(calculator.run("World"));
        System.out.println(calculator.run("World"));
        System.out.println(calculator.run("World"));
        System.out.println(calculator.run("World"));
    }
}

