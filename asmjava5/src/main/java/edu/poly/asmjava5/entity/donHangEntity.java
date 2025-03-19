package edu.poly.asmjava5.entity;

import java.math.BigDecimal;

// import java.math.BigDecimal;

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
@Table(name ="don_hang")
public class donHangEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "khach_hang_id")
    private Long khachhangid; 
     @Column(name = "ngay_dat")
    private String ngaydat;
    @Column(name = "tong_tien", precision = 10, scale = 2)
    private BigDecimal tongtien;
}
