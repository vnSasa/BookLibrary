package com.library.rest;

import com.library.model.Book;
import com.library.model.User;
import com.library.service.BookService;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/books/")
public class BookRestController {

    private final BookService bookService;

    private final UserService userService;

    @Autowired
    public BookRestController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long bookId) {
        if (bookId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Book> userOptional = bookService.getById(bookId);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Book> saveBook(@RequestBody @Valid Book book) {
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookService.save(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Book> updateBook(@RequestBody @Valid Book book) {
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookService.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<User> deleteBook(@PathVariable("id") Long id) {
        Optional<Book> bookOptional = bookService.getById(id);
        if (bookOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAll();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping(value = "{bookId}/take/{userId}")
    public ResponseEntity<Book> takeBook(@PathVariable("bookId") Long bookId,
                                         @PathVariable("userId") Long userId) {
        if (bookId == null || userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Book> bookOptional = bookService.getById(bookId);
        Optional<User> userOptional = userService.getById(userId);

        if (bookOptional.isEmpty() || userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Book book = bookOptional.get();
        if (book.getUser() != null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        book.setUser(userOptional.get());
        bookService.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping(value = "{bookId}/return/ ")
    public ResponseEntity<Book> setUser(@PathVariable("bookId") Long bookId) {
        if (bookId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Book> bookOptional = bookService.getById(bookId);
        if (bookOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Book book = bookOptional.get();
        book.setUser(null);
        bookService.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
