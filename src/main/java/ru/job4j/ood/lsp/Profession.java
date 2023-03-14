package ru.job4j.ood.lsp;

/**
 * Нарушены принципы LSP, т.к.
 * в подклассе отсутствует условие из
 * базового класса.
 */
public class Profession {
    private boolean isIT;

    public Profession(boolean isIT) {
        this.isIT = isIT;
    }

    public boolean getIsIT() {
        return isIT;
    }
}

class EmployeeIT {
    private Profession profession;

    public EmployeeIT(Profession profession) {
        this.profession = profession;
    }

    protected void check(Profession profession) {
        if (!profession.getIsIT()) {
            throw new IllegalArgumentException("Achtung!");
        }
        System.out.println("Done");
    }

    public Profession getProf(Profession profession) {
        check(profession);
        System.out.println("OK");
        return profession;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}

class EmployeeITAnotherDept extends EmployeeIT {

    public EmployeeITAnotherDept(Profession profession) {
        super(profession);
    }

    @Override
    public Profession getProf(Profession profession) {
        System.out.println("OK");
        return profession;
    }

    @Override
    public Profession getProfession() {
        return super.getProfession();
    }

    @Override
    public void setProfession(Profession profession) {
        super.setProfession(profession);
    }
}

class Result {
    public static void main(String[] args) {
        Profession profession = new Profession(false);
        EmployeeIT emp1 = new EmployeeITAnotherDept(profession);
        emp1.getProf(profession);
    }
}
