package classes.serClasses;

import java.lang.reflect.Field;
import java.util.Collection;


public class SerClassType {

    private String appendFront(int span, String name, boolean newRow) {
        StringBuilder string = new StringBuilder();
        if (!newRow) {
            string.append(new String(new char[span]).replace("\0", "  "));
        }
        string.append("<");
        string.append(name);
        string.append(">");
        if (newRow) {
            string.append("\n");
        }
        return string.toString();
    }


    private String appendBack(int span, String name, boolean newRow) {
        StringBuilder string = new StringBuilder();
        if (newRow) {
            string.append(new String(new char[span]).replace("\0", "  "));
        }
        string.append("</");
        string.append(name);
        string.append(">");
        if (!newRow) {
            string.append("\n");
        }

        return string.toString();
    }


    public String run(int span, Object o){
        StringBuilder serString = new StringBuilder();

        if (PrimitiveCheck.isWrapperType(o.getClass())){ // примитив
            serString.append(o.toString());
            return serString.toString();
        }
        else if (o instanceof Collection){ // массив или коллекция // TODO
            //serCollection(span, o.getClass().getName(), (Collection) o); // TODO
            return serString.toString(); // TODO
        }
        else {
            serString.append(appendFront(span, o.getClass().getName(), true));
            span ++;

            Class<?> clazz = o.getClass();
            Field[] declafedFields = clazz.getDeclaredFields();
            for (Field declaredField : declafedFields){
                declaredField.setAccessible(true);
                try {
                    serString.append(appendFront(span, declaredField.getName(),false)); // имя открывающего аттрибута
                    serString.append(run(span, declaredField.get(o)));
                    serString.append(appendBack(span, declaredField.getName(), false)); // имя закрывающего аттрибута

                } catch (IllegalAccessException e){
                    System.out.println("IllegalAccessException");
                }
            }
            span --;
            serString.append(appendBack(span, o.getClass().getName(), true));
            return serString.toString();
        }
    }
}




// TODO: Переписать через паттрен !!!
// TODO: проверить с collection !!!
// TODO: JSON

/*
<Person>
    <firstName>Иван<firstName>
    <lastName>Иванов</lastName>
    <address>
        <city>Москва</city>
        <postalCode>101101<postalCode>
    </address>
    <phoneNumbers>
        <1>123-1234-523</1>
        <2>432-23-232-23</2>
    </phoneNumbers>
</Person>

{
   "firstName": "Иван",
   "lastName": "Иванов",
   "address": {
       "city": "Москва",
       "postalCode": "101101"
   },
   "phoneNumbers": [ # массив или коллекция
       "123-1234-523",
       "432-23-232-23"
   ]
}
 */




