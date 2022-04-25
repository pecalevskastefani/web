package com.example.demo.config;


import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Country;
import com.example.demo.model.enumerations.Category;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataInitializer {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public DataInitializer(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void initData(){
        this.countryService.create("Country1","Continent1");
        this.countryService.create("Country2","Continent2");
        this.countryService.create("Country3","Continent3");
        this.countryService.create("Country4","Continent4");
        this.countryService.create("Country5","Continent5");
        List<Country> countries = this.countryService.listAll();

        this.authorService.create("Author1","Surname1",countries.get(0).getId());
        this.authorService.create("Author2","Surname2",countries.get(1).getId());
        this.authorService.create("Author3","Surname3",countries.get(2).getId());
        this.authorService.create("Author4","Surname4",countries.get(3).getId());
        this.authorService.create("Author5","Surname5",countries.get(4).getId());
        List<Author> authors = this.authorService.listAll();

        this.bookService.create("Book1",Category.DRAMA,authors.get(0).getId(),5);
        this.bookService.create("Book2",Category.THRILLER,authors.get(1).getId(),10);
        this.bookService.create("Book3",Category.BIOGRAPHY,authors.get(2).getId(),2);
        this.bookService.create("Book4",Category.CLASSICS,authors.get(3).getId(),3);
        this.bookService.create("Book5",Category.NOVEL,authors.get(4).getId(),12);
        this.bookService.create("Book6",Category.CLASSICS,authors.get(1).getId(),12);
        this.bookService.create("Book7",Category.DRAMA,authors.get(2).getId(),5);
        this.bookService.create("Book8",Category.BIOGRAPHY,authors.get(3).getId(),0);
        this.bookService.create("Book9",Category.THRILLER,authors.get(4).getId(),2);
        this.bookService.create("Book10",Category.HISTORY,authors.get(1).getId(),9);
        this.bookService.create("Book11",Category.FANTASY,authors.get(0).getId(),1);
        List<Book> books = this.bookService.listAll();


    }
}
