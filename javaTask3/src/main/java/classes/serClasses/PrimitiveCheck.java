package classes.serClasses;

import java.util.*;


class PrimitiveCheck {
    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    static boolean isWrapperType(Class<?> clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes() {
        return Set.of(Boolean.class, Character.class, Byte.class, Short.class, Integer.class,
                Long.class, Float.class, Double.class, Void.class, String.class);
    }
}