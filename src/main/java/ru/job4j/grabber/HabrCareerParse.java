package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {
    private static final String SOURCE_LINK = "https://career.habr.com";
    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer",
            SOURCE_LINK);
    private static final int PAGE = 5;
    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) {
        List<Post> rsl = new ArrayList<>();
        try {
            for (int i = 1; i <= PAGE; i++) {
                Connection connection = Jsoup.connect(PAGE_LINK + "?page=" + i);
                Document document = connection.get();
                Elements rows = document.select(".vacancy-card__inner");
                rows.forEach(row -> rsl.add(parsing(row)));
            }
        } catch (IOException e) {
                e.printStackTrace();
            }
        return List.copyOf(rsl);
    }

    private Post parsing(Element row) {
        Element titleElement = row.select(".vacancy-card__title").first();
        Element linkElement = titleElement.child(0);
        String vacancyName = titleElement.text();
        String link = String.format("%s%s", SOURCE_LINK,
                linkElement.attr("href"));
        Element dateElement = row.select(".vacancy-card__date").first();
        Element dateChild = dateElement.child(0);
        return new Post(vacancyName, link, retrieveDescription(link),
                dateTimeParser.parse(dateChild.attr("datetime"))
        );
    }

    private static String retrieveDescription(String link) {
        Connection connection = Jsoup.connect(link);
        String desc = null;
        try {
            Document document = connection.get();
            Element descElement = document.select(".style-ugc").first();
            Element descChild = descElement.child(0);
            desc = descChild.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return desc;
    }

    public static void main(String[] args) {
        HabrCareerParse habrCareerParse = new HabrCareerParse(new HabrCareerDateTimeParser());
        System.out.println(habrCareerParse.list(PAGE_LINK));
    }
}
