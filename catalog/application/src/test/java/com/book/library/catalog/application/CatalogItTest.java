package com.book.library.catalog.application;

import com.book.library.catalog.application.rest.controller.CatalogController;
import com.book.library.catalog.domain.model.aggregate.Book;
import com.book.library.catalog.domain.repository.BooksCatalogRepository;
import com.book.library.catalog.domain.service.factory.BookFactory;
import com.book.library.infrastructure.adapter.BooksCatalogInMemoryAdapter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static com.jayway.jsonpath.JsonPath.read;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CatalogController.class)
@Import(Configuration.class)
class CatalogItTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BooksCatalogRepository bookCatalog;

    @BeforeEach
    public void setUp() {
        ((BooksCatalogInMemoryAdapter) bookCatalog).removeAllBooks();
    }

    @Test
    void shouldAddBook() throws Exception {

        // Arrange
        String isbn = "12345";
        String title = "Une histoire populaire des Etats unis";
        String author = "Howard Zinn";
        String addBook = """
                {
                    "isbn":"%s",
                    "title": "%s",
                    "authors": [
                        "%s"
                    ]
                }
                """.formatted(isbn, title, author);

        // Act and assert
        MvcResult mvcResult = mockMvc.perform(post("/catalog/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(addBook))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        String bookId = read(content, "$.id");

        Optional<Book> bookOptional = bookCatalog.getBook(bookId);
        Assertions.assertThat(bookOptional).isNotEmpty();

        Book book = bookOptional.get();
        Assertions.assertThat(book.id()).isNotNull();
        Assertions.assertThat(book.title()).isEqualTo(title);
        Assertions.assertThat(book.authors()).containsExactlyInAnyOrder(author);
    }

    @Test
    void shouldListBooks() throws Exception {

        // Arrange
        Book book1 = BookFactory.initialize().withTitle("Les croisades vues par les Arabes").build();
        Book book2 = BookFactory.initialize().withTitle("Stalingrad").build();
        bookCatalog.add(book1);
        bookCatalog.add(book2);

        // Act
        mockMvc.perform(get("/catalog/book")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.books[0].isbn").value(book1.isbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books[0].authors[0]").value(book1.authors().getFirst()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books[0].title").value(book1.title()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books[1].isbn").value(book2.isbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books[1].authors[0]").value(book2.authors().getFirst()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books[1].title").value(book2.title()))
                .andReturn();
    }

    @Test
    void shouldGetBookDetails() throws Exception {

        // Arrange
        Book book = BookFactory.initialize().build();
        bookCatalog.add(book);

        // Act
        mockMvc.perform(get("/catalog/book/" + book.id())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.isbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.authors[0]").value(book.authors().getFirst()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(book.title()))
                .andReturn();

        // Act
        Assertions.assertThat(bookCatalog.getBook(book.id())).contains(book);
    }

    @Test
    void shouldUpdateBook() throws Exception {

        // Arrange
        String title = "L'étrange Défaite";
        String updateBook = """
                {
                    "title": "%s"
                }
                """.formatted(title);

        Book book = BookFactory.initialize().build();
        bookCatalog.add(book);

        // Act and assert
        mockMvc.perform(put("/catalog/book/" + book.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateBook))
                .andExpect(status().isNoContent());


        Book bookAfterUpdate = bookCatalog.getBook(book.id()).get();
        Assertions.assertThat(bookAfterUpdate.title()).isEqualTo(title);

    }

    @Test
    void shouldDeleteBook() throws Exception {

        // Arrange
        Book book = BookFactory.initialize().build();
        bookCatalog.add(book);

        // Act
        mockMvc.perform(delete("/catalog/book/" + book.isbn())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        //
        Assertions.assertThat(bookCatalog.getBook(book.isbn())).isEmpty();
    }

}
