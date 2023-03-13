package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import java.util.Calendar;
import static org.assertj.core.api.Assertions.*;

class JSONReportTest {

    @Test
    void whenJSONReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee emp1 = new Employee("Ivan", now, now, 100);
        store.add(emp1);
        Report engine = new JSONReport(store);
        String rsl = engine.generate(em -> true);
        StringBuilder exp = new StringBuilder()
                .append("[").append("{").append("\"name\":\"")
                .append(emp1.getName()).append("\",")
                .append("\"hired\":{").append("\"year\":")
                .append(emp1.getHired()
                        .get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(emp1.getHired()
                        .get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(emp1.getHired()
                        .get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(emp1.getHired()
                        .get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(emp1.getHired()
                        .get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(emp1.getHired()
                        .get(Calendar.SECOND)).append("},")
                .append("\"fired\":{").append("\"year\":")
                .append(emp1.getFired()
                        .get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(emp1.getFired()
                        .get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(emp1.getFired()
                        .get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(emp1.getFired()
                        .get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(emp1.getFired()
                        .get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(emp1.getFired()
                        .get(Calendar.SECOND)).append("},")
                .append("\"salary\":").append(emp1.getSalary())
                .append("}")
                .append("]");
        assertThat(rsl).isEqualTo(exp.toString());
    }
}