package com.tandon.DAO.Service;

import com.tandon.DAO.POJOs.UserSession;
import com.tandon.DAO.Repo.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSessionServiceImpl implements UserSessionService {

    @Autowired
    private UserSessionRepository repository;

    @Override
    public void add(UserSession user) {
        repository.save(user);
    }

    @Override
    public List<UserSession> getList() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return repository.findAll(PageRequest.of(0, 10, sort)).getContent();
    }

    @Override
    public UserSession findByUsernameAndPassword(String name, String password) {

        return repository.findUsersByUsernameAndPassword(name, password);
    }

    @Override
    public UserSession findByUsername(String name) {

        return repository.findUsersByUsername(name);
    }

}