package classes.serClasses;

import java.util.Collection;
import java.util.Iterator;


public class JsonSerializer implements SerClassInterface {

    public String appendFront(int span, String name, boolean newRow) {
        StringBuilder string = new StringBuilder();
        if (!newRow) {
            string.append(new String(new char[span]).replace("\0", "    "));
        }
        string.append("\"");
        string.append(name);
        string.append("\": ");
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

        if (!newRow) {
            string.append(",");
            string.append("\n");
        }

        return string.toString();
    }

    public String appendFrontName(int span, Object o) {
        StringBuilder string = new StringBuilder();
        //string.append(new String(new char[span]).replace("\0", "    "));
        string.append("{\n");
        return string.toString();
    }

    public String appendBackName(int span, Object o) {
        StringBuilder string = new StringBuilder();
        string.append(new String(new char[span]).replace("\0", "    "));
        string.append("}");
        //string.append("}\n");
        return string.toString();
    }

    public String primitiveHandler(Object o) {
        return o.toString();
    }

    public String collectionHandler(int span, Object o) {
        StringBuilder serString = new StringBuilder();
        serString.append("[");
        serString.append("\n");
        span++;

        Iterator iter = ((Collection) o).iterator();

        while (iter.hasNext()) {
            serString.append(new String(new char[span]).replace("\0", "    "));
            serString.append("\""); // имя открывающего аттрибута
            serString.append(iter.next());
            serString.append("\"");
            if (iter.hasNext()){
                serString.append(",");
            }
            serString.append("\n"); // имя закрывающего аттрибута
        }
        span--;
        serString.append(new String(new char[span]).replace("\0", "    "));
        serString.append("]");
        return serString.toString();

    }
}

// TODO: JSON
// TODO: Заключительный интерфейс
