package ru.job4j.ood.lsp.carparking.logic;

import ru.job4j.ood.lsp.carparking.structure.Car;
import ru.job4j.ood.lsp.carparking.structure.Parking;
import java.util.ArrayList;
import java.util.List;

public class ParkingAcc implements Parking {
    private int passCount;
    private int truckCount;
    private List<Car> passCars;
    private List<Car> trucks;

    public ParkingAcc(int passCount, int truckCount) {
        this.passCount = passCount;
        this.truckCount = truckCount;
        this.passCars = new ArrayList<>(passCount);
        this.trucks = new ArrayList<>(truckCount);
    }

    @Override
    public boolean carSizeConditions(Car car) {
        int size = car.getSize();
        return passCount >= size || truckCount >= size;
    }

    @Override
    public boolean addCar(Car car) {
      boolean rsl = carSizeConditions(car);
      if (rsl) {
          if (car.getSize() > 1) {
              trucks.add(car);
              truckCount -= car.getSize();
          }
          if (car.getSize() >= 1) {
              passCars.add(car);
              passCount -= car.getSize();
          }
      }
      return rsl;
    }

    @Override
    public List<Car> getCars() {
        List<Car> carsOnParking = new ArrayList<>(passCars);
        carsOnParking.addAll(trucks);
        return carsOnParking;
    }
}
