package com.timprogrammiert.OperatingSystem;

import com.timprogrammiert.OperatingSystem.Users.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Author : Tim
 * Date: 03.02.2024
 * Version: 1.0
 */
public class UidManager {
    Map<Integer, User> userByUid;
    private static final int MIN_UID = 100;
    private static final int MAX_UID = 2000;

    public UidManager() {
        this.userByUid = new HashMap<>();
    }

    public Integer generateUid(){
        Random random = new Random();
        Integer uid;
        do {
            uid = random.nextInt(MIN_UID, MAX_UID);
        }while (userByUid.containsKey(uid));
        return uid;
    }
    public void addUserToList(User user){
        userByUid.put(user.getAccountInfo().getUid(), user);
    }
}
