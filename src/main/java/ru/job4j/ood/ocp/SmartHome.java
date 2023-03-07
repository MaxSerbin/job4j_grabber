package ru.job4j.ood.ocp;

/**
 * Нарушение принципа ОСР - для того, чтобы
 * расширить функционал "умного дома" придется добавить
 * методы в класс HomeManagement, а следовательно
 * изменить исходный код.
 */
public class SmartHome {

    private static class HomeManagement {
        public void turnTheLightsOn() {
            String on = "Lights on!";
            System.out.println(on);
        }
    }

    public static void main(String[] args) {
        HomeManagement hm = new HomeManagement();
        hm.turnTheLightsOn();
    }
}
