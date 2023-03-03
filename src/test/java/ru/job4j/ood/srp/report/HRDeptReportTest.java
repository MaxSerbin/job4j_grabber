package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class HRDeptReportTest {

    @Test
    void whenHRReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee emp1 = new Employee("Alla", now, now, 27900);
        Employee emp2 = new Employee("Petr", now, now, 250000);
        Employee emp3 = new Employee("Max", now, now, 45000);
        store.add(emp1);
        store.add(emp2);
        store.add(emp3);
        Report engine = new HRDeptReport(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(emp2.getName()).append(" ")
                .append(emp2.getSalary())
                .append(System.lineSeparator())
                .append(emp3.getName()).append(" ")
                .append(emp3.getSalary())
                .append(System.lineSeparator())
                .append(emp1.getName()).append(" ")
                .append(emp1.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expected.toString());
    }
}