package com.mdjuniooor.shop.mall.service;

import com.mdjuniooor.shop.mall.domain.NormalUser;
import com.mdjuniooor.shop.mall.domain.SecurityNormalUser;
import com.mdjuniooor.shop.mall.dto.NormalUserResponseDto;
import com.mdjuniooor.shop.mall.exception.DeleteUserException;
import com.mdjuniooor.shop.mall.repository.NormalUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final NormalUserRepository normalUserRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        // 컨텍스트 홀더
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Optional<NormalUser> normalUser = normalUserRepository.findByIdentifier(identifier);

        NormalUserResponseDto normalUserResponseDto;

        if (normalUser.isPresent()) {

            if (normalUser.get().getDeleteYn().equals('Y')) {
                throw new DeleteUserException("이미 탈퇴된 유저입니다. 아이디를 다시 만들어주세요.");
            }

            normalUserResponseDto = normalUser.get().toResponseDto(normalUser.get());
            request.getSession().setAttribute("user", normalUserResponseDto);
        }

        return Optional.of(normalUser)
                .map(SecurityNormalUser::new).get();
    }
}
