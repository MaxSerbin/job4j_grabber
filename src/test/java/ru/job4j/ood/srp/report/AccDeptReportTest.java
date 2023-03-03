package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class AccDeptReportTest {

    @Test
    void whenAccReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee emp1 = new Employee("Ivan", now, now, 100);
        Employee emp2 = new Employee("Petr", now, now, 150);
        Employee emp3 = new Employee("Max", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();
        store.add(emp1);
        store.add(emp2);
        store.add(emp3);
        Report engine = new AccDeptReport(store, parser, converter, Currency.RUB, Currency.USD);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(emp1.getName()).append(" ")
                .append(parser.parse(emp1.getHired())).append(" ")
                .append(parser.parse(emp1.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, emp1.getSalary(), Currency.USD))
                .append(System.lineSeparator())
                .append(emp2.getName()).append(" ")
                .append(parser.parse(emp2.getHired())).append(" ")
                .append(parser.parse(emp2.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, emp2.getSalary(), Currency.USD))
                .append(System.lineSeparator())
                .append(emp3.getName()).append(" ")
                .append(parser.parse(emp3.getHired())).append(" ")
                .append(parser.parse(emp3.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, emp3.getSalary(), Currency.USD))
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expected.toString());
    }
}