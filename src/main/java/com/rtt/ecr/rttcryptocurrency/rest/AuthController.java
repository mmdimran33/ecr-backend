package com.rtt.ecr.rttcryptocurrency.rest;

import com.rtt.ecr.rttcryptocurrency.constant.ApiConstants;
import com.rtt.ecr.rttcryptocurrency.entity.UserPersonalDetailsEntity;
import com.rtt.ecr.rttcryptocurrency.model.LoginDto;
import com.rtt.ecr.rttcryptocurrency.repo.RoleRepository;
import com.rtt.ecr.rttcryptocurrency.repo.UserPersonalDetailsRepo;
import com.rtt.ecr.rttcryptocurrency.security.JwtTokenProvider;
import com.rtt.ecr.rttcryptocurrency.service.UserPersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@CrossOrigin(origins="http://localhost:4200")
//@CrossOrigin(origins="http://43.204.25.68:8081/api/v1/")
@CrossOrigin(origins="*")
@RestController
@RequestMapping(value= ApiConstants.API_VERSION_V1 + ApiConstants. URI_USER_AUTH)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserPersonalDetailsRepo userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserPersonalDetailsService userPersonalDetailsService;

    @PostMapping(ApiConstants.USER_AUTH)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) throws UsernameNotFoundException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
       /* if(!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginFailureResponse("Invalid user name and password", "User Not Found", "url"));
        }*/
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);
        System.out.println("JWT TOKEn ------------" +token);

        //return ResponseEntity.ok(new JWTAuthResponse(token));
        UserPersonalDetailsEntity userPersonalDetailsEntity = userPersonalDetailsService.findByUserEmailId(loginDto.getUsernameOrEmail());
        userPersonalDetailsEntity.setBearerToken(token);
        userPersonalDetailsService.userUpdateToken(userPersonalDetailsEntity);

        /*HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("bearerToken",token);
        HttpResponse response = null;
        return new ResponseEntity<>(user, token,HttpStatus.OK);*/
        return new ResponseEntity<>(userPersonalDetailsEntity,HttpStatus.OK);

    }

    @GetMapping(ApiConstants.USER_AUTH_ROLE_ADMIN)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";

    }

    @GetMapping(ApiConstants.USER_AUTH_ROLE_USER)
    @PreAuthorize("hasRole('ROLE_USER')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    @GetMapping(ApiConstants.USER_AUTH_LOGOUT)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "Logout successfully";
    }
}
