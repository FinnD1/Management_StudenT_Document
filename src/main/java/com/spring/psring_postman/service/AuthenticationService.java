package com.spring.psring_postman.service;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.JWTClaimsSetVerifier;
import com.spring.psring_postman.dto.reponse.AuthenticationReponse;
import com.spring.psring_postman.dto.reponse.IntrospectResponse;
import com.spring.psring_postman.dto.request.AuthenticationRequest;
import com.spring.psring_postman.dto.request.IntrospectRequest;
import com.spring.psring_postman.entity.User;
import com.spring.psring_postman.exception.AppException;
import com.spring.psring_postman.exception.ErrorCode;
import com.spring.psring_postman.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    private final UserRepository userRepository;

    @NonFinal
//    @Value("{}")
    protected  static   String SIGNER_KEY= "YZnjRB+DlW7W7JxzzuQXiY+qhVVQB5xlJQo0l2ypPzWG61JFJ7x89hbOJHz2XsL3";

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token=request.getToken();

        JWSVerifier verifier=new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationDate=signedJWT.getJWTClaimsSet().getExpirationTime();

         var verified = signedJWT.verify(verifier);

         return IntrospectResponse.builder()
                 .valid(verified && expirationDate.after(new Date()))
                 .build();
    }

    public AuthenticationReponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUserName(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        var token= generateToken(user);

        return AuthenticationReponse.builder().token(token).authenticated(true).build();
    }

    private String generateToken(User username) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet  = new JWTClaimsSet.Builder()
                .subject(username.getUserName())
                .issuer("finnd.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope",buildScope(username))
                .build();

        Payload payload= new Payload(jwtClaimsSet.toJSONObject());


        JWSObject jwsObject =new JWSObject(jwsHeader,payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cant not create token",e);
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner= new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(stringJoiner::add);
        }
        return stringJoiner.toString();
    }
}
