package com.marine.website.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<SiteUser> _siteUser = this.userRepository.findByemail(email);

        if (_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자 email을 찾을 수 없습니다.");
        }
        SiteUser siteUser = _siteUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        //일단은 모든 최초 가입 사용자는 user로 입장시킨다!
//        if ("admin".equals(email)) {
//            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
//        }else{
//      }
        authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));

        return new User(siteUser.getEmail(), siteUser.getPassword(), authorities);
    }
}
