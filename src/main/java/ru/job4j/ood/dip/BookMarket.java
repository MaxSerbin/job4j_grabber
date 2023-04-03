package ru.job4j.ood.dip;

/**
 * Нарушение принципа DIP - т.к.
 * прежде всего есть жесткая зависимость между классами,
 * а так же в классе LibraryService присутствует зависимость
 * от реализации (HashMap), а не от абстракции.
 */
public class BookMarket {
    private LibraryService libraryService;

    public BookMarket(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void addToShelf(Book book) {
        libraryService.addBook(book);
    }
}
