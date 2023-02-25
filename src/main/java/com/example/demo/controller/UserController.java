package com.example.demo.controller;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("users",userService.getAll());
        return "users";
    }

    @GetMapping("/addUser")
    public String add(Model model){
        model.addAttribute("user",new User());
        return "addUser";
    }
    @PostMapping("/saveUser")
    public String save(@ModelAttribute("user") User user){
        userService.save(user,user.getRoleName());
        return "redirect:users";
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request, Model model){
        Principal principal = request.getUserPrincipal();
        model.addAttribute("user",userService.getUserByUsername(principal.getName()));
        return "profile";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")Long id, Model model){
        User user = userService.getById(id);
        model.addAttribute("user",user);
        return "updateUser";
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.GET})
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user){
        //userRepository.saveAndFlush(user);
       userService.update(id,user,user.getRoleName());
        return "redirect:users";
    }

    @RequestMapping (value = "/delete/{id}", method= {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable("id") Long id){
//        User user = userService.getById(id);
        userRepository.deleteById(id);
        return "redirect:/users/users";
    }
}
