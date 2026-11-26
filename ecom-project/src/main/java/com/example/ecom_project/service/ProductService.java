package com.example.ecom_project.service;

import com.example.ecom_project.model.Product;
import com.example.ecom_project.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;
    public List<Product> getAllProducts() {
        return repo.findAll();

    }

    public Product getProductById(int id) {
       return repo.findById(id).orElse(null);
    }

    public Product addProdudct(Product p, MultipartFile imageFile) throws IOException {
        //convert image into bytes
        //image name
        //image type

        p.setImageName(imageFile.getOriginalFilename());
        p.setImageType(imageFile.getContentType());
        p.setImageData(imageFile.getBytes());
        return repo.save(p);

    }

    public Product updateProduct(int id, Product p, MultipartFile imageFile) throws IOException {

        p.setImageData(imageFile.getBytes());
        p.setImageName(imageFile.getOriginalFilename());
        p.setImageType(imageFile.getContentType());

       return repo.save(p);
    }


    public void deleteProduct(int id) {
        Product p=getProductById(id);
        repo.delete(p);
    }

    public List<Product> searchProduct(String keyword) {
        return repo.searchProducts(keyword);
    }
}
