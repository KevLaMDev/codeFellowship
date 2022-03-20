package com.kevDev.codeFellowship.controller;

import com.kevDev.codeFellowship.model.AppUser;
import com.kevDev.codeFellowship.model.Post;
import com.kevDev.codeFellowship.repositories.AppUserRepository;
import com.kevDev.codeFellowship.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class PostController {
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    PostRepository postRepository;

    @PostMapping("/newpost")
    public RedirectView addPost(Principal p, String postBody) {
        AppUser currentUser = (AppUser) appUserRepository.findByUsername(p.getName());
        Post postToAdd = new Post();
        postToAdd.setBody(postBody);
        Date date = new Date();
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);
        postToAdd.setCreatedAt(timestamp);
        postToAdd.setPostsOfUser(currentUser);
        postRepository.save(postToAdd);
        return new RedirectView("/myprofile");
    }
}
