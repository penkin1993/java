package project.save_load_handlers;

import java.util.Arrays;

public class ListSizeHandler {
    private int listSize;

    public ListSizeHandler(int listSize) {
        this.listSize = listSize;
    }

    public Object cut(Object object) {
        try {
            Object[] arrayObject = (Object[]) object;
            Arrays.asList(arrayObject).subList(0, this.listSize);
            return arrayObject;

        } catch (ClassCastException c) {
          return object;
        }
    }
}
