import java.util.NoSuchElementException;

import org.junit.Test;

import static org.junit.Assert.*;

import hashmap.*;

public class MainHashMapTest {
    HashMap hashMap1 = new HashMap<String, Integer>();
    HashMap hashMap2 = new HashMap<String, Integer>();
    HashMap hashMap3 = new HashMap<String, Integer>();

    @Test
    public void assertHashMap1() {
        hashMap1.put("Russia", 1991);
        hashMap1.put("Korea", 1992);

        assertEquals(hashMap1.get("Russia"), 1991);
        assertEquals(hashMap1.get("Korea"), 1992);

        hashMap1.put("Russia", 1999);
        hashMap1.put("Korea", 1921);

        assertEquals(hashMap1.get("Russia"), 1999);
        assertEquals(hashMap1.get("Korea"), 1921);

        assertEquals(hashMap1.size(), 2);
    }

    @Test(expected = NoSuchElementException.class)
    public void assertHashMap2() {
        hashMap2.put("Russia", 1991);
        hashMap2.put("Korea", 1992);

        assertEquals(hashMap2.get("Russia"), 1991);
        assertEquals(hashMap2.get("Korea"), 1992);

        hashMap2.remove("Russia");
        hashMap2.remove("Korea");

        assertEquals(hashMap2.size(), 0);

        assertNull(hashMap2.get("Russia"));
    }

    @Test
    public void assertHashMap3() {
        hashMap3.put("Russia", 1991);
        hashMap3.put("Korea", 1992);

        assertTrue(hashMap3.contains("Russia"));
        hashMap2.remove("Russia");
        assertFalse(hashMap3.contains("Russia"));

        assertEquals(hashMap3.size(), 1);


    }

}
