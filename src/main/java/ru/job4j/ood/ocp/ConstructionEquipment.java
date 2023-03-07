package ru.job4j.ood.ocp;

import java.util.List;

/**
 * Нарушение принципа ОСР , т.к. невозможно наследование,
 * поскольку наследник, сохраняя состояние родительского класса,
 * не может обладать теми же характеристиками - не является
 * идентичной/родственной сущностью (например самосвал).
 */
public class ConstructionEquipment {

    private static class Excavator {
        public void dig() {
            String execute = "Dig!!!";
            System.out.println(execute);
        }
    }

    public static void main(String[] args) {
        List<Excavator> list = List.of(new Excavator());
        list.forEach(Excavator::dig);
    }
}
