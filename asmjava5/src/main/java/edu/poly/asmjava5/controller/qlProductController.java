package edu.poly.asmjava5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.asmjava5.dao.ProductDAO;
import edu.poly.asmjava5.entity.ProductEntity;


@Controller
@RequestMapping("/qlproduct")
public class qlProductController {
     @Autowired
    private ProductDAO productDAO; 
    
    
    @GetMapping("/index")
    public String adminproduct(Model model) {
        List<ProductEntity> product = productDAO.findAll();
        model.addAttribute("products", product);
        
        return "admin/qlProduct"; 
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("productname") String productname, Model model) {
        ProductEntity product = productDAO.findByproductname(productname);  
    
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("products", productDAO.findAll());  
            return "admin/qlProduct"; 
        }
        return "redirect:/qlproduct/index"; 
    }


    @PostMapping("/create")
public String create(@ModelAttribute ProductEntity product, Model model) {
    try {
        System.out.println("Creating user: " + product);
        productDAO.save(product);
        return "redirect:/qlproduct/index"; 

    } catch (Exception e) {
        model.addAttribute("errorcreate", "Có lỗi xảy ra khi tạo sản phẩm.");
        model.addAttribute("products", productDAO.findAll()); 
        return "admin/qlProduct"; 
    }
}

@PostMapping("/update")
public String update(@ModelAttribute ProductEntity product, Model model) {
    try {
        productDAO.save(product); // Lưu sản phẩm đã cập nhật vào cơ sở dữ liệu
        return "redirect:/qlproduct/index"; // Chuyển hướng về danh sách sản phẩm
    } catch (Exception e) {
        model.addAttribute("error", "Có lỗi xảy ra khi cập nhật sản phẩm.");
        return "admin/qlProduct"; // Trả về trang chỉnh sửa nếu có lỗi
    }
}
}
