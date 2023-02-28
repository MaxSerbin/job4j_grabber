package ru.job4j.ood.srp;

/**
 * Singleton нарушает принцип SRP, т.к.
 * помимо создания экземпляра класса он
 * так же контролирует кол-во экземпляров.
 */
public class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
