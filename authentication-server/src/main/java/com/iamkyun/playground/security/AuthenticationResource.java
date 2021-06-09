package com.iamkyun.playground.security;

import com.iamkyun.playground.security.model.AuthenticationRequest;
import com.iamkyun.playground.security.model.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthenticationResource {

    @PostMapping("/jwt")
    public Mono<ResponseEntity<AuthenticationResponse>> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest) {
        return null;
    }
}
