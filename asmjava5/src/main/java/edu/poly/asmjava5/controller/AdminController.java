package edu.poly.asmjava5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.asmjava5.dao.ProductDAO;
import edu.poly.asmjava5.dao.UserDAO;
import edu.poly.asmjava5.dao.phanLoaiDAO;
import edu.poly.asmjava5.dao.hoaDonDAO;
// import edu.poly.asmjava5.entity.ProductEntity;
import edu.poly.asmjava5.entity.UsersEntity;
import edu.poly.asmjava5.entity.phanLoaiEntity;
import edu.poly.asmjava5.entity.hoaDonEntity;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
      @Autowired
    private UserDAO userDAO; 
    @Autowired
    private phanLoaiDAO phanloaiDAO; 
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    HttpServletRequest request;
    @Autowired
    private hoaDonDAO hoadonDAO;

    
    @GetMapping("/index")
    public String admin(Model model) {
        // Lấy session từ HttpServletRequest
        UsersEntity user = (UsersEntity) request.getSession().getAttribute("user");
        if (user == null || !user.isRole()) {
            return "redirect:/"; 
            
        }

        long totalUsers = userDAO.tongNhanVien();
        model.addAttribute("totalUsers", totalUsers);
        long tongSanPhams = productDAO.tongSanPham();   
        model.addAttribute("tongSanPhams", tongSanPhams);  
        long tonghoaDons = hoadonDAO.tonghoaDon();   
        model.addAttribute("tonghoaDons", tonghoaDons);
        return "admin/HomeAdmin";
    }
    @GetMapping("/qlPhanLoaiHang")
        public String qlPhanLoaiHang(Model model) {
            List<phanLoaiEntity> phanloai = phanloaiDAO.findAll();
            model.addAttribute("phanloais", phanloai);
            return "admin/qlPhanLoaiHang";
        }
        @PostMapping("/create")
public String create(@ModelAttribute phanLoaiEntity phanloai, Model model) {
    try {
        System.out.println("Creating user: " + phanloai);
        phanloaiDAO.save(phanloai);
        return "redirect:/admin/qlPhanLoaiHang";
    } catch (Exception e) {
        e.printStackTrace(); 
        model.addAttribute("clients", phanloaiDAO.findAll());
        model.addAttribute("user", phanloai);
        return "admin/qlPhanLoaiHang"; 
    }
}

    @GetMapping("/qlHoaDon")
    public String qlHoaDon(Model model) {
        List<hoaDonEntity> hoaDon = hoadonDAO.findAll();
        model.addAttribute("hoaDons", hoaDon);
        return "admin/qlHoaDon";
    }
    
}
