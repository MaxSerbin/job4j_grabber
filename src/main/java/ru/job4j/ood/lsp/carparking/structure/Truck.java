package ru.job4j.ood.lsp.carparking.structure;

public class Truck implements Car {
    private int size;

    public Truck(int size) {
        validation(size);
        this.size = size;
    }

     void validation(int size) {
         if (size <= 1) {
             throw new IllegalArgumentException("Not a truck !");
         }
     }

    @Override
    public int getSize() {
        return size;
    }
}
