package edu.poly.asmjava5.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name ="hoa_don")
public class hoaDonEntity {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "khach_hang_id")
    private Long khachhangid; 
     @Column(name = "ngay_lap")
    private String ngaylap;
    @Column(name = "tong_tien", precision = 10, scale = 2)
    private BigDecimal tongtien;
    @Column(name = "nhan_vien_id")
    private Long nhanvienid;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nhan_vien_id", insertable = false, updatable = false)
    private UsersEntity nhanVien;
}
