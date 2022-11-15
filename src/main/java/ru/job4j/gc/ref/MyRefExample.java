package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;

public class MyRefExample {

    private static void safeSoft() {
        SoftReference<String> soft = new SoftReference<>("Hello!");
        String strong = soft.get();
        System.gc();
        if (strong != null) {
            System.out.println(strong);
        } else {
            System.out.println("Reference soft is null.");
        }
    }

    private static void safeWeak() {
        WeakReference<List<Integer>> weak =
                new WeakReference<>(Arrays.asList(1, 2, 3));
        List<Integer> strong = weak.get();
        if (strong != null) {
            System.out.println(strong);
        } else {
            System.out.println("Reference weak is null.");
        }
    }

    public static void main(String[] args) {
        safeSoft();
        safeWeak();
    }
}
