package ru.job4j.ood.lsp.foodStore.cQuality;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodStore.structure.*;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void whenDistribute() {
        List<Store> storeList = new ArrayList<>();
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        storeList.add(shop);
        storeList.add(warehouse);
        storeList.add(trash);
        List<Food> foodList = new ArrayList<>();
        Food f1 = new Food("Butter",
                new GregorianCalendar(2023, Calendar.APRIL, 25).getTime(),
                new GregorianCalendar(2023, Calendar.MARCH, 10).getTime(),
                200, 0);
        Food f2 = new Food("Fish",
                new GregorianCalendar(2023, Calendar.MARCH, 30).getTime(),
                new GregorianCalendar(2023, Calendar.MARCH, 1).getTime(),
                500, 0);
        Food f3 = new Food("Bread",
                new GregorianCalendar(2023, Calendar.MARCH, 15).getTime(),
                new GregorianCalendar(2023, Calendar.MARCH, 14).getTime(),
                50, 0);
        Food f4 = new Food("Meat",
                new GregorianCalendar(2023, Calendar.MARCH, 16).getTime(),
                new GregorianCalendar(2023, Calendar.MARCH, 1).getTime(),
                500, 10);
        foodList.add(f1);
        foodList.add(f2);
        foodList.add(f3);
        foodList.add(f4);
        Date inventDate = new GregorianCalendar(2023, Calendar.MARCH, 15).getTime();
        ControlQuality cq = new ControlQuality(storeList, foodList, inventDate);
        cq.toDistribute();
        assertThat(trash.getFood()).isEqualTo(List.of(f3));
        assertThat(shop.getFood()).isEqualTo(List.of(f2, f4));
        assertThat(warehouse.getFood()).isEqualTo(List.of(f1));
        assertThat(f2.getPrice()).isEqualTo(500);
        assertThat(f4.getPrice()).isEqualTo(450);
    }
}