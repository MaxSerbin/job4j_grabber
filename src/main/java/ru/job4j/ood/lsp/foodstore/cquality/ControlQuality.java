package ru.job4j.ood.lsp.foodstore.cquality;

import ru.job4j.ood.lsp.foodstore.structure.Food;
import ru.job4j.ood.lsp.foodstore.structure.Store;

import java.util.Date;
import java.util.List;

public class ControlQuality {
    private List<Store> storeList;
    private List<Food> foodList;
    private Date inventDate;

    public ControlQuality(List<Store> storeList, List<Food> foodList, Date inventDate) {
        this.storeList = storeList;
        this.foodList = foodList;
        this.inventDate = inventDate;
    }

    public void toDistribute() {
        for (Food food : foodList) {
            for (Store store : storeList) {
                if (store.foodDateConditions(food, inventDate)) {
                    store.addFood(food, inventDate);
                }
            }
        }
    }
}
