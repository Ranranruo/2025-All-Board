package sms.allBoard.Common.Security.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import sms.allBoard.Common.Security.Details.MemberDetailsService;
import sms.allBoard.Common.Enum.ResponseStatus;
import sms.allBoard.Common.Util.ApiResponse;
import sms.allBoard.Common.Util.JwtUtil;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JwtUtil jwtUtil;
    private final MemberDetailsService memberDetailsService;

    public JwtFilter(JwtUtil jwtUtil, MemberDetailsService memberDetailsService) {
        this.jwtUtil = jwtUtil;
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
            jwtUtil.isExpired(accessToken);
        } catch (Exception e) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("application/json;charset=UTF-8");
//            objectMapper.writeValue(response.getWriter(), new ApiResponse<String>(false, ResponseStatus.UNAUTHORIZED, null));
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null, null));
            filterChain.doFilter(request, response);
        }

        String username = jwtUtil.getUsername(accessToken);
        UserDetails userDetails = memberDetailsService.loadUserByUsername(username);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        filterChain.doFilter(request, response);
    }
}