package com.controller;

import com.dao.UserDao;
import com.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
public class SecurityController {

    @Resource(name = "userDaoImpl")
    private UserDao userDao;

    @RequestMapping(value = "/login_page")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login_page";
    }

    @RequestMapping(value = "/registration_page")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "registration_page";
    }

    @PostMapping(value = "/registration_page")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        List<User> userExists = userDao.getUserByName(user.getUserName());
        if (!userExists.isEmpty()) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the login provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration_page");
        } else {
            userDao.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration_page");
        }
        return modelAndView;
    }
}
