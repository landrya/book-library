package com.book.library.catalog.domain.service;

import com.book.library.catalog.domain.service.factory.BookFactory;
import com.book.library.catalog.domain.model.aggregate.Book;
import com.book.library.catalog.domain.repository.BooksCatalogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookManagementServiceTest {

    @InjectMocks
    private BookManagementService bookManagementService;

    @Mock
    private BooksCatalogRepository booksCatalog;

    @Mock
    private IdGenerator idGenerator;


    @Nested
    class AddBook {

        @Test
        void shouldAddBookToCatalog() {

            // Arrange
            String id = UUID.randomUUID().toString();
            when(idGenerator.generateId()).thenReturn(id);
            Book book = BookFactory.initialize().build();

            // Act
            bookManagementService.addBookToCatalog(book);

            // Assert
            verify(booksCatalog).add(book.toBuilder().id(id).build());
        }

    }

    @Nested
    class RemoveBook {

        @Test
        void shouldRemoveBookFromCatalog() {

            // Arrange
            String bookId = UUID.randomUUID().toString();

            // Act
            bookManagementService.removeBookFromCatalog(bookId);

            // Assert
            verify(booksCatalog).remove(bookId);
        }

    }

    @Nested
    class GetBookDetails {

        @Test
        void shouldGetBookDetails() {

            // Arrange
            String isbn = "230300442X";
            Book expectedBook = BookFactory.initialize().withId(isbn).build();
            when(booksCatalog.getBook(isbn)).thenReturn(Optional.of(expectedBook));

            // Act
            Optional<Book> actualBook = bookManagementService.getBookFromCatalog(isbn);

            // Assert
            Assertions.assertThat(actualBook).contains(expectedBook);
        }

    }

    @Nested
    class SearchBookByTitle {

        @Test
        void shouldFindBookByTitle() {

            // Arrange
            String title = "Le jeu de la vie";
            Book expectedBook = BookFactory.initialize().withTitle(title).build();
            when(booksCatalog.searchByTitle(title)).thenReturn(List.of(expectedBook));

            // Act
            List<Book> actualBook = bookManagementService.searchBookByTitle(title);

            // Assert
            Assertions.assertThat(actualBook).containsExactly(expectedBook);
        }

    }

}