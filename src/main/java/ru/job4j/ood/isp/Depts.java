package ru.job4j.ood.isp;

/**
 * Нарушение принципа ISP - т.к. например
 * отдел выдачи заказов это частный случай
 * департаментов и не будет использовать
 * все методы, но будет зависеть.
 */
public interface Depts {

    String getReport();

    boolean addTask();

    boolean acceptOrder();

    boolean giveOrder();
}
