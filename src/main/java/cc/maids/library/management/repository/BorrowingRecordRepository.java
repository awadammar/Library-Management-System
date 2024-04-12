package cc.maids.library.management.repository;


import cc.maids.library.management.entity.Book;
import cc.maids.library.management.entity.BorrowingRecord;
import cc.maids.library.management.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    BorrowingRecord findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);
}
