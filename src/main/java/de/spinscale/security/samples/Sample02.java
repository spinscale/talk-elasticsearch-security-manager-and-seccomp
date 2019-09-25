package de.spinscale.security.samples;

import java.lang.reflect.Field;

public class Sample02 {

    // Reflection example, changing private field
    public static void main(String[] args) throws Exception {
        MyPojo pojo = new MyPojo("my_value");
        System.out.println(pojo);

        Field f = pojo.getClass().getDeclaredField("value");
        f.setAccessible(true);
        f.set(pojo, "my_new_value");

        System.out.println(pojo);
    }

}
