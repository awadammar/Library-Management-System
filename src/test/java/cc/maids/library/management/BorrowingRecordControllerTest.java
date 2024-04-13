package cc.maids.library.management;

import cc.maids.library.management.controller.BorrowingRecordController;
import cc.maids.library.management.entity.Book;
import cc.maids.library.management.entity.BorrowingRecord;
import cc.maids.library.management.entity.Patron;
import cc.maids.library.management.service.BookService;
import cc.maids.library.management.service.BorrowingRecordService;
import cc.maids.library.management.service.PatronService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

@WebMvcTest(BorrowingRecord.class)
class BorrowingRecordControllerTest {

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @Mock
    private BookService bookService;

    @Mock
    private PatronService patronService;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    @Test
    void testBorrowBook() {
        // Arrange
        Long bookId = 1L;
        Long patronId = 1L;
        Book book = new Book(bookId, "Book 1", "Author 1");
        Patron patron = new Patron(patronId, "Ammar Awad");
        doNothing().when(borrowingRecordService).borrowBook(book, patron);

        // Act
        ResponseEntity<Void> response = borrowingRecordController.borrowBook(bookId, patronId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
     void testReturnBook() {
        // Arrange
        Long bookId = 1L;
        Long patronId = 1L;
        Book book = new Book(bookId, "Book 1", "Author 1");
        Patron patron = new Patron(patronId, "Ammar Awad");
        doNothing().when(borrowingRecordService).returnBook(book, patron);

        // Act
        ResponseEntity<Void> response = borrowingRecordController.returnBook(bookId, patronId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
