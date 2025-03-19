package edu.poly.asmjava5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import edu.poly.asmjava5.dao.hoaDonDAO; 
import edu.poly.asmjava5.entity.hoaDonEntity;

import java.util.List;

@Controller
public class HoaDonController {

    private final hoaDonDAO hoaDonDAO;

    public HoaDonController(hoaDonDAO hoaDonDAO) {
        this.hoaDonDAO = hoaDonDAO;
    }

    @GetMapping("/hoadon")
    public String hienThiHoaDon(Model model) {
        List<hoaDonEntity> hoaDons = hoaDonDAO.findAll();
        model.addAttribute("hoaDons", hoaDons);
        return "admin/qlHoaDon"; 
    }
}