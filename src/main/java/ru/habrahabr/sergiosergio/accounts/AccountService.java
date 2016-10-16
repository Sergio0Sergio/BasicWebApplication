package ru.habrahabr.sergiosergio.accounts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sg on 16.10.16.
 */
public class AccountService {

    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountService(){
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile){
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }
}
