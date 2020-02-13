package project;


public class Main{
    public static void main(String[] args) {

        Class[] defaultIdentityBy = {Object.class};
        CacheHandler cacheHandler = new CacheHandler("./", CacheType.FILE, 100,
                "BLABLA", true, defaultIdentityBy);

        Calculator calculator = cacheHandler.cache(new CalculatorImpl());
        System.out.println(calculator.run("Hello"));
        System.out.println(calculator.run("World"));
        System.out.println(calculator.run("World"));


        Service serviceImpl = cacheHandler.cache(new ServiceImpl());
        System.out.println(serviceImpl.work("World1"));
        System.out.println(serviceImpl.work("World2"));
        System.out.println(serviceImpl.work("World3"));
        System.out.println(serviceImpl.work("World4"));
        System.out.println(serviceImpl.work("World5"));
        System.out.println(serviceImpl.work("World6"));
        System.out.println(serviceImpl.work("World7"));

    }
}

