package edu.poly.asmjava5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import edu.poly.asmjava5.dao.UserDAO;
import edu.poly.asmjava5.entity.UsersEntity;
import jakarta.servlet.http.HttpServletRequest;
@Controller
public class RegisterController {
    @Autowired
    private UserDAO userDAO;
    HttpServletRequest request;
     @GetMapping("/register")
    public String register(Model model) {
        return "home/register";
    }
   @PostMapping("/register") // URL để xử lý đăng ký
    public String register(@RequestParam("username") String username,
                        @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("sdt") String sdt,
                           Model model) {
        // Kiểm tra xem người dùng đã tồn tại chưa
        if (userDAO.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists!");
            return "/home/register"; 
        }

        // Tạo mới người dùng
        UsersEntity user = new UsersEntity();
        user.setUsername(username);
        user.setEmail(email);
        user.setMatkhau(password); 
        user.setSdt(sdt);

        userDAO.save(user); // Lưu người dùng vào cơ sở dữ liệu
        
        model.addAttribute("message", "Registration successful!"); 
        return "redirect:/login"; 
    }
}
