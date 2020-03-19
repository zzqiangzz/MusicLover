package com.jackpeng.lib_baselibrary.utils;

import java.lang.reflect.ParameterizedType;

public class TUtil {
    public static <T> T getT(Object o, int i) {
        try {
            //拿到传入对象带的泛型对象
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            // e.printStackTrace();
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        } catch (ClassCastException e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
        }
        return null;
    }
}
