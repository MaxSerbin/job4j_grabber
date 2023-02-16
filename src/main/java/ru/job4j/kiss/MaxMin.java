package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getValue(value, comparator.reversed());
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getValue(value, comparator);
    }

    public <T> T getValue(List<T> value, Comparator<T> comparator) {
        value.sort(comparator);
        return value.get(0);
    }
}
