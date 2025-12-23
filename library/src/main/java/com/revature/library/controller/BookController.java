package com.revature.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.library.model.Book;
import com.revature.library.service.BookService;
import com.revature.library.exception.BookNotFoundException;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookServ){
        this.bookService = bookServ;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id){
        Optional<Book> book = bookService.findById(id);
        return book.map(value -> ResponseEntity.ok(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody @Valid Book book){
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/{id}/checkout")
    public ResponseEntity<Book> checkoutBook(@PathVariable Long id){
        Book checkedBook = bookService.checkoutBook(id);
        return ResponseEntity.ok(checkedBook);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long id){
        Book returned = bookService.returnBook(id);
        return ResponseEntity.ok(returned);
    }


}
