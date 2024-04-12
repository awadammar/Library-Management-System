package cc.maids.library.management.service;

import cc.maids.library.management.entity.Book;
import cc.maids.library.management.exception.DuplicateEntityException;
import cc.maids.library.management.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
    }

    public Book addBook(Book book) {
        // Validate if book with the same ISBN already exists
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new DuplicateEntityException("Book with ISBN " + book.getIsbn() + " already exists.");
        }
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = getBookById(id);

        // Validate if the updated ISBN already exists for another book
        if (!existingBook.getIsbn().equals(updatedBook.getIsbn()) &&
                bookRepository.existsByIsbn(updatedBook.getIsbn())) {
            throw new DuplicateEntityException("Book with ISBN " + updatedBook.getIsbn() + " already exists.");
        }

        // Update book attributes
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPublicationYear(updatedBook.getPublicationYear());
        existingBook.setIsbn(updatedBook.getIsbn());

        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        Book existingBook = getBookById(id);
        bookRepository.delete(existingBook);
    }
}
