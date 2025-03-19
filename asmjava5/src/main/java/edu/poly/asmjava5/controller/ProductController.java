package edu.poly.asmjava5.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.asmjava5.dao.CartDAO;
import edu.poly.asmjava5.dao.ProductDAO;
import edu.poly.asmjava5.dao.hoaDonDAO;
// import edu.poly.asmjava5.dao.UserDAO;
import edu.poly.asmjava5.entity.ProductEntity;
// import edu.poly.asmjava5.entity.UsersEntity;
// import edu.poly.asmjava5.entity.donHangEntity;
import edu.poly.asmjava5.entity.hoaDonEntity;
import edu.poly.asmjava5.item.CartItem;
import jakarta.servlet.http.HttpSession;

@Controller

public class ProductController {
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private hoaDonDAO hoaDonDAO;
    // @Autowired
    // private UsersEntity user;
    // @Autowired
    // private UserDAO userDAO;

      @GetMapping("/product")
    public String product(Model model) {
        List<ProductEntity> product = productDAO.findAll();
        model.addAttribute("products", product);
        
        return "home/product"; 
    }
    
    @GetMapping("/productdetails/{id}")
    public String productDetail(@PathVariable("id") Long id, Model model) { 
        Optional<ProductEntity> productOptional = productDAO.findById(id);
        if (productOptional.isPresent()) {
            ProductEntity product = productOptional.get();
            model.addAttribute("product", product);
        } else {
            model.addAttribute("error", "Sản phẩm không tồn tại");
        }
        return "home/productdetails"; 
    }
    @GetMapping("/qlProduct")
    public String adminproduct(Model model) {
        List<ProductEntity> product = productDAO.findAll();
        model.addAttribute("products", product);
  
        return "admin/qlProduct"; 
    }
     @PostMapping("/addcart/{id}")
    public String addToCart(@PathVariable("id") Long id, HttpSession session) {
        ProductEntity product = productDAO.findById(id).orElse(null);
        if (product != null) {
            CartDAO cart = (CartDAO) session.getAttribute("cart");
            if (cart == null) {
                cart = new CartDAO();
                session.setAttribute("cart", cart);
            }
            cart.addProduct(product);
        }
        return "redirect:/product"; 
    }
    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        CartDAO cart = (CartDAO) session.getAttribute("cart");
        if (cart != null) {
            model.addAttribute("products", cart.getProducts());
            model.addAttribute("totalPrice", cart.getTotalPrice());
        } else {
            model.addAttribute("message", "Giỏ hàng trống");
        }
        return "home/cart"; 
    }

    @PostMapping("/removecart/{id}")
    public String removeFromCart(@PathVariable("id") Long id, HttpSession session) {
        CartDAO cart = (CartDAO) session.getAttribute("cart");
        if (cart != null) {
            cart.removeProduct(id);
        }
        return "redirect:/cart"; 
    }
    @PostMapping("/updatequantity/{id}")
public String updateQuantity(@PathVariable("id") Long id, @RequestParam("action") String action, HttpSession session) {
    CartDAO cart = (CartDAO) session.getAttribute("cart");
    if (cart != null) {
        if (action.equals("increase")) {
            cart.getProducts().stream()
                .filter(item -> item.getProduct().getId().equals(id))
                .findFirst()
                .ifPresent(CartItem::increaseQuantity);
        } else if (action.equals("decrease")) {
            cart.getProducts().stream()
                .filter(item -> item.getProduct().getId().equals(id))
                .findFirst()
                .ifPresent(item -> {
                    if (item.getQuantity() > 1) {
                        item.decreaseQuantity(); // Giảm số lượng nếu lớn hơn 1
                    } else {
                        cart.removeProduct(id); // Nếu số lượng bằng 1, xóa sản phẩm
                    }
                });
        }
    }
    return "redirect:/cart"; // Chuyển hướng về trang giỏ hàng
}
  @PostMapping("/thanhtoan")
public String thanhtoan(@ModelAttribute hoaDonEntity hoaDon, 
                         @RequestParam String nhanvienid, 
                         @RequestParam String tongtien, 
                         HttpSession session,
                         Model model) {
    try {
        // Lấy ngày hiện tại
       LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String ngaylap = now.format(formatter);
    
       
        BigDecimal tongTienValue = new BigDecimal(tongtien.replace(",", ".")); 
        hoaDon.setTongtien(tongTienValue); 
        hoaDon.setNgaylap(ngaylap);
        Long khachHangIdValue = Long.parseLong(nhanvienid);
        hoaDon.setNhanvienid(khachHangIdValue);
        hoaDonDAO.save(hoaDon);
        CartDAO cart = (CartDAO) session.getAttribute("cart");
        cart.clear();
        return "home/index"; 
    } catch (Exception e) {
        e.printStackTrace();
        return "redirect:/cart";
    }
}
}
