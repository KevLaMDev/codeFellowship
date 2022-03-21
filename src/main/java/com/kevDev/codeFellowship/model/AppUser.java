package com.kevDev.codeFellowship.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String bio;
    String imageUrl;

    @OneToMany(mappedBy = "postsOfUser", cascade = CascadeType.ALL)
    List<Post> postList;

    @ManyToMany(mappedBy = "usersFollowingMe")
    Set<AppUser> usersWhomIFollow;

    @ManyToMany
    @JoinTable(name = "whomIFollow_to_whoFollowsMe", joinColumns = {@JoinColumn(name="whomIfollow")}, inverseJoinColumns = {@JoinColumn(name="whoFollowMe")})
    Set<AppUser> usersFollowingMe;


    public AppUser() {
        // default constructor
    }

    public AppUser(String username, String password, String firstName, String lastName, LocalDate dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public Set<AppUser> getUsersWhomIFollow() {
        return usersWhomIFollow;
    }

    public void addUsertoUsersFollowingMe(AppUser user) {
        usersFollowingMe.add(user);
    }

    public Set<AppUser> getUsersFollowingMe() {
        return usersFollowingMe;
    }

    public void setUsersFollowingMe(Set<AppUser> usersFollowingMe) {
        this.usersFollowingMe = usersFollowingMe;
    }

    public void setUsersWhoIFollow(Set<AppUser> usersWhomIFollow) {
        this.usersWhomIFollow = usersWhomIFollow;
    }

    public void addUserToUsersWhomIFollow(AppUser userToFollow) {
        usersWhomIFollow.add(userToFollow);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public Post getMostRecentPost() {
        return postList.get(1);
    }
}
