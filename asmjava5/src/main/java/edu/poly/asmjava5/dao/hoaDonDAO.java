package edu.poly.asmjava5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.poly.asmjava5.entity.hoaDonEntity;

public interface hoaDonDAO extends JpaRepository<hoaDonEntity,Long> {
      @Query("SELECT COUNT(h) FROM hoaDonEntity h")  
      long tonghoaDon();  
}
