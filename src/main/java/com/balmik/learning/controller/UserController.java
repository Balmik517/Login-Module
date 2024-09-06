//package com.balmik.learning.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.balmik.learning.model.User;
//import com.balmik.learning.service.UserService;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/register")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new User());
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute("user") User user) {
//        userService.save(user);
//        return "redirect:/login";
//    }
//
//    @GetMapping("/login")
//    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
//        if (error != null) {
//            model.addAttribute("error", "User not registered. Please register first.");
//        }
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpServletRequest request) {
//        User user = userService.findByUsername(username);
//        if (user == null) {
//            return "redirect:/login?error=true";
//        } else if (userService.checkPassword(password, user.getPassword())) {
//            HttpSession session = request.getSession();
//            session.setAttribute("username", username);
//            return "redirect:/home";
//        } else {
//            model.addAttribute("error", "Invalid username or password");
//            return "login";
//        }
//    }
//
//    @GetMapping("/home")
//    public String home(Model model, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            username = "guest";
//        }
//        model.addAttribute("username", username);
//        return "home";
//    }
//
//    @PostMapping("/logout")
//    public String logout(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        return "redirect:/login";
//    }
//}


package com.balmik.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.balmik.learning.model.User;
import com.balmik.learning.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (userService.usernameExists(user.getUsername())) {
            model.addAttribute("error", "Username already exists.");
            return "register";
        }

        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("error", "Email already exists.");
            return "register";
        }

        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "User not registered. Please register first.");
        }
        return "login";
    }

//    @PostMapping("/login")
//    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpServletRequest request) {
//        User user = userService.findByUsername(username);
//        if (user == null) {
//            return "redirect:/login?error=true";
//        } else if (userService.checkPassword(password, user.getPassword())) {
//            HttpSession session = request.getSession();
//            session.setAttribute("username", username);
//            return "redirect:/home";
//        } else {
//            model.addAttribute("error", "Invalid username or password");
//            return "login";
//        }
//    }
    
    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpServletRequest request) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return "redirect:/login?error=true";
        } else if (userService.checkPassword(password, user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            //System.out.println(user.getRole());
            session.setAttribute("role", user.getRole()); // Set role in the session
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }



//    @GetMapping("/home")
//    public String home(Model model, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            username = "guest";
//        }
//        model.addAttribute("username", username);
//        return "home";
//    }
    
    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role"); // Make sure this is set appropriately
        if (username == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", username);
        model.addAttribute("role", role); // Add role attribute for the JSP
        return "home";
    }



    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}
