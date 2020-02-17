package classes.serClasses;

import classes.Serializer;

import java.lang.reflect.Field;
import java.util.Collection;


public class ReflectionSerializer implements Serializer {
    private final SerializationStrategy serObj;

    public ReflectionSerializer(SerializationStrategy serObj) {
        this.serObj = serObj;
    }

    public String run(int span, Object o) throws IllegalAccessException {
        StringBuilder serString = new StringBuilder();
        if (PrimitiveCheck.isWrapperType(o.getClass())) { // примитив
            return serString.append(serObj.primitiveHandler(o)).toString();
        } else if (o instanceof Collection) { // массив или коллекция
            return serString.append(serObj.collectionHandler(span, o)).toString();
        }
        serObj.appendFrontName(serString, span, o.getClass().getName());
        span = appendForwardBack(o, serString, span);
        serObj.appendBackName(serString, span, o.getClass().getName());
        return serString.toString();

    }

    private int appendForwardBack(Object o, StringBuilder serString, int span) throws IllegalAccessException {
        span++;
        Class<?> clazz = o.getClass();
        Field[] declafedFields = clazz.getDeclaredFields();
        for (Field declaredField : declafedFields) {
            declaredField.setAccessible(true);
            try {
                serString.append(serObj.appendFront(span, declaredField.getName(), false)); // имя открывающего аттрибута
                serString.append(run(span, declaredField.get(o)));
                serString.append(serObj.appendBack(span, declaredField.getName(), false)); // имя закрывающего аттрибута
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("IllegalArgumentException");
            }
        }
        span--;
        return span;
    }


    public String serialize(Object o) throws IllegalAccessException {
        return run(0, o);
    }
}


