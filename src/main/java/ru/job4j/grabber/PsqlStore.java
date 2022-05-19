package ru.job4j.grabber;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {
    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("driver-class-name"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            cnn = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement ps = cnn.prepareStatement(
                "insert into post (title, link, description, created) values (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getLink());
            ps.setString(3, post.getDescription());
            ps.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            ps.execute();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    post.setId(rs.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> rsl = new ArrayList<>();
        Post post;
        try (PreparedStatement ps = cnn.prepareStatement(
                "select * from post")) {
            try (ResultSet rs = ps.executeQuery()) {
                while ((post = create(rs)) != null) {
                    rsl.add(post);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.copyOf(rsl);
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        try (PreparedStatement ps = cnn.prepareStatement(
                "select * from post where id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                post = create(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    private Post create(ResultSet rs) {
        Post post = null;
        try {
            if (rs.next()) {
                post = new Post(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getTimestamp(5).toLocalDateTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    public static void main(String[] args) throws Exception {
        Properties cnf = new Properties();
        try (InputStream is = PsqlStore.class.getClassLoader().
                getResourceAsStream("psql.properties")) {
            cnf.load(is);
        }
        Post one = new Post("AAA", "www.aaa111", "AAAaaa", LocalDateTime.now());
        Post two = new Post("BBB", "www.bbb222", "BBBbbb", LocalDateTime.now());
        Post three = new Post("CCC", "www.ccc333", "CCCccc", LocalDateTime.now());
        try (PsqlStore psqlStore = new PsqlStore(cnf)) {
            psqlStore.save(one);
            psqlStore.save(two);
            psqlStore.save(three);
            System.out.println("All posts :");
            psqlStore.getAll().forEach(System.out::println);
            System.out.println("Find by ID :");
            System.out.println(psqlStore.findById(2));
        }
    }
}
