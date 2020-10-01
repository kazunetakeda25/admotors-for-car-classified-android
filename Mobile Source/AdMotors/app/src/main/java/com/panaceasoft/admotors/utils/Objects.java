package com.panaceasoft.admotors.utils;

public class Objects {
    public static boolean equals(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        }
        return o2 != null && o1.equals(o2);
    }
}