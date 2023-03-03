package ru.job4j.ood.srp.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public String readCSV(String file) {
        List<List<String>> record = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(file))) {
            while (sc.hasNextLine()) {
                record.add(getLine(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return record.toString();
    }

    private List<String> getLine(String line) {
        List<String> lines = new ArrayList();
        try (Scanner sc = new Scanner(line)) {
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                lines.add(sc.next());
            }
        }
        return lines;
    }
}
