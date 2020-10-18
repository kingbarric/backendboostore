package com.bookstore.bookstorebackend.controller;

import com.bookstore.bookstorebackend.entities.Books;
import com.bookstore.bookstorebackend.entities.SearchObject;
import com.bookstore.bookstorebackend.entities.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity save(@RequestBody final Books book) {
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
            booksRepository.save(book);
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

    @GetMapping(value = "/findall")
    public List<Books> findAll() {
        return booksRepository.findAll();
    }

    @PostMapping(value = "/findsearch")
    public List<Books> searchBooks(@RequestBody SearchObject searchObject) {
        String sql= "SELECT * FROM books where ";
      //  if(!StringUtils.isEmpty(searchObject.getCategory())){
            sql +=" category =' "+searchObject.getCategory()+"' ";
      //  }
      //  if(!StringUtils.isEmpty(searchObject.getPublisher())){
            sql +=" OR publisher =' "+searchObject.getPublisher()+"' ";
      //  }
       // if(!StringUtils.isEmpty(searchObject.getLowerPrice()) && !StringUtils.isEmpty(searchObject.getUpperPrice()) ){
            sql +=" OR  price BETWEEN  "+searchObject.getLowerPrice()+"  AND "+searchObject.getUpperPrice()+" ";
      //  }

        RowMapper<Books> rowMapper = new BeanPropertyRowMapper<>(Books.class);
    return jdbcTemplate.query(sql, rowMapper);



    }

}
