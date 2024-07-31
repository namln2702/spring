package com.example.demo.service.implement;

import ch.qos.logback.core.spi.ErrorCodes;
import com.example.demo.DTO.request.AuthenticationRequest;
import com.example.demo.DTO.request.IntrospectRequest;
import com.example.demo.DTO.response.AuthenticationResponse;
import com.example.demo.DTO.response.IntrospectResponse;
import com.example.demo.Model.Student;
import com.example.demo.Payload.ResponseData;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AuthenticationServiceImp implements AuthenticationService {
    @Autowired
    StudentRepository studentRepository;

    @NonFinal
    @Value("${jwt.singerkey}")
    protected String SIGNER_KEY;

    @Override
    public IntrospectResponse checkToken(IntrospectRequest introspectRequest) {
        var token = introspectRequest.getToken();

        try {
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

            SignedJWT signedJWT = SignedJWT.parse(token);

            var check = signedJWT.verify(verifier);

            Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            return new IntrospectResponse(check && expityTime.after(new Date()));


        }
        catch (Exception e) {
            throw new RuntimeException("Error not SignerJWT.parse or Token decoding failed");
        }

    }

    @Override
    public AuthenticationResponse createToken (AuthenticationRequest request){
        Optional<Student> student = studentRepository.findByUsername(request.getUsername());

        if(student.isPresent()){

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

            boolean authenticated = passwordEncoder.matches(request.getPassword(), student.get().getPassword());

            if (!authenticated) {
                throw new RuntimeException("User or password not true");
            }

        }

        var token = generateToken(request.getUsername());

        return new AuthenticationResponse(token, true);

    }
    private String generateToken(String username){
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("lainguyennam.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("customeClaim", "Custom")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

            return jwsObject.serialize();
        }catch (Exception e){
            throw new RuntimeException("Cannot create tokenl");
        }



    }


}
