package edu.poly.asmjava5.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "san_pham")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long  id; 
    @Column(name = "ten_san_pham")
    private String productname; 
    @Column(name = "mo_ta")
    private String status;
    @Column(name = "gia")
    private Integer price;
    @Column(name = "so_luong")
    private Integer soluong;
    @Column(name = "ngay_tao")
    private LocalDateTime  ngaytao;
    @Column(name = "anh")
    private String anh;
    @Column(name = "phan_loai_id")
    private Integer phanloaiID;
}
