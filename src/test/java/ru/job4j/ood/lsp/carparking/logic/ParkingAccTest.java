package ru.job4j.ood.lsp.carparking.logic;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.carparking.structure.Car;
import ru.job4j.ood.lsp.carparking.structure.Parking;
import ru.job4j.ood.lsp.carparking.structure.PassCar;
import ru.job4j.ood.lsp.carparking.structure.Truck;

import static org.assertj.core.api.Assertions.*;

class ParkingAccTest {

    @Test
    void whenPassCarsAndTruckOkParking() {
        Parking pa = new ParkingAcc(2, 4);
        Car pass1 = new PassCar();
        Car pass2 = new PassCar();
        Car truck1 = new Truck(2);
        Car truck2 = new Truck(2);
        assertThat(pa.addCar(pass1)).isTrue();
        assertThat(pa.addCar(pass2)).isTrue();
        assertThat(pa.addCar(truck1)).isTrue();
        assertThat(pa.addCar(truck2)).isTrue();
    }

    @Test
    void whenPassCarsAndTruckOnPassParking() {
        Parking pa = new ParkingAcc(4, 1);
        Car pass1 = new PassCar();
        Car pass2 = new PassCar();
        Car truck1 = new Truck(2);
        assertThat(pa.addCar(pass1)).isTrue();
        assertThat(pa.addCar(pass2)).isTrue();
        assertThat(pa.addCar(truck1)).isTrue();
    }

    @Test
    void whenTruckNotParking() {
        Parking pa = new ParkingAcc(1, 0);
        Car truck1 = new Truck(2);
        assertThat(pa.addCar(truck1)).isFalse();

    }

    @Test
    void whenGetList() {
        Parking pa = new ParkingAcc(2, 4);
        Car pass1 = new PassCar();
        Car pass2 = new PassCar();
        Car truck1 = new Truck(2);
        Car truck2 = new Truck(2);
        pa.addCar(pass1);
        pa.addCar(pass2);
        pa.addCar(truck1);
        pa.addCar(truck2);
        assertThat(pa.getCars()).contains(pass1, pass2, truck1, truck2);

    }
}