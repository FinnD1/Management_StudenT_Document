package com.spring.psring_postman.controller;


import com.nimbusds.jose.JOSEException;
import com.spring.psring_postman.dto.reponse.AuthenticationReponse;
import com.spring.psring_postman.dto.reponse.ApiResponse;
import com.spring.psring_postman.dto.reponse.IntrospectResponse;
import com.spring.psring_postman.dto.request.AuthenticationRequest;
import com.spring.psring_postman.dto.request.IntrospectRequest;
import com.spring.psring_postman.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationReponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationReponse>builder().result(result).build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}
