package com.example.web_pg.dao;

import com.example.web_pg.data.Author;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AuthorDao {
    @Resource(name = "java:app/jdbc/pg_demo")
    private DataSource ds;

    public void add(String name) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("insert into author (name) values (?)")
        ) {
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Author> findAll() {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from author")
        ) {
            List<Author> authors = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                authors.add(new Author(rs.getInt(1), rs.getString(2)));
            }
            return authors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
