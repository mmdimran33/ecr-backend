package com.rtt.ecr.rttcryptocurrency.rest;

import com.rtt.ecr.rttcryptocurrency.constant.ApiConstants;
import com.rtt.ecr.rttcryptocurrency.entity.UserPersonalDetailsEntity;
import com.rtt.ecr.rttcryptocurrency.service.UserPersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;

//@CrossOrigin(origins="http://localhost:4200")
//@CrossOrigin(origins="http://43.204.25.68:8081/api/v1/")
@CrossOrigin(origins="*")
@RestController
@RequestMapping(value= ApiConstants.API_VERSION_V1 + ApiConstants. URI_PERSONAL_DETAILS )
public class UserPersonalDetailsController {

    @Autowired
    private UserPersonalDetailsService userPersonalDetailsService;
    @PostMapping(value = ApiConstants.USER_REGISTRATION)
    public ResponseEntity<UserPersonalDetailsEntity>userRegistration(
            @RequestBody UserPersonalDetailsEntity userPersonalDetailsEntity) throws IOException {
        return new ResponseEntity<>(userPersonalDetailsService.
                saveUser(userPersonalDetailsEntity), HttpStatus.CREATED);
    }

     @GetMapping(value = ApiConstants.USER_VIEW)
    public ResponseEntity<UserPersonalDetailsEntity>userViewDetails(
            @RequestParam String userName, @RequestParam String userEmailId) {
       //  UserPersonalDetailsEntity u= userPersonalDetailsService.userViewDetails(userName,userEmailId);
         //System.out.println("welcome::"+u.getUserEmailId());
        //return new ResponseEntity<>(HttpStatus.OK);
         return new ResponseEntity<>(userPersonalDetailsService.
                 userViewDetails(userName,userEmailId), HttpStatus.OK);

    }
}
