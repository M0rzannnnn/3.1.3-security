package ru.vinogradov.kataBoot.conrtoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import ru.vinogradov.kataBoot.model.User;

import ru.vinogradov.kataBoot.service.UserService;
import ru.vinogradov.kataBoot.service.UserServiceImp;


import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService = new UserServiceImp();

    @GetMapping(value = "/")
    public String printUsers (ModelMap model) {
//        userService.add(new User("Kristina", "Semennikova", 31L));
//        userService.add(new User("Polina", "Tikhova", 23L));
//        userService.add(new User("Vadim", "Vinogradov", 32L));
//        userService.add(new User("Vladimir", "Semennikov", 34L));
        List<User> listOfUsers = userService.getAll();
        model.addAttribute("listOfUsers", listOfUsers);
        return "Users";
    }

    @GetMapping("/addNewUser")
    public String addNewUser (ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new-user-info";
    }
    @PostMapping("/")
    public String saveUser (@ModelAttribute ("user") User user) {
        userService.add(user);
        return "redirect:/admin/";
    }

    @GetMapping("/{id}/edit")
    public String editUser (ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.show(id));
        return "/editUser";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute ("user") User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/admin/";
    }
    @DeleteMapping("/{id}")
    public String  deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

}
