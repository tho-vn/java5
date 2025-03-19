  package edu.poly.asmjava5.dao;
  import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
  import org.springframework.data.repository.query.Param;

  import edu.poly.asmjava5.entity.UsersEntity;
  import jakarta.transaction.Transactional;
  public interface UserDAO extends JpaRepository<UsersEntity, Long>{
    @Query("SELECT COUNT(u) FROM UsersEntity u")  
      long tongNhanVien();  

      @Query("SELECT u FROM UsersEntity u WHERE u.sdt = :sdt") // Thay đổi ở đây
      UsersEntity findBySdt(@Param("sdt") String sdt);

      UsersEntity findByUsername(String username);
      @Query("SELECT u FROM UsersEntity u WHERE u.sdt = :sdt")
      List<UsersEntity> findBySdtSearch(@Param("sdt") String sdt);


      UsersEntity findByEmail(String email);
      
      @Transactional
      void deleteByEmail(String email);
      Optional<UsersEntity> findById(Long id);
     
     

  }
