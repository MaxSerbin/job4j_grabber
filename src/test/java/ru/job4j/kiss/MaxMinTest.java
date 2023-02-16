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
}