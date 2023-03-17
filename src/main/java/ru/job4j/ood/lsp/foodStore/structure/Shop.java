package ru.job4j.ood.lsp.foodStore.structure;

import java.util.Date;

public class Shop extends AbstractStore {

    @Override
    public boolean foodDateConditions(Food food, Date inventDate) {
        int shelfLife = food.getExpDateInPercent(inventDate);
        return shelfLife < 100 && shelfLife >= 25;
    }

    @Override
    public boolean addFood(Food food, Date inventDate) {
        if (!super.addFood(food, inventDate)) {
            return false;
        }
        if (food.getExpDateInPercent(inventDate) > 75) {
            food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount() / 100);
        }
        return true;
    }
}
