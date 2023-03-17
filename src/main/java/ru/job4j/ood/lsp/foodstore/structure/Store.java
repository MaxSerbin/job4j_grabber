package ru.job4j.ood.lsp.foodstore.structure;

import java.util.Date;
import java.util.List;

public interface Store {

     boolean foodDateConditions(Food food, Date inventDate);

     boolean addFood(Food food, Date inventDate);

     List<Food> getFood();
}
