package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class XMLReportTest {

    @Test
    void whenXMLReportGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Employee emp1 = new Employee("Ivan", now, now, 100);
        store.add(emp1);
        Report engine = new XMLReport(store);
        String rsl = engine.generate(em -> true);
        StringBuilder exp = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employees>\n")
                .append("    <empList>\n")
                .append("        <fired>").append(dateFormat
                        .format(emp1.getHired().getTime())).append("</fired>\n")
                .append("        <hired>").append(dateFormat
                        .format(emp1.getFired().getTime())).append("</hired>\n")
                .append("        <name>").append(emp1.getName()).append("</name>\n")
                .append("        <salary>").append(emp1.getSalary()).append("</salary>\n")
                .append("    </empList>\n")
                .append("</employees>\n");
        assertThat(rsl).isEqualTo(exp.toString());
    }
}