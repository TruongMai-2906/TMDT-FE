package com.example.cdwebbe.controller;

import com.example.cdwebbe.model.exception.AppException;
import com.example.cdwebbe.model.Role;
import com.example.cdwebbe.model.RoleName;
import com.example.cdwebbe.model.User;
import com.example.cdwebbe.payload.ApiResponse;
import com.example.cdwebbe.payload.JwtAuthenticationResponse;
import com.example.cdwebbe.payload.LoginRequest;
import com.example.cdwebbe.payload.SignUpRequest;
import com.example.cdwebbe.repository.RoleRepository;
import com.example.cdwebbe.repository.UserRepository;
import com.example.cdwebbe.security.CurrentUser;
import com.example.cdwebbe.security.JwtTokenProvider;
import com.example.cdwebbe.security.UserPrincipal;
import com.example.cdwebbe.service.UserService;
import com.google.common.primitives.Chars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    public static void sendPlainTextEmail(String host, String port,
                                   final String userName, final String password, String toAddress,
                                   String subject, String message) throws AddressException,
            MessagingException {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
// *** BEGIN CHANGE
        properties.put("mail.smtp.user", userName);

        // creates a new session, no Authenticator (will connect() later)
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);
// *** END CHANGE

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setText(message);

// *** BEGIN CHANGE
        // sends the e-mail
        Transport t = session.getTransport("smtp");
        t.connect(userName, password);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
// *** END CHANGE

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws MessagingException {
        System.out.print("Đã chạy đc vô hàm signup");
        System.out.print(signUpRequest);
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Tài khoản này đã được đăng ký rồi, thử lại!"),
                    HttpStatus.OK);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email  đã tồn tại, vui lòng đăng kí khác!"),
                    HttpStatus.OK);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getGender(), signUpRequest.getAddress(), signUpRequest.getPhone());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        //sendmail to welcome
        sendPlainTextEmail("smtp.gmail.com", "587", "tmdt.test1234@gmail.com", "pbpxgmcvuzlydxbw", signUpRequest.getEmail(), "Register successfully", "Welcome to my website!!!");

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    @PostMapping("/signupadmin")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignUpRequest signUpRequest) {
        System.out.print(signUpRequest);
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Tài khoản này đã được đăng ký rồi, thử lại"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Tài khoản đã tồn tại, vui lòng đăng kí khác!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getGender(), signUpRequest.getAddress(), signUpRequest.getPhone());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Admin registered successfully"));
    }

    @GetMapping("/checkvalidname")
    public ResponseEntity<?> checkValidName(@RequestParam(required = false) String name) {
        if (userRepository.existsByUsername(name)) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.ACCEPTED);
    }

    @GetMapping("/checkvalidemail")
    public ResponseEntity<?> checkValidEmail(@RequestParam(required = false) String email) {
        if (userRepository.existsByEmail(email)) {
            return new ResponseEntity(new ApiResponse(false, "Email is already taken!"),
                    HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity deleteClass(
            @PathVariable long id
    ) {
        String message = userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete user success");
    }

    @GetMapping("/forgotpassword/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable("email") String email){
        try{
            String characters="ABCDEFGHIKLMNOZXCV";
            Random rand = new Random();
            char[] text = new char[5];
            for (int i = 0; i < 5; i++)
            {
                text[i] = characters.charAt(rand.nextInt(characters.length()));
            }
            String chuoi = text.toString();
            int ranNum = rand.nextInt(1000000)+1;
            String generatedString=String.valueOf(ranNum)+chuoi;
            User user = userRepository.findByEmail(email).get();
            if(user==null){
                return ResponseEntity.ok().body(new ApiResponse(false,"Không tồn tại email này"));
            }

            user.setPassword(passwordEncoder.encode(generatedString));
            userRepository.save(user);
            sendPlainTextEmail("smtp.gmail.com", "587", "tmdt.test1234@gmail.com", "pbpxgmcvuzlydxbw", email, "Recovery PassWord SecondHand Town ", "Mã mật khẩu mới của bạn đã được kích hoạt ! Vui lòng sử dụng mật khẩu mới này để đăng nhập "+generatedString);
            return ResponseEntity.ok().body(new ApiResponse(true,"Quên mật khẩu thành công , vui lòng check mail"));


        }catch (Exception e){
            return  ResponseEntity.ok().body(new ApiResponse(false,"Quên mật khẩu thất bại"+e));
        }

    }

}
