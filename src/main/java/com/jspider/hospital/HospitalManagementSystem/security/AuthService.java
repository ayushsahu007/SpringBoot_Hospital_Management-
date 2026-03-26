package com.jspider.hospital.HospitalManagementSystem.security;

import com.jspider.hospital.HospitalManagementSystem.dto.LoginRequestDto;
import com.jspider.hospital.HospitalManagementSystem.dto.LoginResponseDto;
import com.jspider.hospital.HospitalManagementSystem.dto.SignupResponseDto;
import com.jspider.hospital.HospitalManagementSystem.model.User;
import com.jspider.hospital.HospitalManagementSystem.model.type.AuthProviderType;
import com.jspider.hospital.HospitalManagementSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token, user.getId());
    }
    public User signUpInternal(LoginRequestDto signUpRequestDto,AuthProviderType authProviderType, String providerId) {
        User user = userRepository.findByUsername( signUpRequestDto.getUsername()).orElse(null);

        if (user != null) throw new IllegalArgumentException("User already exists");

//        return userRepository.save(User.builder()
//                        .username(signUpRequestDto.getUsername())
//                        .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
//                .build());
        return userRepository.save(User.builder()
                .username(signUpRequestDto.getUsername())
                .password(signUpRequestDto.getPassword() != null
                        ? passwordEncoder.encode(signUpRequestDto.getPassword())
                        : null)
                .providerType(authProviderType)   // ✅ add this
                .providerId(providerId)           // ✅ add this
                .build());

    }

    // login controller
    public SignupResponseDto signup(LoginRequestDto signupRequestDto) {
        User user = signUpInternal(signupRequestDto, AuthProviderType.EMAIL, null);
        return new SignupResponseDto(user.getId(), user.getUsername());
    }



    @Transactional
    public ResponseEntity<LoginResponseDto> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {

        //fetch providerType and ProviderID
          // save the providerType and ProviderID info with user
        //otherwise , first signup  then login

        AuthProviderType providerType = authUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User, registrationId);

        User user = userRepository.findByProviderIdAndProviderType(providerId, providerType).orElse(null);
        String email = oAuth2User.getAttribute("email");

        User emailUser = userRepository.findByUsername(email).orElse(null);

        if(user == null && emailUser == null) {
            // signup flow:
            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId);
            user = signUpInternal(new LoginRequestDto(username,null), providerType, providerId);
      }
        else if(user != null) {
            if(email != null && !email.isBlank() && !email.equals(user.getUsername())) {
                user.setUsername(email);
                userRepository.save(user);
            }
        } else {
            throw new BadCredentialsException("This email is already registered with provider "+emailUser.getProviderType());
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(authUtil.generateAccessToken(user), user.getId());
        return ResponseEntity.ok(loginResponseDto);
    }

}
