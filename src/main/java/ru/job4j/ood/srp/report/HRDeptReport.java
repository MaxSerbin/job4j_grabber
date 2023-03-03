package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HRDeptReport implements Report {
    private final Store store;

    public HRDeptReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> list = store.findBy(filter);
        list.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        for (Employee emp : list) {
            text.append(emp.getName()).append(" ")
                    .append(emp.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
