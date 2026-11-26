package com.example.ecom_project.controller;

import com.example.ecom_project.model.Product;
import com.example.ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin    //connects frontend to backend  n
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;

//    @GetMapping("/")
//    public String greet(){
//        return "Hello";
//    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts(){  //to send the data along with response
        List l =service.getAllProducts();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product p= service.getProductById(id);
        if(p!=null)
        return new ResponseEntity<>(p,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    } // data is sent to server in parts
    @PostMapping("/Product")
    public ResponseEntity<?>addProduct(@RequestPart Product p,
                                       @RequestPart MultipartFile imageFile){  //image will be received in file
        try {
            Product p1 = service.addProdudct(p, imageFile);
            return new  ResponseEntity<>(p1,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }//                              to show the msg

    }
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product p,@RequestPart MultipartFile imageFile) throws IOException {
        Product p1= null;
        try {
             p1 = service.updateProduct(id, p, imageFile);
        }catch(Exception e){
            new ResponseEntity<>("Failde to Update",HttpStatus.BAD_REQUEST);
        }

        if(p1!=null){
         return new ResponseEntity<>("Updated",HttpStatus.OK);
        }else

           return new ResponseEntity<>("Failde to Update",HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/Product/{id}")
    public ResponseEntity<String>deleteProduct(@PathVariable int id){
     Product p =service.getProductById(id);
     if(p!=null){
         service.deleteProduct(id);
         return new ResponseEntity<>("Deleted",HttpStatus.OK);

     }else{
         return new ResponseEntity<>("Product not Found",HttpStatus.NOT_FOUND);

     }
    }
    @GetMapping("/Product/Search")
    public ResponseEntity<List<Product>>searchProduct( @RequestParam String keyword){
        List<Product> p =service.searchProduct(keyword);
        return new ResponseEntity<>(p,HttpStatus.OK);


    }




}
