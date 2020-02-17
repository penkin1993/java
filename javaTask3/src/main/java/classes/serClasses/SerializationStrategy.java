package classes.serClasses;

public interface SerializationStrategy {
    void appendFrontName(StringBuilder serString, int span, Object o);

    void appendBackName(StringBuilder serString, int span, Object o);

    String appendFront(int span, String name, boolean newRow);

    String appendBack(int span, String name, boolean newRow);

    String primitiveHandler(Object o);

    String collectionHandler(int span, Object o);
}
