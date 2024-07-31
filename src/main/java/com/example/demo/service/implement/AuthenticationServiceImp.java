package com.example.demo.service.implement;

import ch.qos.logback.core.spi.ErrorCodes;
import com.example.demo.DTO.request.AuthenticationRequest;
import com.example.demo.DTO.response.AuthenticationResponse;
import com.example.demo.Model.Student;
import com.example.demo.Payload.ResponseData;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AuthenticationServiceImp implements AuthenticationService {
    @Autowired
    StudentRepository studentRepository;

//    @NonFinal
//    protected static final String SIGNER_KEY = "4lBxZYpiNfNqBXsCXE8ZYNFfFwVRoJsCVVUeS6uT3grgq4WVaZgI2VFKwNAIjIPq";
//
//    private String generateToken(String username){
//        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
//
//        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
//                .subject(username)
//                .issuer("nguyennam.com")
//                .issueTime(new Date())
//                .expirationTime(new Date(
//                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
//                ))
//                .claim("customClaim", "Custom")
//                .build();
//
//        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
//
//        JWSObject jwsObject = new JWSObject(header, payload);
//
//        try {
//            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
//            return jwsObject.serialize();
//        } catch (JOSEException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    @Override
    public AuthenticationResponse authentication (AuthenticationRequest request){
        Optional<Student> student = studentRepository.findByUsername(request.getUsername());

        if(student.isPresent()){

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

            boolean authenticated = passwordEncoder.matches(request.getPassword(), student.get().getPassword());

            if (!authenticated) {
                throw new RuntimeException("User or password not true");
            }

        }

        return false;

    }


}