package com.example.kemen.services;

import com.example.kemen.entities.User;
import com.example.kemen.repos.UserCrud;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;

@Service
public class CacheImageService {
    @Autowired
    private UserCrud userCrud;
    @Autowired
    private CacheManager cacheManager;

    @Cacheable(cacheNames = "vkImage", key = "#str")
    public String getCacheableImageByUserVkId(String str) {
        System.out.println("finding ...");
        return userCrud.findFirstByVkUserId(str).getVkImageUrl();
    }

    public void updateUsersVkImageDB() throws IOException {
        System.out.println("lets start update");
        for (User user : userCrud.findAll()) {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("https://api.vk.com/method/users.get?v=5.131&fields=photo_50&name_case=Nom&access_token=179b0777179b0777179b0777a317e2b38a1179b179b077776f24854636966ed45b38208&user_ids=" + user.getVkUserId());
            HttpResponse httpresponse = httpclient.execute(httpget);
            Scanner sc = new Scanner(httpresponse.getEntity().getContent());
            if (sc.hasNext()) {
                String str = sc.nextLine();
                JSONObject object = new JSONObject(str);
                JSONArray users = object.getJSONArray("response");
                JSONObject user0 = users.getJSONObject(0);
                String vkImageUrl = user0.getString("photo_50");
                if (!vkImageUrl.equals(user.getVkImageUrl())) {
                    user.setVkImageUrl(vkImageUrl);
                    userCrud.save(user);
                }
            }
        }
    }

    @Scheduled(fixedDelay = 60000L)
    public void clearUsersVkImageCache() {
        cacheManager.getCache("vkImage").clear();
    }
}
