package classes;

import classes.serClasses.SerClassType;


public class ObjecIterator implements Serializer {
    private StringBuilder serString = new StringBuilder();
    private final SerClassType serClass;

    public ObjecIterator(SerClassType serClass) {
        this.serClass = serClass;
    }

    private String getSerString() {
        return serString.toString();
    }

    private void setSerString(String string) {
        serString.append(string);
    }

    public String serialize(Object o) {
        setSerString(this.serClass.run(0, o));
        return getSerString();
    }

}








