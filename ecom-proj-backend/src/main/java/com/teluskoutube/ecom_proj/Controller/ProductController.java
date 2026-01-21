package com.teluskoutube.ecom_proj.Controller;


import com.teluskoutube.ecom_proj.Model.Product;
import com.teluskoutube.ecom_proj.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String greet(){
        return "welcome";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>>getProduct(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable int id){
        Product product = service.getProducts(id);
        if(product != null)
             return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){

        try{
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{Pid}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int Pid){

        System.out.println("Getting image for ID: " + Pid);

        Product product = service.getProducts(Pid);

        if (product == null) {
            System.out.println("Product NOT FOUND for ID = " + Pid);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        byte[] imageFile = product.getImageDate();

        if (imageFile == null) {
            System.out.println("Image is NULL for product ID = " + Pid);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok().body(imageFile);
    }


    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product ,@RequestPart MultipartFile imageFile ){
        Product product1 = null;
        try {
                product1 = service.updateProduct(id, product, imageFile);
           }
           catch (IOException e){
            return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);

           }
        if (product1 != null)
            return new ResponseEntity<>("Successfully Updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product1 = service.getProducts(id);
        if (product1 != null) {
            service.deleteProductById(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts( @RequestParam String keyword){
        List<Product> products = service.searchProducts(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);

    }


}
