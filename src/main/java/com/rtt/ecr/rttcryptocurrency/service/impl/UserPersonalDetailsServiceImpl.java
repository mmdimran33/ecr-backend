package com.rtt.ecr.rttcryptocurrency.service.impl;

import com.rtt.ecr.rttcryptocurrency.entity.Role;
import com.rtt.ecr.rttcryptocurrency.entity.UserPersonalDetailsEntity;
import com.rtt.ecr.rttcryptocurrency.repo.RoleRepository;
import com.rtt.ecr.rttcryptocurrency.repo.UserPersonalDetailsRepo;
import com.rtt.ecr.rttcryptocurrency.service.UserPersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.codec.Hex;

import java.util.Collections;

@Service
public class UserPersonalDetailsServiceImpl implements UserPersonalDetailsService {

    @Autowired
    private UserPersonalDetailsRepo userPersonalDetailsRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


   /* @Autowired
    private JavaMailSender emailSender;*/

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserPersonalDetailsEntity saveUser(UserPersonalDetailsEntity userPersonalDetailsEntity) {
        Role roles=null;
        String sendUserPasswordToMail=userPersonalDetailsEntity.getUserPassword();
       // SimpleMailMessage message = new SimpleMailMessage();
        userPersonalDetailsEntity.setUserPassword(passwordEncoder.encode(userPersonalDetailsEntity.getUserPassword()));
        //Created and mapped with Admin Role
        if(userPersonalDetailsEntity.getUserEmailId().equalsIgnoreCase("imrantech333@gmail.com")){
            roles = roleRepository.findByName("ROLE_ADMIN").get();
        }else{
            //Created and mapped with User Role
             roles = roleRepository.findByName("ROLE_USER").get();
        }
        userPersonalDetailsEntity.setRoles(Collections.singleton(roles));
        UserPersonalDetailsEntity userPersonalEntity= userPersonalDetailsRepo.save(userPersonalDetailsEntity);
       /* message.setFrom("hr@octavision.org");
        message.setTo(userPersonalDetailsEntity.getUserEmailId());
        message.setSubject("!!!!!Welcome to Octavision onboarding Please find your User email id and Password!!!!!");
        message.setText("Your User email id:"+userPersonalDetailsEntity.getUserEmailId()
                +",,,"+"and your User Password:"+ sendUserPasswordToMail);
        emailSender.send(message);*/
        return userPersonalEntity;
    }

    @Override
    public UserPersonalDetailsEntity userViewDetails(String userName, String userEmailId) {
        return userPersonalDetailsRepo.findByUserNameAndUserEmailId(userName,userEmailId);
    }

    @Override
    public UserPersonalDetailsEntity findByUserEmailId(String userNameOrEmail) {
        return userPersonalDetailsRepo.findByUserEmailId(userNameOrEmail);
    }

    @Override
    public UserPersonalDetailsEntity userUpdateToken(UserPersonalDetailsEntity userPersonalDetailsEntity) {
        return userPersonalDetailsRepo.save(userPersonalDetailsEntity);
    }
}
