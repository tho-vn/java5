package edu.poly.asmjava5.controller;

import edu.poly.asmjava5.dao.UserDAO;
import edu.poly.asmjava5.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// import java.util.Optional;

@Controller
@RequestMapping("/qlusers")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/index")
    public String index(Model model) {
        List<UsersEntity> users = userDAO.findAll();
        model.addAttribute("clients", users);
        model.addAttribute("user", new UsersEntity()); // Thêm đối tượng rỗng cho form
        return "admin/qlUser"; 
    }

    @PostMapping("/create")
public String create(@ModelAttribute UsersEntity user, Model model) {
    try {
        System.out.println("Creating user: " + user);
        userDAO.save(user);
        return "redirect:/qlusers/index";
    } catch (Exception e) {
        e.printStackTrace(); 
        model.addAttribute("error", "Email đã tồn tại!");
        model.addAttribute("clients", userDAO.findAll());
        model.addAttribute("user", user);
        return "admin/qlUser"; 
    }
}

@PostMapping("/update")
public String update(@ModelAttribute UsersEntity user, Model model) {
    try {
        // Kiểm tra xem user có ID không
        if (user.getId() != null) {
            // Tìm bản ghi hiện có
            UsersEntity existingUser  = userDAO.findById(user.getId()).orElseThrow(() -> new Exception("User  not found"));

            // Cập nhật các trường cần thiết
            existingUser.setEmail(user.getEmail());
            existingUser.setMatkhau(user.getMatkhau());
            existingUser.setUsername(user.getUsername());
            existingUser.setSdt(user.getSdt());

            // Lưu bản ghi đã cập nhật
            userDAO.save(existingUser );
        } else {
            model.addAttribute("errorupdate", "ID không hợp lệ!");
            return "admin/qlUser ";
        }

        return "redirect:/qlusers/index";
    } catch (DataIntegrityViolationException e) {
        model.addAttribute("errorupdate", "Email đã tồn tại!");
        model.addAttribute("clients", userDAO.findAll());
        model.addAttribute("user", user);
        return "admin/qlUser ";
    } catch (Exception e) {
        model.addAttribute("errorupdate", "Lỗi cập nhật: " + e.getMessage());
        model.addAttribute("clients", userDAO.findAll());
        model.addAttribute("user", user);
        return "admin/qlUser ";
    }
} 

@PostMapping("/delete")
public String delete(@RequestParam("email") String email, Model model) {
    try {
        userDAO.deleteByEmail(email);
        model.addAttribute("message", "Xóa người dùng thành công!");
    } catch (Exception e) {
        model.addAttribute("error", "Không thể xóa người dùng!");
    }
    return "redirect:/qlusers/index";
}


@GetMapping("/edit")
public String edit(@RequestParam("email") String email, Model model) {
    UsersEntity user = userDAO.findByEmail(email); // Sử dụng findByEmail

    if (user != null) {
        model.addAttribute("user", user);
        model.addAttribute("clients", userDAO.findAll()); 
        return "admin/qlUserUpdate";
    }
    return "redirect:/qlusers/index";
}


    // @GetMapping("/search")
    // public String searchUser (@RequestParam("sdt") String sdt, Model model) {
    //     List<UsersEntity> clients = userDAO.findBySdtSearch(sdt);
    //     model.addAttribute("clients", clients);
    //     model.addAttribute("user", new UsersEntity());
    //     return "admin/qlUser ";
    // }
}