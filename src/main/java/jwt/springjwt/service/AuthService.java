package jwt.springjwt.service;

import jwt.springjwt.dto.LoginUserDto;
import jwt.springjwt.dto.RegisterUserDto;
import jwt.springjwt.repository.User;
import jwt.springjwt.util.data.DataUtil;
import jwt.springjwt.util.request.RequestData;
import jwt.springjwt.util.response.ResponseData;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private User ourUserRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<RegisterUserDto> signUp(@RequestBody RegisterUserDto registrationRequest){
        RegisterUserDto req = new RegisterUserDto();
        try {
            jwt.springjwt.model.User ourUsers = new jwt.springjwt.model.User();
            ourUsers.setEmail(req.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            jwt.springjwt.model.User ourUserResult = ourUserRepo.save(ourUsers);

        }catch (Exception e){
           System.out.println(">>>>>>>>> Error "+ e.getMessage());
        }
        return new ResponseEntity<>(req, HttpStatus.CREATED);
    }

    public DataUtil signIn(DataUtil request) {
        DataUtil response = new DataUtil();

        // Extract the body from RequestData and cast to LoginUserDto
        try {
            // Authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getString("email"),request.getString("password"))
            );

            // Fetch the user from the repository
            var user = ourUserRepo.findByEmail(request.getString("email"))
                    .orElseThrow(() -> new RuntimeException("User not found"));

            System.out.println("USER IS: " + user);

            // Generate JWT and refresh token
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            // Set response data
            request.setInt("status",200);
            request.setString("token",jwt);
            request.setString("refreshToken",refreshToken);
            request.setString("expireDate","24h");

        } catch (Exception e) {
            request.setInt("status",500);
            request.setString("message","Error during login: " + e.getMessage());
        }

        return response;
    }


//    public ReqRes refreshToken(ReqRes refreshTokenReqiest){
//        ReqRes response = new ReqRes();
//        String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
//        User users = ourUserRepo.findByEmail(ourEmail).orElseThrow();
//        if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
//            var jwt = jwtUtils.generateToken(users);
//            response.setStatusCode(200);
//            response.setToken(jwt);
//            response.setRefreshToken(refreshTokenReqiest.getToken());
//            response.setExpirationTime("24Hr");
//            response.setMessage("Successfully Refreshed Token");
//        }
//        response.setStatusCode(500);
//        return response;
//    }
}