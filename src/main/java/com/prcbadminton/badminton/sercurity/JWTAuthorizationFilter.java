package com.prcbadminton.badminton.sercurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.prcbadminton.badminton.entities.User;
import com.prcbadminton.badminton.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


import static com.prcbadminton.badminton.constant.SecurityConstant.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private UserRepository userRepository;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
        } else {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            DecodedJWT decodeJWT = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""));
            String email = decodeJWT.getSubject();
            Map<String, Claim> claims = decodeJWT.getClaims();
            String role = claims.get(AUTHORITIES_KEY).asString();
            if (email != null) {
                Optional<User> account = userRepository.findByEmail(email);
                Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                grantedAuthorities.add(new SimpleGrantedAuthority(role));
                return new UsernamePasswordAuthenticationToken(email, account, grantedAuthorities);
            }
            return null;
        }
        return null;
    }
}
