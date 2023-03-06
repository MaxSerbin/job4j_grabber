package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.csv.CSVReader;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ITDeptReportTest {

    @Test
    public void whenCSVReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee emp1 = new Employee("Ivan", now, now, 100);
        Employee emp2 = new Employee("Petr", now, now, 150);
        Employee emp3 = new Employee("Max", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(emp1);
        store.add(emp2);
        store.add(emp3);
        String del = ";";
        Report engine = new ITDeptReport(store, parser, "itreport.csv", del);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(emp1.getName()).append(del)
                .append(parser.parse(emp1
                        .getHired())).append(del)
                .append(parser.parse(emp1
                        .getFired())).append(del)
                .append(emp1.getSalary())
                .append(System.lineSeparator())
                .append(emp2.getName()).append(del)
                .append(parser.parse(emp2
                        .getHired())).append(del)
                .append(parser.parse(emp2
                        .getFired())).append(del)
                .append(emp2.getSalary())
                .append(System.lineSeparator())
                .append(emp3.getName()).append(del)
                .append(parser.parse(emp3
                        .getHired())).append(del)
                .append(parser.parse(emp3
                        .getFired())).append(del)
                .append(emp3.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expected.toString());
    }
}