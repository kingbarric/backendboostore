package com.bookstore.bookstorebackend.controller;

import com.bookstore.bookstorebackend.entities.*;
import com.bookstore.bookstorebackend.entities.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping(value = "/save")
    public ResponseEntity save(@RequestBody final BookDto book) {
        System.out.println(book);
        Map m = new HashMap();
        if(StringUtils.isEmpty(book.getIsbn())){
            m.put("code",1);
            m.put("message","ISBN can not be null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
        }
        if(StringUtils.isEmpty(book.getTitle())){
            m.put("code",1);
            m.put("message","Title can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
        }
        if(StringUtils.isEmpty(book.getCategory())){
            m.put("code",1);
            m.put("message","Category can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
        }
        if(StringUtils.isEmpty(book.getPublisher())){
            m.put("code",1);
            m.put("message","Publisher can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
        }
        if(StringUtils.isEmpty(book.getQuantityOnHand()+"")){
            m.put("code",1);
            m.put("message","Quantity on hand can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
        }
        try{
            if(booksRepository.findByIsbn(book.getIsbn())!=null){
                m.put("code",1);
                m.put("message","Book with the ISBN Already exist");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(m);
            }
            Books b = new Books();
            b.setCategory(Category.valueOf(book.getCategory()));
            b.setImage(book.getImage());
            b.setIsbn(book.getIsbn());
            b.setPrice(book.getPrice());
            b.setPublisher(Publisher.valueOf(book.getPublisher()));
            b.setQuantityOnHand(book.getQuantityOnHand());
            b.setTitle(book.getTitle());
            booksRepository.save(b);
            m.put("code",0);
            m.put("message","Book created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(m);
        }catch (Exception e){
            e.printStackTrace();
            m.put("code",-1);
            m.put("message","Error occurred creating new book "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(m);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity update(@RequestBody final BookDto book) {
        System.out.println(book);
        Map m = new HashMap();
        if(StringUtils.isEmpty(book.getIsbn())){
            m.put("code",1);
            m.put("message","ISBN can not be null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
        }
        if(StringUtils.isEmpty(book.getTitle())){
            m.put("code",1);
            m.put("message","Title can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
        }
        if(StringUtils.isEmpty(book.getCategory())){
            m.put("code",1);
            m.put("message","Category can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
        }
        if(StringUtils.isEmpty(book.getPublisher())){
            m.put("code",1);
            m.put("message","Publisher can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
        }
        if(StringUtils.isEmpty(book.getQuantityOnHand()+"")){
            m.put("code",1);
            m.put("message","Quantity on hand can not be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
        }
        try{
            if(booksRepository.findByIsbn(book.getIsbn()) ==null){
                m.put("code",1);
                m.put("message","Book with the ISBN does not exist");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(m);
            }
            Books b = booksRepository.findByIsbn(book.getIsbn());
            b.setCategory(Category.valueOf(book.getCategory()));
            b.setImage(book.getImage());
            b.setIsbn(book.getIsbn());
            b.setPrice(book.getPrice());
            b.setPublisher(Publisher.valueOf(book.getPublisher()));
            b.setQuantityOnHand(book.getQuantityOnHand());
            b.setTitle(book.getTitle());
            booksRepository.save(b);
            m.put("code",0);
            m.put("message","Book updated successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(m);
        }catch (Exception e){
            e.printStackTrace();
            m.put("code",-1);
            m.put("message","Error occurred creating new book "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(m);
        }
    }

    @GetMapping(value = "/findall")
    public List<Books> findAll() {
        return booksRepository.findAll();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        Books b = booksRepository.findByIsbn(id);
         booksRepository.delete(b);
        Map m = new HashMap();
        m.put("code",0);
        m.put("message","Book  successfully deleted");
        return ResponseEntity.status(HttpStatus.OK).body(m);
    }

    @PostMapping(value = "/findsearch")
    public List<Books> searchBooks(@RequestBody SearchObject searchObject) {
        System.out.println(searchObject);
        String sql= "SELECT * FROM books where ";
      //  if(!StringUtils.isEmpty(searchObject.getCategory())){
            sql +=" category ='"+searchObject.getCategory()+"' ";
      //  }
      //  if(!StringUtils.isEmpty(searchObject.getPublisher())){
            sql +=" OR publisher ='"+searchObject.getPublisher()+"' ";
      //  }

     if(!StringUtils.isEmpty(searchObject.getLowerPrice()) && !StringUtils.isEmpty(searchObject.getUpperPrice()) ){
            sql +=" OR   price BETWEEN  "+searchObject.getLowerPrice()+"  AND "+searchObject.getUpperPrice()+" ";
      }
        System.out.println(sql);
        RowMapper<Books> rowMapper = new BeanPropertyRowMapper<>(Books.class);
    return jdbcTemplate.query(sql, rowMapper);
    }

    @GetMapping(value = "/findbyid/{isbn}")
    @Transactional
    public Books findById(@PathVariable String isbn){
        return this.booksRepository.findByIsbn(isbn);
    }

}
