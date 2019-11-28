package classes.serClasses;

import java.lang.reflect.Field;
import java.util.Collection;


public class SerClassType {
    private final SerClassInterface serObj;

    public SerClassType(SerClassInterface serObj) {
        this.serObj = serObj;
    }

    public String run(int span, Object o) {
        StringBuilder serString = new StringBuilder();
        if (PrimitiveCheck.isWrapperType(o.getClass())) { // примитив
            return serString.append(serObj.primitiveHandler(o)).toString();

        } else if (o instanceof Collection) { // массив или коллекция
            return serString.append(serObj.collectionHandler(span, o)).toString();
        } else {
            serString.append(serObj.appendFrontName(span, o.getClass().getName()));
            span++;

            Class<?> clazz = o.getClass();
            Field[] declafedFields = clazz.getDeclaredFields();
            for (Field declaredField : declafedFields) {
                declaredField.setAccessible(true);
                try {
                    serString.append(serObj.appendFront(span, declaredField.getName(), false)); // имя открывающего аттрибута
                    serString.append(run(span, declaredField.get(o)));
                    serString.append(serObj.appendBack(span, declaredField.getName(), false)); // имя закрывающего аттрибута

                } catch (IllegalAccessException e) {
                    System.out.println("IllegalAccessException");
                }
            }
            span--;
            serString.append(serObj.appendBackName(span, o.getClass().getName()));
            return serString.toString();

        }
    }
}


