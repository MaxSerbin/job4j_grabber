package ru.job4j.ood.lsp.foodstore.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.assertj.core.api.Assertions.*;

class TrashTest {
    private Store store;
    private Food food;
    private Date inventDate;

    @BeforeEach
    void init() {
        store = new Trash();
        food = new Food("Fish",
                new GregorianCalendar(2023, Calendar.MARCH, 25).getTime(),
                new GregorianCalendar(2023, Calendar.MARCH, 5).getTime(),
                200, 0);

    }

    @Test
    void whenConditionsIsTrue() {
        inventDate = new GregorianCalendar(2023, Calendar.MARCH, 25).getTime();
        boolean rsl = store.foodDateConditions(food, inventDate);
        assertThat(rsl).isEqualTo(true);
    }

    @Test
    void whenConditionsIsFalse() {
        inventDate = new GregorianCalendar(2023, Calendar.MARCH, 22).getTime();
        boolean rsl = store.foodDateConditions(food, inventDate);
        assertThat(rsl).isEqualTo(false);
    }
}