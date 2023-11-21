package cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t02.Security.auth;

import cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t02.Security.config.JwtService;
import cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t02.Security.developer.Developer;
import cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t02.Security.developer.Role;
import cat.itacademy.barcelonactiva.dolgopolov.kirill.s05.t02.Security.repository.IDeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final IDeveloperRepository iDeveloperRepository;
    private final JwtService jwtService;

//    public AuthResponse login(LoginRequest request) {
//    }

    public AuthResponse register(RegisterRequest request) {
        Developer developer = Developer.builder()
                .username(request.getUsername())
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.USER)
                .build();
        iDeveloperRepository.save(developer);
        return AuthResponse.builder()

                .token(jwtService.getToken(developer))
                .build();
    }
}
