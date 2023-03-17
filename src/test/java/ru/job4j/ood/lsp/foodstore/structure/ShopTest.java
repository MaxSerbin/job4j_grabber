package ru.job4j.ood.lsp.foodstore.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class ShopTest {
    private Store store;
    private Food food;
    private Date inventDate;

    @BeforeEach
    void init() {
        food = new Food("Meat",
                new GregorianCalendar(2023, Calendar.MARCH, 16).getTime(),
                new GregorianCalendar(2023, Calendar.MARCH, 1).getTime(),
                500,
                10);
        store = new Shop();
    }

    @Test
    void whenConditionsIsTrue() {
        inventDate = new GregorianCalendar(2023, Calendar.MARCH, 12).getTime();
        assertThat(store.foodDateConditions(food, inventDate)).isEqualTo(true);
    }

    @Test
    void whenConditionsIsFalse() {
        inventDate = new GregorianCalendar(2023, Calendar.MARCH, 17).getTime();
        assertThat(store.foodDateConditions(food, inventDate)).isEqualTo(false);
    }

    @Test
    void whenNotChangePrice() {
        inventDate = new GregorianCalendar(2023, Calendar.MARCH, 12).getTime();
        store.addFood(food, inventDate);
        assertThat(store.getFood()).isEqualTo(List.of(food));
        assertThat(food.getPrice()).isEqualTo(500);
    }

    @Test
    void whenChangePrice() {
        inventDate = new GregorianCalendar(2023, Calendar.MARCH, 15).getTime();
        store.addFood(food, inventDate);
        assertThat(store.getFood()).isEqualTo(List.of(food));
        assertThat(food.getPrice()).isEqualTo(450);
    }
}