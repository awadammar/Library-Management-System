package cc.maids.library.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "borrowing_records")
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Book is required")
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @NotNull(message = "Patron is required")
    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    private Patron patron;

    @NotNull(message = "Borrow date is required")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "borrow_date")
    private Date borrowDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "return_date")
    private Date returnDate;

    public BorrowingRecord(Book book, Patron patron) {
        this.book = book;
        this.patron = patron;
        this.borrowDate = new Date();
    }
}
