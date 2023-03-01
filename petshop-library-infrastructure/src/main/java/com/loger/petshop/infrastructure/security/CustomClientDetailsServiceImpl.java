package com.loger.petshop.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

/**
 * @author loger
 * @date 2023/2/28 14:04
 */
@Component
public class CustomClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return clientDetailsService.loadClientByClientId(s);
    }
}
