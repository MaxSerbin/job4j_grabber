package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return  getValue(value, (o1, o2) -> comparator.compare(o1, o2) >= 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getValue(value, (o1, o2) -> comparator.compare(o1, o2) <= 0);
    }

    private  <T> T getValue(List<T> value, BiPredicate<T, T> predicate) {
        if (value.isEmpty()) {
            return null;
        }
        var rsl = value.get(0);
        for (var val : value) {
            if (predicate.test(val, rsl)) {
                rsl = val;
            }
        }
       return rsl;
    }
}
