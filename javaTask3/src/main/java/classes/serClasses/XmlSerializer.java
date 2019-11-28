package classes.serClasses;

import java.util.Collection;
import java.util.Iterator;


public class XmlSerializer implements SerClassInterface {

    public String appendFront(int span, String name, boolean newRow) {
        StringBuilder string = new StringBuilder();
        if (!newRow) {
            string.append(new String(new char[span]).replace("\0", "    "));
        }
        string.append("<");
        string.append(name);
        string.append(">");
        if (newRow) {
            string.append("\n");
        }
        return string.toString();
    }


    public String appendBack(int span, String name, boolean newRow) {
        StringBuilder string = new StringBuilder();
        if (newRow) {
            string.append(new String(new char[span]).replace("\0", "    "));
        }
        string.append("</");
        string.append(name);
        string.append(">");
        if (!newRow) {
            string.append("\n");
        }

        return string.toString();
    }

    public String appendFrontName(int span, Object o){
        return appendFront(span, o.toString(), true);
    }

    public String appendBackName(int span, Object o){
        return appendBack(span, o.toString(), true);
    }

    public String primitiveHandler(Object o) {
        return o.toString();
    }

    public String collectionHandler(int span, Object o) {
        StringBuilder serString = new StringBuilder();
        serString.append("\n");
        span++;

        Iterator iter = ((Collection) o).iterator();
        int i = 1;

        while (iter.hasNext()) {
            serString.append(appendFront(span, Integer.toString(i), false)); // имя открывающего аттрибута
            serString.append(iter.next());
            serString.append(appendBack(span, Integer.toString(i), false)); // имя закрывающего аттрибута
            i++;
        }
        span--;
        serString.append(new String(new char[span]).replace("\0", "    "));
        return serString.toString();

    }
}



