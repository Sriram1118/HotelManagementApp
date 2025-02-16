package com.HotelProjectcode.HotelManagement.controller.auth;

import com.HotelProjectcode.HotelManagement.dto.AuthenticationRequest;
import com.HotelProjectcode.HotelManagement.dto.AuthenticationResponse;
import com.HotelProjectcode.HotelManagement.dto.SignupRequest;
import com.HotelProjectcode.HotelManagement.dto.UserDto;
import com.HotelProjectcode.HotelManagement.entity.User;
import com.HotelProjectcode.HotelManagement.repository.UserRepository;
import com.HotelProjectcode.HotelManagement.services.auth.AuthService;
import com.HotelProjectcode.HotelManagement.services.jwt.UserService;
import com.HotelProjectcode.HotelManagement.util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
        try{
            UserDto createdUser=authService.createUser(signupRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        }catch (EntityExistsException entityExistsException){
            return new ResponseEntity<>("User Already Exists",HttpStatus.NOT_ACCEPTABLE);
        }catch(Exception e){
            return new ResponseEntity<>("User not created, come again later",HttpStatus.BAD_REQUEST);
        }
    }
//for testing purpose
    // Endpoint to handle POST requests to "/api/success"
    @PostMapping("/success")
    public ResponseEntity<String> postOk(@RequestBody String data) {
        // Create a response message with received data
        String responseMessage = "Received data: " + data;
        // Return an HTTP 200 OK response with the response message
        return ResponseEntity.ok().body(responseMessage);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){
    try{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
        throw new BadCredentialsException("Incorrect username or password");
    }

    final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
    Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails);

    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()){
        authenticationResponse.setJwt(jwt);
        authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        authenticationResponse.setUserId(optionalUser.get().getId());
        }
    return authenticationResponse;
    }
}
