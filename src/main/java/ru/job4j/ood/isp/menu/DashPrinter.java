package ru.job4j.ood.isp.menu;

public class DashPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            String dash = "----".repeat(Math.max(0, menuItemInfo
                    .getNumber().split("\\.").length - 1));
            System.out.println(dash
                    + menuItemInfo.getNumber()
                    + " "
                    + menuItemInfo.getName());

        }
    }
}
