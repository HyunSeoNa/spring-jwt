package com.example.springjwt.jwt;

import com.example.springjwt.dto.CustomUserDetails;
import com.example.springjwt.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * fileName     : null.java
 * author       : hyunseo
 * date         : 2025. 4. 12.
 * description  :
 */
@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        // authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("JWT token null");

            filterChain.doFilter(request, response);        // 다음 필터로 넘겨주기

            // 조건에 해당되면 메소드 종료(필수)
            return;
        }

        // Bearer 부분 제거 후 순수 토큰 획득
        String token = authorization.split(" ")[1];

        // 토큰 소멸 시간 검증
        if(jwtUtils.isExpired(token)) {
            log.info("JWT token expired");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰에서 Username과 role 획득
        String username = jwtUtils.getUsername(token);
        String role = jwtUtils.getRole(token);

        // 일시적인 유저 생성
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);   // DB 조회 필요 없이 임시 비밀번호로 넣어줘도 됨.
        userEntity.setPassword("hyunter");
        userEntity.setRole(role);

        // UserDetail에 회원 정보 객체 담기
        CustomUserDetails userDetails = new CustomUserDetails(userEntity);
        // 스프링 시큐리티 인증 토큰 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }
}
