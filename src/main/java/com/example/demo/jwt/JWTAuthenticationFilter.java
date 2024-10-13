//package com.example.demo.jwt;
//
//
//import com.example.demo.Services.UserDetailsServiceImpl;
//import com.example.demo.util.JwtUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JWTAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
//        final String authorizationHeader = request.getHeader("Authorization");
//
//        String username = null;
//        String jwt = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7);
//            username = jwtUtil.extractUsername(jwt);
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//            if (jwtUtil.validateToken(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//        chain.doFilter(request, response);
//    }
//}
//

//package com.example.demo.jwt;
//
//import com.example.demo.Services.CustomUserDetailsService;
//import com.example.demo.Services.UserDetailsServiceImpl;
//import com.example.demo.util.JwtUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JWTAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
////    @Autowired
////    private UserDetailsServiceImpl userDetailsService;
//@Autowired
//private CustomUserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
//        final String authorizationHeader = request.getHeader("Authorization");
//
//        Long userId = null;
//        String jwt = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7);
//            userId = jwtUtil.extractUserId(jwt); // Extract userId instead of username
//        }
//
//        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.userDetailsService.loadUserById(userId); // Load user details using userId
//            if (jwtUtil.validateToken(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//        chain.doFilter(request, response);
//    }
//}

///
package com.example.demo.jwt;

import com.example.demo.Services.CustomUserDetailsService;
import com.example.demo.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // Method to sanitize log messages to prevent CRLF injection
    private String sanitizeHeaderValue(String value) {
        return value.replaceAll("[\r\n]", " ");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final StringBuilder logMessages = new StringBuilder();
        final String authorizationHeader = request.getHeader("Authorization");
        logMessages.append("[Filter] Authorization Header: ").append(authorizationHeader).append("\n");

        Long userId = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            logMessages.append("[Filter] Extracted JWT: ").append(jwt).append("\n");

            try {
                userId = jwtUtil.extractUserId(jwt);
                logMessages.append("[Filter] Extracted UserId: ").append(userId).append("\n");
            } catch (Exception e) {
                logMessages.append("[Filter] Error extracting userId: ").append(e.getMessage()).append("\n");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.addHeader("X-Filter-Log", sanitizeHeaderValue(logMessages.toString())); // Sanitize header value
                return;
            }
        } else {
            logMessages.append("[Filter] Authorization header is missing or not Bearer.\n");
        }

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = userDetailsService.loadUserById(userId);
                logMessages.append("[Filter] Loaded UserDetails: ").append(userDetails.getUsername()).append("\n");

                if (jwtUtil.validateToken(jwt, userDetails)) {
                    logMessages.append("[Filter] JWT is valid.\n");
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    logMessages.append("[Filter] Invalid JWT token.\n");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.addHeader("X-Filter-Log", sanitizeHeaderValue(logMessages.toString())); // Sanitize
                    return;
                }
            } catch (Exception e) {
                logMessages.append("[Filter] Error loading UserDetails: ").append(e.getMessage()).append("\n");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.addHeader("X-Filter-Log", sanitizeHeaderValue(logMessages.toString())); // Sanitize
                return;
            }
        } else {
            logMessages.append("[Filter] User already authenticated or no valid token found.\n");
        }

        // Attach sanitized logs to request for further use if needed
        request.setAttribute("filterLogs", logMessages.toString());
        chain.doFilter(request, response);
    }
}



