package sms.allBoard.Auth.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sms.allBoard.Auth.AuthValidator;
import sms.allBoard.Auth.DTO.SignInRequestDTO;
import sms.allBoard.Auth.DTO.SignInResponseDTO;
import sms.allBoard.Auth.Details.MemberDetails;
import sms.allBoard.Auth.JWT.JWTProvider;
import sms.allBoard.Common.Enum.ResponseStatus;
import sms.allBoard.Common.Enum.FieldStatus;
import sms.allBoard.Common.Util.ApiResponse;
import sms.allBoard.Common.Util.Redis.RedisService;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;


public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JWTProvider jwtProvider;
    private final AuthValidator authValidator;
    private final RedisService redisService;

    public LoginFilter(AuthenticationManager authenticationManager, JWTProvider jwtProvider, AuthValidator authValidator, RedisService redisService) {
        setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/sign-in");

        this.jwtProvider = jwtProvider;
        this.redisService = redisService;
        this.authValidator = authValidator;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            SignInRequestDTO signInRequestDTO = new SignInRequestDTO();
            Map<String, Object> json = null;
            json = objectMapper.readValue(request.getInputStream(), Map.class);
            signInRequestDTO.setUsername((String) json.get("username"));
            signInRequestDTO.setPassword((String) json.get("password"));

            request.setAttribute("signInJson", json);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(signInRequestDTO.getUsername(), signInRequestDTO.getPassword());
            return this.getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        System.out.println("successful authentication");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        MemberDetails member = (MemberDetails) auth.getPrincipal();

        String refreshToken = jwtProvider.generateRefreshToken(member.getUsername());
        String accessToken = jwtProvider.generateAccessToken(member.getUsername());
        String refreshUUID = UUID.randomUUID().toString();

        redisService.set(refreshUUID, refreshToken);

        Cookie cookie = new Cookie("refresh_token", refreshUUID);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        System.out.println(redisService.get(refreshUUID));

        response.addCookie(cookie);

        response.addHeader("Authorization", "Bearer " + accessToken);

        SignInResponseDTO signInResponseDTO = new SignInResponseDTO();
        signInResponseDTO.setUsername(FieldStatus.SUCCESS);
        signInResponseDTO.setPassword(FieldStatus.SUCCESS);

        response.setStatus(HttpServletResponse.SC_OK);

        String responseToString = objectMapper.writeValueAsString(new ApiResponse<SignInResponseDTO>(true, ResponseStatus.SUCCESS, signInResponseDTO));

        response.getWriter().write(responseToString);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        System.out.println("unsuccessfulAuthentication");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        SignInRequestDTO signInRequestDTO = new SignInRequestDTO();
        Map<String, Object> json = (Map<String, Object>) request.getAttribute("signInJson");
        signInRequestDTO.setUsername((String) json.get("username"));
        signInRequestDTO.setPassword((String) json.get("password"));

        SignInResponseDTO signInResponseDTO = authValidator.validateSignInRequest(signInRequestDTO);

        boolean isValidUsername = signInResponseDTO.getUsername().equals(FieldStatus.SUCCESS);
        boolean isValidPassword = signInResponseDTO.getPassword().equals(FieldStatus.SUCCESS);

        String responseToString = "default";

        if (!(isValidUsername && isValidPassword)) {
            responseToString = objectMapper.writeValueAsString(new ApiResponse<SignInResponseDTO>(false, ResponseStatus.INVALID, signInResponseDTO));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(responseToString);
        } else {
            signInResponseDTO.setUsername(FieldStatus.ERROR);
            signInResponseDTO.setPassword(FieldStatus.ERROR);
            responseToString = objectMapper.writeValueAsString(new ApiResponse<SignInResponseDTO>(false, ResponseStatus.UNAUTHORIZED, signInResponseDTO));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(responseToString);
        }
    }
}