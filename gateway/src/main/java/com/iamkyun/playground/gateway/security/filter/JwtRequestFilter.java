package com.iamkyun.playground.gateway.security.filter;

import com.iamkyun.playground.gateway.security.MyUserDetailsService;
import com.iamkyun.playground.gateway.security.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtRequestFilter implements GatewayFilter, Ordered {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {

                })
        );
    }

    @Override
    public int getOrder() {
        return 0;
    }

    // @Override
    // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
    //
    //     final String authorizationHeader = request.getHeader("Authorization");
    //
    //     String username = null;
    //     String jwt = null;
    //
    //     if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
    //         jwt = authorizationHeader.substring(7);
    //         username = jwtUtils.extractUsername(jwt);
    //     }
    //
    //
    //     if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    //
    //         UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
    //
    //         if (jwtUtils.validateToken(jwt, userDetails)) {
    //
    //             UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
    //                     new UsernamePasswordAuthenticationToken(
    //                             userDetails, null, userDetails.getAuthorities());
    //             usernamePasswordAuthenticationToken
    //                     .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    //             SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    //         }
    //     }
    //     chain.doFilter(request, response);
    // }

}
