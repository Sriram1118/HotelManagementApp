package com.HotelProjectcode.HotelManagement.services.auth;

import com.HotelProjectcode.HotelManagement.dto.SignupRequest;
import com.HotelProjectcode.HotelManagement.dto.UserDto;
import com.HotelProjectcode.HotelManagement.entity.User;
import com.HotelProjectcode.HotelManagement.enums.UserRole;
import com.HotelProjectcode.HotelManagement.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
//@RequiredArgsConstructor
//public class AuthServiceImpl {
//
//    private final UserRepository userRepository;
//
//    @PostConstruct
//    public void createAdminAccount(){
//        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
//        if(adminAccount.isEmpty()){
//            User user=new User();
//            user.setEmail("admin@test.com");
//            user.setName("Admin");
//            user.setUserRole(UserRole.ADMIN);
//            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
//            userRepository.save(user);
//            System.out.println("Admin account created Successfully");
//        }else{
//            System.out.println("Admin account already exist");
//        }
//    }
//}

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createAdminAccount() {
        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount.isEmpty()) {
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Admin account created Successfully");
        } else {
            System.out.println("Admin account already exists");
        }
    }

    public UserDto createUser(SignupRequest signupRequest){
        if(userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()){
            throw new EntityExistsException("User Already Exists with email "+signupRequest.getEmail());
        }
        User user=new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createdUser=userRepository.save(user);
        return createdUser.getUserDto();
    }

}
