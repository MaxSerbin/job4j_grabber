package ru.job4j.ood.lsp;

/**
 * Нарушены принципы LSP, т.к.
 * в подклассе отсутствует условие из
 * базового класса.
 */
public class Profession {
    boolean isIT;

    public Profession(boolean isIT) {
        this.isIT = isIT;
    }
}

class EmployeeIT {
    protected Profession profession;

    public EmployeeIT(Profession profession) {
        this.profession = profession;
    }

    protected void check(Profession profession) {
        if (!profession.isIT) {
            throw new IllegalArgumentException("Achtung!");
        }
        System.out.println("OK");
    }

    public void getProf(Profession profession) {
        check(profession);
        System.out.println(profession);
    }
}

class EmployeeITAnotherDept extends EmployeeIT {

    public EmployeeITAnotherDept(Profession profession) {
        super(profession);
    }

    @Override
    public void getProf(Profession profession) {
        System.out.println("OK");
    }
}

class Result {
    public static void main(String[] args) {
        Profession profession = new Profession(false);
        EmployeeIT emp1 = new EmployeeITAnotherDept(profession);
        emp1.getProf(profession);
    }
}
