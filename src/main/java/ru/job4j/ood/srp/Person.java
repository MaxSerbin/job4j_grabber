package ru.job4j.ood.srp;

/**
 * В этом классе присутствует нарушение принципов
 * SRP, т.к. совмещены модель и бизнес-логика,
 * в то время как модель должна отвечать
 * только за хранение данных.
 */
public class Person {
    private String name;
    private String surname;
    private int age;
    private int passport;

    public Person getProfession(int passport) {
        return null;
    }
}
