package com.timprogrammiert.OperatingSystem;

import com.timprogrammiert.OperatingSystem.Groups.Group;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Author : Tim
 * Date: 03.02.2024
 * Version: 1.0
 */
public class GidManager {
    Map<Integer, Group> groupsByGid;
    private static final int MIN_UID = 100;
    private static final int MAX_UID = 2000;

    public GidManager() {
        this.groupsByGid = new HashMap<>();
    }

    public Integer generateUid(){
        Random random = new Random();
        Integer uid;
        do {
            uid = random.nextInt(MIN_UID, MAX_UID);
        }while (groupsByGid.containsKey(uid));
        return uid;
    }
    public void addGroupToList(Group group){
        groupsByGid.put(group.getGroupInfo().getGuid(), group);
    }
}
