package com.example.pswbackend.security;

import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

public class TokenUtils {


    public String getToken(HttpServletRequest request) {
        return null;
    }

    public String getUsernameFromToken(String authToken) {
        return null;
    }

    public boolean validateToken(String authToken, UserDetails userDetails) {
        return false;
    }
}
