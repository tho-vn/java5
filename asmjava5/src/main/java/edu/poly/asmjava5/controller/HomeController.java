package edu.poly.asmjava5.controller;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import edu.poly.asmjava5.dao.ProductDAO;
// import edu.poly.asmjava5.dao.UserDAO;
// import edu.poly.asmjava5.entity.ProductEntity;
// import edu.poly.asmjava5.entity.UsersEntity;

import jakarta.servlet.http.HttpServletRequest;
// import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class HomeController {
    // @Autowired
    // private UserDAO userDAO; 
    // @Autowired
    // private ProductDAO productDAO;
    HttpServletRequest request;
    @GetMapping("/")
    public String index(Model model) {
        return "home/index"; 
    }
    @GetMapping("/profile")
    public String profile(Model model) {
        return "home/profile";
    }

    @GetMapping("/changepassword")
    public String changepassword(Model model) {
        return "home/changepassword";
    }
    // @PostMapping("/changepassword")
    // public String changePassword(@RequestParam("oldPassword") String oldPassword,
    // @RequestParam("newPassword") String newPassword,
    // @RequestParam("confirmPassword") String confirmPassword,
    // Model model) {
        
    //     return entity;
    // }
    


}
