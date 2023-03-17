package ru.job4j.ood.lsp.foodstore.structure;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

abstract class AbstractStore implements Store {
    private List<Food> foodList = new ArrayList<>();

    public abstract boolean foodDateConditions(Food food, Date inventDate);

    public boolean addFood(Food food, Date inventDate) {
        if (!foodDateConditions(food, inventDate)) {
            return false;
        }
        foodList.add(food);
        return true;
    }

    public List<Food> getFood() {
        return new ArrayList<>(foodList);
    }
}
