package ru.job4j.ood.dip;

import java.util.HashMap;
import java.util.Map;

public class LibraryService {
    private Book book;
    private Map<Integer, String> books = new HashMap<>();

    public LibraryService(Book book) {
        this.book = book;
    }

    public void addBook(Book newBook) {
        books.put(newBook.getId(), newBook.getName());
    }
}
