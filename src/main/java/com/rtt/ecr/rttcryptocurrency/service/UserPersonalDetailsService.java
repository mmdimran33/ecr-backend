package com.rtt.ecr.rttcryptocurrency.service;

import com.rtt.ecr.rttcryptocurrency.entity.UserPersonalDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserPersonalDetailsService {

    UserPersonalDetailsEntity saveUser(UserPersonalDetailsEntity userPersonalDetailsEntity);
    UserPersonalDetailsEntity userViewDetails(String userName,String userEmailId);
    UserPersonalDetailsEntity findByUserEmailId(String userNameOrEmail);
    UserPersonalDetailsEntity userUpdateToken(UserPersonalDetailsEntity userPersonalDetailsEntity);


}
