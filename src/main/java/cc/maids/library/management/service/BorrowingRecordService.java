package cc.maids.library.management.service;

import cc.maids.library.management.entity.Book;
import cc.maids.library.management.entity.BorrowingRecord;
import cc.maids.library.management.entity.Patron;
import cc.maids.library.management.exception.BusinessException;
import cc.maids.library.management.repository.BorrowingRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BorrowingRecordService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Transactional
    public void borrowBook(Book book, Patron patron) {
        // Check if the book is available for borrowing
        if (!book.isAvailableForBorrowing()) {
            throw new BusinessException("Book with ID " + book.getId() + " is not available for borrowing.");
        }

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(new Date());
        borrowingRecordRepository.save(borrowingRecord);

        // Update book availability status
        book.setAvailable(false);
    }

    @Transactional
    public void returnBook(Book book, Patron patron) {
        // Check if the book is borrowed by the patron
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(book, patron);
        if (borrowingRecord == null) {
            throw new BusinessException("Book with ID " + book.getId() + " is not borrowed by patron with ID " + patron.getId());
        }

        // Update return date
        borrowingRecord.setReturnDate(new Date());
        borrowingRecordRepository.save(borrowingRecord);

        // Update book availability status
        book.setAvailable(true);
    }
}