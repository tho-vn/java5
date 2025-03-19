package edu.poly.asmjava5.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.poly.asmjava5.entity.ProductEntity;



public interface ProductDAO extends JpaRepository<ProductEntity, Long>{
 @Query("SELECT COUNT(p) FROM ProductEntity p")  
    long tongSanPham();  

    Optional<ProductEntity> findById(Long id);

    ProductEntity findByproductname(String productname); 
}
