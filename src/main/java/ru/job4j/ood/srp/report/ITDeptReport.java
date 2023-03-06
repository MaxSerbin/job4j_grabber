package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.csv.CSVReader;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.function.Predicate;

public class ITDeptReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final String file;
    private String delimiter;

    public ITDeptReport(Store store,
                        DateTimeParser<Calendar> dateTimeParser,
                        String file, String delimiter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.file = file;
        this.delimiter = delimiter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(delimiter)
                    .append(dateTimeParser.parse(employee
                            .getHired())).append(delimiter)
                    .append(dateTimeParser.parse(employee
                            .getFired())).append(delimiter)
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.write(text.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}
