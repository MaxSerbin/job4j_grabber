package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {

    public static final int DIRECTORY = 1;
    public static final int LOAD_CACHE = 2;
    public static final int GET_CACHE = 3;

    public static final String SELECT = "Выберите меню";
    public static final String DIR = "Укажите директорию";
    public static final String NAME = "Укажите имя файла";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
                Введите 1, чтобы выбрать директорию.
                Введите 2, чтобы загрузить содержимое файла в кэш.
                Введите 3, чтобы получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        AbstractCache<String, String> cache = null;
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (DIRECTORY == userChoice) {
                System.out.println(DIR);
                String dir = scanner.nextLine();
                cache = new DirFileCache(dir);
            } else if (LOAD_CACHE == userChoice) {
                System.out.println(NAME);
                String key = scanner.nextLine();
                String value = cache.get(key);
                cache.put(key, value);
            } else if (GET_CACHE == userChoice) {
                System.out.println(NAME);
                String key = scanner.nextLine();
                System.out.println(cache.get(key));
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
}
