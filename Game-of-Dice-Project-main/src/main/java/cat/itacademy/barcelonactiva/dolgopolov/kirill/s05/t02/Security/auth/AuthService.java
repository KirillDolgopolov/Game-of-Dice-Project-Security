package cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t02.Security.auth;

import cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t02.Security.config.JwtService;
import cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t02.Security.developer.Developer;
import cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t02.Security.developer.Role;
import cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t02.Security.repository.IDeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final IDeveloperRepository iDeveloperRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = iDeveloperRepository.findByUsername(request.username).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
        Developer developer = Developer.builder()
                .username(request.getUsername())
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        iDeveloperRepository.save(developer);
        return AuthResponse.builder()

                .token(jwtService.getToken(developer))
                .build();
    }
}
