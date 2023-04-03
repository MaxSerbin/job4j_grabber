package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {
    public static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");
    public static final int ADD_TO_ROOT = 1;
    public static final int ADD_TO_PARENT = 2;
    public static final int ACTION = 3;
    public static final int SHOW_RSL_MENU = 4;
    public static final int EXIT = 0;
    public static final String MENU = """
            МЕНЮ
            0 Выход.
            1 Добавить элемент в корень меню.
            2 Добавить элемент к родительскому элементу.
            3 Вызвать действие.
            4 Показать меню.
            """;
    private final Menu menu = new SimpleMenu();
    private final MenuPrinter printer = new DashPrinter();

    public void showMenu() {
        System.out.println(MENU);
    }

    public void init() {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            showMenu();
            System.out.println("Введите номер пункта меню : ");
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice == ADD_TO_ROOT) {
                System.out.println("Введите имя корневого элемента : ");
                String rootEl = scanner.nextLine();
                menu.add(Menu.ROOT, rootEl, DEFAULT_ACTION);
            } else if (userChoice == ADD_TO_PARENT) {
                System.out.println("Введите имя дочернего элемента : ");
                String rootSubEl = scanner.nextLine();
                System.out.println("Введите имя корневого элемента : ");
                String rootEl = scanner.nextLine();
                menu.add(rootEl, rootSubEl, DEFAULT_ACTION);
            } else if (userChoice == ACTION) {
                System.out.println("Введите имя элемента : ");
                String actionEl = scanner.nextLine();
                menu.select(actionEl).get().getActionDelegate().delegate();
            } else if (userChoice == SHOW_RSL_MENU) {
                printer.print(menu);
            } else if (userChoice == EXIT) {
                System.out.println("EXIT");
                run = false;
            } else {
                System.out.println("Введите корректный номер пункта меню ! ");
            }
        }
    }

    public static void main(String[] args) {
        TodoApp todoApp = new TodoApp();
        todoApp.init();
    }
}
