package ru.job4j.kiss;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

class MaxMinTest {
    private List<Integer> values = new ArrayList<>();
    private Comparator<Integer> comp = Comparator.naturalOrder();

    @BeforeEach
    void initEach() {
        values.add(1);
        values.add(2);
        values.add(3);
    }

    @Test
    void whenMax() {
        MaxMin maxmin = new MaxMin();
        int rsl = maxmin.max(values, comp);
        assertThat(rsl).isEqualTo(3);
    }

    @Test
    void whenMin() {
        MaxMin maxmin = new MaxMin();
        int rsl = maxmin.min(values, comp);
        assertThat(rsl).isEqualTo(1);
    }

    @Test
    void whenIsEmptyMin() {
        MaxMin maxmin = new MaxMin();
        values.clear();
        var exp = maxmin.min(values, comp);
        assertThat(exp).isEqualTo(null);
    }

    @Test
    void whenIsEmptyMax() {
        MaxMin maxmin = new MaxMin();
        values.clear();
        var exp = maxmin.min(values, comp);
        assertThat(exp).isEqualTo(null);
    }

    @Test
    void whenDoubleMax() {
        MaxMin maxmin = new MaxMin();
        values.add(3);
        int rsl = maxmin.max(values, comp);
        assertThat(rsl).isEqualTo(3);
    }

    @Test
    void whenDoubleMin() {
        MaxMin maxmin = new MaxMin();
        values.add(1);
        int rsl = maxmin.min(values, comp);
        assertThat(rsl).isEqualTo(1);
    }
}