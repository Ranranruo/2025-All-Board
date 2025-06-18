package sms.allBoard.Auth.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import sms.allBoard.Auth.Details.MemberDetailsService;
import sms.allBoard.Auth.Service.JwtService;
import sms.allBoard.Common.Enum.ResponseStatus;
import sms.allBoard.Common.Util.ApiResponse;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JwtService jwtService;
    private final MemberDetailsService memberDetailsService;

    public JWTFilter(JwtService jwtService, MemberDetailsService memberDetailsService) {
        this.jwtService = jwtService;
        this.memberDetailsService = memberDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String accessToken = authorization.substring("Bearer ".length());

        try {
            jwtService.isExpired(accessToken);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            objectMapper.writeValue(response.getWriter(), new ApiResponse<String>(false, ResponseStatus.UNAUTHORIZED, null));
            return;
        }

        String username = jwtService.getUsername(accessToken);
        UserDetails userDetails = memberDetailsService.loadUserByUsername(username);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        filterChain.doFilter(request, response);
    }
}