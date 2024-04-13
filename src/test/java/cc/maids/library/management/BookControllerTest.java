package cc.maids.library.management;

import cc.maids.library.management.controller.BookController;
import cc.maids.library.management.entity.Book;
import cc.maids.library.management.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(Book.class)
class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void testGetAllBooks() {
        // Arrange
        List<Book> books = Arrays.asList(
                new Book(1L, "Book 1", "Author 1"),
                new Book(2L, "Book 2", "Author 2")
        );
        when(bookService.getAllBooks()).thenReturn(books);

        // Act
        List<Book> result = bookController.getAllBooks();

        // Assert
        assertEquals(2, result.size());
        assertEquals(books, result);
    }

    @Test
    void testGetBookById() {
        // Arrange
        Long bookId = 1L;
        Book book = new Book(bookId, "Book 1", "Author 1");
        when(bookService.getBookById(bookId)).thenReturn(book);

        // Act
        ResponseEntity<Book> response = bookController.getBookById(bookId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
     void testAddBook() {
        // Arrange
        Book bookToAdd = new Book();
        bookToAdd.setTitle("Book 3");
        bookToAdd.setAuthor("Author 3");
        when(bookService.addBook(any(Book.class))).thenReturn(bookToAdd);

        // Act
        ResponseEntity<Book> response = bookController.addBook(bookToAdd);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(bookToAdd, response.getBody());
    }

    @Test
     void testUpdateBook() {
        // Arrange
        Long bookId = 1L;
        Book updatedBook = new Book(bookId, "Updated Book", "Updated Author");
        when(bookService.updateBook(eq(bookId), any(Book.class))).thenReturn(updatedBook);

        // Act
        ResponseEntity<Book> response = bookController.updateBook(bookId, updatedBook);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBook, response.getBody());
    }

    @Test
     void testDeleteBook() {
        // Arrange
        Long bookId = 1L;
        doNothing().when(bookService).deleteBook(bookId);

        // Act
        ResponseEntity<Void> response = bookController.deleteBook(bookId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
