package com.loger.petshop.infrastructure.security;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * @author loger
 * @date 2023/2/28 13:47
 */
@Component
public class RSA256PublicJWTAccessToken extends JWTAccessToken{

    RSA256PublicJWTAccessToken(UserDetailsService userDetailsService) throws IOException {
        super(userDetailsService);
        Resource resource = new ClassPathResource("public.cert");
        String publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        setVerifierKey(publicKey);
    }

}
