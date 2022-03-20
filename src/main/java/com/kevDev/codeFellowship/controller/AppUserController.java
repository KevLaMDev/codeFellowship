package com.kevDev.codeFellowship.controller;

import com.kevDev.codeFellowship.model.AppUser;
import com.kevDev.codeFellowship.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class AppUserController {
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/")
    public String getLandingPage(Principal p, Model m) {
        String username = null;
        if (p != null) {
            username = p.getName();
        }
        m.addAttribute("username", username);
        return ("landing-page.html");
    }

    @GetMapping("/home")
    public String getHomePage(Principal p, Model m) {
        String username = p.getName();
        AppUser user = (AppUser) appUserRepository.findByUsername(username);
        m.addAttribute("username", user.getUsername());
        m.addAttribute("user", user);
        return ("home-page.html");
    }

    @GetMapping("/login")
    public String getloginPage() { return ("login-page.html");}

    @GetMapping("/signup")
    public String getSignupPage() {
        return ("signup-page.html");
    }

    @PostMapping("/signup")
    public RedirectView createNewUser(String username, String password, String firstName, String lastName, String bio, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate  dateOfBirth, RedirectAttributes redirectAttributes) {
        if (appUserRepository.existsByUsername(username)) {
            redirectAttributes.addFlashAttribute("errorMsg", "Username already taken, please choose a different one.");
            return new RedirectView("/signup");
        }
        // instantiate new AppUser class object passing in input fields
        AppUser newAppUser = new AppUser();
        newAppUser.setUsername(username);
        newAppUser.setFirstName(firstName);
        newAppUser.setLastName(lastName);
        newAppUser.setBio(bio);
        newAppUser.setDateOfBirth(dateOfBirth);
        // hash input password and set hashed password to password property
        String hashedPassword = passwordEncoder.encode(password);
        newAppUser.setPassword(hashedPassword);
        // save new AppUser class object instance in DB
        appUserRepository.save(newAppUser);

        // logins in users after creation of profile
        authWithHttpServletRequest(username, password);
        return new RedirectView("/home");
    }
    

    @PostMapping
    public RedirectView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new RedirectView("/login");
    }



    public void authWithHttpServletRequest (String username, String password)
    {
        try {
            request.login(username, password);
        } catch (ServletException SE) {
            System.out.println("Error: Servlet Exception");
            SE.printStackTrace();
        }
    }
}
