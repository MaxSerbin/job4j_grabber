package ru.job4j.ood.ocp;

/**
 * Нарушение принципов ОСР - т.к.
 * классы зависят от реализации , а
 * не от абстракции.
 */
public class Robot {

    private static class Go {
        public void doIt() {
            System.out.println("Go");
        }
    }

    private static class Stop {
        public void doIt() {
            System.out.println("Stop");
        }
    }

    public static void main(String[] args) {
        Go go = new Go();
        Stop stop = new Stop();
        go.doIt();
        stop.doIt();
    }
}
