package com.example.kemen.controllers;

import com.example.kemen.entities.Role;
import com.example.kemen.entities.User;
import com.example.kemen.repos.RoleCrud;
import com.example.kemen.services.CacheImageService;
import com.example.kemen.services.UserService;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Scanner;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleCrud roleCrud;
    @Autowired
    private CacheImageService cacheImageService;
    @Value("${user.pagination.count}")
    private String userCount;


    @PostMapping("/create")
    public void createUser(String login, String name, String lastname, String password, String vkUserId) throws IOException {
        String vkImageUrl = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("https://api.vk.com/method/users.get?v=5.131&fields=photo_50&name_case=Nom&access_token=179b0777179b0777179b0777a317e2b38a1179b179b077776f24854636966ed45b38208&user_ids=" + vkUserId);
        HttpResponse httpresponse = httpclient.execute(httpget);
        Scanner sc = new Scanner(httpresponse.getEntity().getContent());

        if (sc.hasNext()) {
            String str = sc.nextLine();
            JSONObject object = new JSONObject(str);
            JSONArray users = object.getJSONArray("response");
            JSONObject user0 = users.getJSONObject(0);
            vkImageUrl = user0.getString("photo_50");
        }
        User user = new User(login, name, lastname, password, vkImageUrl, vkUserId, roleCrud.getAuthorityByRole(Role.ROLE_USER));
        userService.addUser(user);
    }

    @GetMapping("/list")
    public Page<User> getUsers(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page) {
        return userService.getAllUsers(PageRequest.of(page, Integer.parseInt(userCount)));
    }

    @GetMapping("{id}")
    public User userInfo(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

    @GetMapping("{id}/image")
    public String getUserImg(@PathVariable long id) {
        String vkUserId = userService.getUserById(id).getVkUserId();
        if (vkUserId == null) {
            return null;
        }
        return userService.getImageByVkUserId(vkUserId);
    }

    @GetMapping("updateDB")
    public void updateUsersImagesDB() throws IOException {
        cacheImageService.updateUsersVkImageDB();
    }

    @PostMapping("/put/{id}")
    public void userPatch(String login, String name, String lastname, String password, boolean isAdmin, @PathVariable("id") long id) {
        User user = userService.getUserById(id);
        if (login != null) {
            user.setLogin(login);
        }
        if (password != null) {
            user.setPassword(password);
        }
        if (name != null) {
            user.setName(name);
        }
        if (lastname != null) {
            user.setLastName(lastname);
        }
        if (isAdmin) {
            if (!user.isAdmin()) {
                user.addAuthority(roleCrud.getAuthorityByRole(Role.ROLE_ADMIN));
            }
        } else {
            if (user.isAdmin()) {
                user.removeAuthority(roleCrud.getAuthorityByRole(Role.ROLE_ADMIN));
            }
        }
        userService.updateUser(user);
    }

    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
    }
}

