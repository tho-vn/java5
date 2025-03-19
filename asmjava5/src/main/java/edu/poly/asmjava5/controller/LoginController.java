package edu.poly.asmjava5.controller;

import edu.poly.asmjava5.dao.UserDAO;
import edu.poly.asmjava5.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession; 
@Controller
public class LoginController {

    @Autowired
    private UserDAO userDAO;
    @GetMapping("/login")
    public String login(Model model) {
        return "home/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("sdt") String sdt, 
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        UsersEntity user = userDAO.findBySdt(sdt); 
        
        if (user != null && user.getMatkhau().equals(password)) {
            // Đăng nhập thành công
            session.setAttribute("user", user);
            return "redirect:/"; 
        } else {
            // Đăng nhập thất bại
            model.addAttribute("error", "Invalid phone number or password"); 
            return "home/login"; 
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/"; 
    }
}