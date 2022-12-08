package com.tandon.DAO.Repo;

import com.tandon.DAO.POJOs.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSession, Integer> {
    UserSession findUsersByUsernameAndPassword(String username, String password);

    UserSession findUsersByUsername(String username);
}