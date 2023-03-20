package ru.job4j.ood.lsp.carparking.structure;

public class PassCar implements Car {
    private static final int SIZE = 1;

    @Override
    public int getSize() {
        return SIZE;
    }
}
