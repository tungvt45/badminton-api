package com.prcbadminton.badminton.sercurity;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prcbadminton.badminton.constant.SecurityConstant;
import com.prcbadminton.badminton.entities.User;
import com.prcbadminton.badminton.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.prcbadminton.badminton.constant.SecurityConstant.HEADER_STRING;
import static com.prcbadminton.badminton.constant.SecurityConstant.SECRET;
import static com.prcbadminton.badminton.constant.SecurityConstant.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        System.out.println("Authentication");


        try {
            User creds = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword())
        );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        List<GrantedAuthority> roles = (List<GrantedAuthority>) auth.getAuthorities();
        String roleUser = roles.get(0).getAuthority();
        String email = ((UserDetails) auth.getPrincipal()).getUsername();

        // create token
        String token = JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                .withClaim(SecurityConstant.AUTHORITIES_KEY, roleUser)
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        res.addHeader("Access-Control-Expose-Headers", "Access-Token, Uid");
        res.setContentType("application/json");

//        // get account details
        Optional<User> account = userRepository.findByEmail(email);
        account.get().setPassword("");
        // convert object to json string
        ObjectMapper mapper = new ObjectMapper();
        String accountJson = mapper.writeValueAsString(account.get().getName());
        System.out.println("SUCESSSSSSSSSSSSSSSSSSS");
        // write respone
        PrintWriter out = res.getWriter();
        out.print(accountJson);
        out.flush();
    }
}
