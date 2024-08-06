package com.example.demo.service.implement;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.IntrospectRequest;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.dto.response.IntrospectResponse;
import com.example.demo.model.Role;
import com.example.demo.model.Student;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;


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
            Object role = signedJWT.getJWTClaimsSet().getClaim("scope");


            return new IntrospectResponse(check && expityTime.after(new Date()), role);


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
        else {
            throw new RuntimeException("UserName not found");
        }


        Set<Role> saveRole = student.get().getRoles();

        List<String> nameRole = new ArrayList<>();

        for (Role pos : saveRole){
            nameRole.add(pos.getName());
        }
        // TODO not fix Role
        var token = generateToken(request.getUsername(), nameRole);

        return new AuthenticationResponse(token, true);

    }
    private String generateToken(String username, List<String> roles){
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("lainguyennam.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", roles)
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

            return jwsObject.serialize();
        }catch (Exception e){
            throw new RuntimeException("Cannot create token");
        }



    }


}
