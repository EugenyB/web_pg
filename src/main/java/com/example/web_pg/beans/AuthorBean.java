package com.example.web_pg.beans;

import com.example.web_pg.dao.AuthorDao;
import com.example.web_pg.data.Author;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped

public class AuthorBean implements Serializable {
    @EJB
    private AuthorDao authorDao;

    @Getter @Setter
    private Author author = new Author();

    public void add() {
        authorDao.add(author.getName());
        author = new Author();
    }

    public List<Author> getAuthors() {
        return authorDao.findAll();
    }
}
