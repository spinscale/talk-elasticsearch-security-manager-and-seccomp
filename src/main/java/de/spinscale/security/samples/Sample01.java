/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package de.spinscale.security.samples;

import java.lang.reflect.Field;

public class Sample01 {

    // Simple reflection example, accessing a private field
    public static void main(String[] args) throws Exception {
        MyPojo pojo = new MyPojo("my_value");

        Field f = pojo.getClass().getDeclaredField("value");
        f.setAccessible(true);
        String value = (String) f.get(pojo);

        System.out.println("value = " + value + "");
    }
}