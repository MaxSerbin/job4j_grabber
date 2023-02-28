package ru.job4j.ood.srp;

/**
 * В этом случае нарушение принципов SRP происходит
 * по причине наличия методов с различным функционалом,
 * необходимо создать различные абстракции
 * с присущим только им функционалом.
 */
public interface Dept {
    public Dept findByPassport(int passport);

    public Dept getSalary(int passport);
}
