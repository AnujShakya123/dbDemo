package com.example.dbDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String addUser(UserInfo userInfo) {
        userRepository.save(userInfo);

        return "User has been added to the db successfully";
    }

    public UserInfo getUserById(Integer userId) throws Exception {
        Optional<UserInfo> optionalUserInfo = userRepository.findById(userId);
        if (!optionalUserInfo.isPresent()) {
            throw new Exception("User with Id is not present");
        }
        UserInfo userInfo = optionalUserInfo.get();

        return userInfo;
    }

    public String deleteUserById(Integer id){
        if(userRepository.existsById(id)==true){
        userRepository.deleteById(id);
        return "User with id is deleted";
    }else{
        return "UserId was  invalid";
    }
}

public String updateEmail(Integer userId, String newEmail){
        Optional<UserInfo> optionalUserInfo=userRepository.findById(userId);
        UserInfo userInfo=optionalUserInfo.get();
        userInfo.setEmail(newEmail);

        userRepository.save(userInfo);
        return "Email updated successfully";
}
}