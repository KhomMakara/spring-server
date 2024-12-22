package jwt.springjwt.controller;

import jwt.springjwt.dto.RegisterUserDto;
import jwt.springjwt.service.AuthService;
import jwt.springjwt.util.data.DataUtil;
import jwt.springjwt.util.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RegisterUserDto registrationRequest) {
        try {

            authService.signUp(registrationRequest);

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseData signIn(@RequestBody DataUtil request){
        authService.signIn(request);
        ResponseData res = new ResponseData();
        res.setBody(request);
        return res;
    }

}