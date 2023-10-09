package com.example.reservation.service;

import com.example.reservation.dto.UserDTO;
import com.example.reservation.model.User;
import com.example.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    //ID를 통해 사용자의 정보를 검색
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getUsername());
    }


    //시스템에 사용자를 등록. (점장 or 고객)
    public UserDTO registerUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setId(userDTO.getId());


        user = userRepository.save(user);

        return new UserDTO(user.getId(), user.getName(), user.getEmail()); // 다른 필드들도 필요하다면 추가...
    }

}