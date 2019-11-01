import hashmap.HashMap;


public class Main {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap<String, Integer>();

        hashMap.put("Russia", 1991);
        hashMap.put("Korea", 1992);
        hashMap.put("USA", 1993);
        hashMap.put("Canada", 1994);
        hashMap.put("Russia1", 1999);
        hashMap.put("Russia2", 1999);
        hashMap.put("Russia3", 1999);
        hashMap.put("Russia4", 1999);
        hashMap.put("Russia5", 1999);
        hashMap.put("Russia6", 1999);
        hashMap.put("Russia7", 1999);
        hashMap.put("Russia8", 1999);
        hashMap.put("Russia9", 1999);
        hashMap.put("Russia10", 1999);
        hashMap.put("Russia11", 1999);
        hashMap.put("Russia12", 1999);
        hashMap.put("Russia13", 1999);
        hashMap.put("Russia14", 1999);
        hashMap.put("Russia15", 1999);
        hashMap.put("Russia16", 1999);

        System.out.println(hashMap.get("Russia"));

        hashMap.remove("Russia");

        System.out.println(hashMap.contains("Russia"));

        System.out.println(hashMap.size());

        for (Object s : hashMap.keySet()) {
            System.out.println(s);
        }

        for(Object el : hashMap.values()){
            System.out.println(el);
        }

        System.out.println(hashMap.size());

    }
}
