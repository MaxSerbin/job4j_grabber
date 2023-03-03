package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.csv.CSVReader;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.function.Predicate;

public class ITDeptReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final String file;

    public ITDeptReport(Store store,
                        DateTimeParser<Calendar> dateTimeParser,
                        String file) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.file = file;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
      try (PrintWriter pw = new PrintWriter(file)) {
          StringBuilder text = new StringBuilder();
          text.append("Name; Hired; Fired; Salary;")
                  .append(System.lineSeparator());
          for (Employee employee : store.findBy(filter)) {
              text.append(employee.getName()).append(" ")
                      .append(dateTimeParser.parse(employee
                              .getHired())).append(" ")
                      .append(dateTimeParser.parse(employee
                              .getFired())).append(" ")
                      .append(employee.getSalary())
                      .append(System.lineSeparator());
              pw.write(text.toString());
              pw.close();
          }
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      }
        CSVReader reader = new CSVReader();
      return reader.readCSV(file);
    }
}
