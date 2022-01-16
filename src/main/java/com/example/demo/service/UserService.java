package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User verify(String email, String password){
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }

    public void updateNickname(String nickname, Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(selectUser -> {
            selectUser.setNickname(nickname);
            userRepository.save(selectUser);
        });
    }

    public void updateProfileImg(String imgUrl, Long id){
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(selectUser -> {
            selectUser.setProfile_img(imgUrl);
            userRepository.save(selectUser);
        });
    }
}
