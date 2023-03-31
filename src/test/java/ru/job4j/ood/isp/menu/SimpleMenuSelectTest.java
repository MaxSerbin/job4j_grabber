package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class SimpleMenuSelectTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    void whenSelect() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(menu.select("Сходить в магазин").get())
                .isEqualTo(new Menu.MenuItemInfo("Сходить в магазин",
                        List.of("Купить продукты"), STUB_ACTION, "1."));
    }
}