package ru.job4j.ood.lsp.carparking.structure;

import java.util.List;

public interface Parking {

    boolean carSizeConditions(Car car);

    boolean addCar(Car car);

    List<Car> getCars();

}
