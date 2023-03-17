package ru.job4j.ood.lsp.foodStore.structure;

import java.util.Date;

public class Trash extends AbstractStore {

    @Override
    public boolean foodDateConditions(Food food, Date inventDate) {
        int shelfLife = food.getExpDateInPercent(inventDate);
        return shelfLife >= 100;
    }
}
