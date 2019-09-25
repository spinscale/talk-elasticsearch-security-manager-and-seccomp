package de.spinscale.security.samples;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Sample03 {

    // changing a private static final field, WTF?!
    public static void main(String[] args) throws Exception {
        MyPojo pojo = new MyPojo("my_value");
        System.out.println(pojo);

        Field field = pojo.getClass().getDeclaredField("FOO");
        setStaticFinalField(field, Boolean.FALSE);

        System.out.println(pojo);
    }

    // courtesy Heinz Kabutz https://www.javaspecialists.eu/archive/Issue272.html
    // this requires RuntimePermission accessClassInPackage.sun.misc
    private final static Unsafe UNSAFE;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void setStaticFinalField(Field field, Object value) throws ReflectiveOperationException {
        if (!Modifier.isStatic(field.getModifiers()) || !Modifier.isFinal(field.getModifiers())) {
            throw new IllegalArgumentException("field should be final static");
        }
        Object fieldBase = UNSAFE.staticFieldBase(field);
        long fieldOffset = UNSAFE.staticFieldOffset(field);
        UNSAFE.putObject(fieldBase, fieldOffset, value);
    }
}
