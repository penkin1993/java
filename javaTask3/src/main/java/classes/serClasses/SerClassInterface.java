package classes.serClasses;

public interface SerClassInterface {

    String appendFrontName(int span, Object o);
    String appendBackName(int span, Object o);
    String appendFront(int span, String name, boolean newRow);
    String appendBack(int span, String name, boolean newRow);
    String primitiveHandler(Object o);
    String collectionHandler(int span, Object o);
}
