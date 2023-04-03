package ru.job4j.ood.dip;

import java.util.ArrayList;
import java.util.List;

/**
 * Нарушение принципа DIP - т.к.
 * присутствует зависимость от реализации,
 * а не от абстракции.
 */
public class Library {
    private List<String> book = new ArrayList<>();

    public Library(List<String> book) {
        this.book = book;
    }
}
