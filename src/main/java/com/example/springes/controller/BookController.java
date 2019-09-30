package com.example.springes.controller;

import com.example.springes.dao.BookDao;
import com.example.springes.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Akash Deep
 **/

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookDao bookDao;

    @GetMapping("/{id}")
    public Map<String,Object> getBookById(@PathVariable String id){
        return bookDao.getBookById(id);
    }

    @PostMapping
    public Book insertBook(@RequestBody Book book) throws Exception{
        return bookDao.insertBook(book);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateBookById(@RequestBody Book book, @PathVariable String id) {
        return bookDao.upadateBookById(id, book);
    }
}
