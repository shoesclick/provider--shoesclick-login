package com.shoesclick.provider.spi.site;

import com.shoesclick.provider.model.User;

import java.util.HashMap;

public class SiteUserStore {

    private HashMap<String, User> hashMapStorage;

    public SiteUserStore(){
        this.hashMapStorage = new HashMap<>(3);
        this.hashMapStorage.put("alex", new User("alex", "alex@123"));
        this.hashMapStorage.put("kevin", new User("kevin", "kevin@123"));
    }

    public User getUser(String username){
        return this.hashMapStorage.get(username);
    }
}
