package com.rtt.ecr.rttcryptocurrency.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Set;


@Entity
@Table(name="user_persnol_details",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userName"}),
        @UniqueConstraint(columnNames = {"userEmailId"})
})
public class UserPersonalDetailsEntity {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userEmailId;
    private String userPhoneNo;
    private String userCountry;
    private String userPassword;
    private String bearerToken;
    public String getBearerToken() {
        return bearerToken;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    @Lob
    private byte[] userPassportDoc;
    @Lob
    private byte[] userNationalIdCardDoc;
    @Lob
    private byte[] userDrivingLicenseDoc;

    public UserPersonalDetailsEntity() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public void setUserPhoneNo(String userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public byte[] getUserPassportDoc() {
        return userPassportDoc;
    }

    public void setUserPassportDoc(byte[] userPassportDoc) {
        this.userPassportDoc = userPassportDoc;
    }

    public byte[] getUserNationalIdCardDoc() {
        return userNationalIdCardDoc;
    }

    public void setUserNationalIdCardDoc(byte[] userNationalIdCardDoc) {
        this.userNationalIdCardDoc = userNationalIdCardDoc;
    }

    public byte[] getUserDrivingLicenseDoc() {
        return userDrivingLicenseDoc;
    }

    public void setUserDrivingLicenseDoc(byte[] userDrivingLicenseDoc) {
        this.userDrivingLicenseDoc = userDrivingLicenseDoc;
    }

    public UserPersonalDetailsEntity(String userName, String userEmailId, String userPhoneNo, String userCountry,
                                     String userPassword, byte[] userPassportDoc, byte[] userNationalIdCardDoc, byte[] userDrivingLicenseDoc, Long id
                                     ,String bearerToken) {
        this.userName = userName;
        this.userEmailId = userEmailId;
        this.userPhoneNo = userPhoneNo;
        this.userCountry = userCountry;
        this.userPassword = userPassword;
        this.userPassportDoc = userPassportDoc;
        this.userNationalIdCardDoc = userNationalIdCardDoc;
        this.userDrivingLicenseDoc = userDrivingLicenseDoc;
        this.id=id;
        this.bearerToken=bearerToken;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

}
