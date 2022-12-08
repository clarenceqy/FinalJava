package com.tandon.DAO.Service;


import com.tandon.DAO.POJOs.UserSession;

import java.util.List;

public interface UserSessionService {
    void add(UserSession user);

    List<UserSession> getList();

    UserSession findByUsernameAndPassword(String name, String password);

    UserSession findByUsername(String name);
}