package edu.poly.asmjava5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhan_vien")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; 
    @Column(name = "ten_nhan_vien")
    private String username; 
    @Column(name = "email")
    private String email;
    @Column(name = "matkhau")
    private String matkhau;
    @Column(name = "so_dien_thoai")
    private String sdt;
    @Column(name = "dia_chi")
    private String diachi;
    @Column(name = "chucvu", nullable = false) 
    private boolean role =false;
}