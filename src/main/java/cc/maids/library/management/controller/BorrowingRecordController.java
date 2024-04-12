package cc.maids.library.management.controller;

import cc.maids.library.management.entity.Book;
import cc.maids.library.management.entity.Patron;
import cc.maids.library.management.service.BookService;
import cc.maids.library.management.service.BorrowingRecordService;
import cc.maids.library.management.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrowing-records")
public class BorrowingRecordController {
    @Autowired
    private BorrowingRecordService borrowingRecordService;
    @Autowired
    private BookService bookService;
    @Autowired
    private PatronService patronService;

    @PostMapping("/borrow")
    public ResponseEntity<Void> borrowBook(@RequestParam Long bookId, @RequestParam Long patronId) {
        Book book = bookService.getBookById(bookId);
        Patron patron = patronService.getPatronById(patronId);
        borrowingRecordService.borrowBook(book, patron);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/return")
    public ResponseEntity<Void> returnBook(@RequestParam Long bookId, @RequestParam Long patronId) {
        Book book = bookService.getBookById(bookId);
        Patron patron = patronService.getPatronById(patronId);
        borrowingRecordService.returnBook(book, patron);
        return ResponseEntity.noContent().build();
    }
}
