package com.springboot.SattimSatiyorum.controller;

import com.springboot.SattimSatiyorum.dto.Auth.AuthRequestDTO;
import com.springboot.SattimSatiyorum.dto.Auth.AuthResponseDTO;
import com.springboot.SattimSatiyorum.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtTokenUtil;

    @Autowired
    public AuthRestController(AuthenticationManager authenticationManager, @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, JwtUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO authRequestDTO) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getMail(), authRequestDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect email and password.");
        }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDTO.getMail());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthResponseDTO(jwt);
    }


}
