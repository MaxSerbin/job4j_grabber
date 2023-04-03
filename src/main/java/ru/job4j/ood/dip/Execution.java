package ru.job4j.ood.dip;

/**
 * Нарушение принципа DIP - т.к.
 * используется конкретная реализация интерфейса
 * Action, а не абстракция.
 */
public class Execution {
    private Run run;

    public Execution(Run run) {
        this.run = run;
    }

    public void doIt() {
        run.doIt();
    }
}
