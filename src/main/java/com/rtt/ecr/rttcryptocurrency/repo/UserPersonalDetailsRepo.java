package com.rtt.ecr.rttcryptocurrency.repo;

import com.rtt.ecr.rttcryptocurrency.entity.UserPersonalDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserPersonalDetailsRepo extends JpaRepository<UserPersonalDetailsEntity,String> {

    //select u.user_country,u.user_email_id,u.user_name,u.user_password,u.user_phone_no
    @Query(value = "select u.* from user_persnol_details u " +
            "where u.user_name=:userName and u.user_email_id=:userEmailId",nativeQuery = true)
    UserPersonalDetailsEntity findByUserNameAndUserEmailId(@Param("userName") String userName,
                                  @Param("userEmailId") String userEmailId);

    UserPersonalDetailsEntity findByUserEmailId(String userEmailId);
    //Optional<UserPersonalDetailsEntity> findByUserEmailId(String userEmailId);
    Optional<UserPersonalDetailsEntity> findByUserNameOrUserEmailId(String username, String email);

}
