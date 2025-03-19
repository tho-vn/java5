package edu.poly.asmjava5.dao;
import org.springframework.data.jpa.repository.JpaRepository;


import edu.poly.asmjava5.entity.phanLoaiEntity;
public interface phanLoaiDAO extends  JpaRepository<phanLoaiEntity, String>{
    
}
