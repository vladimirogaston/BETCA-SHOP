package ar.ungs.shop.services;

import ar.ungs.shop.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.ungs.shop.entities.UserEntity;
import ar.ungs.shop.repositories.UsersDao;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String P_TOKEN = "";

    @Autowired
    private UsersDao userRepository;

    @Override
    public UserDetails loadUserByUsername(final String name) {
        UserEntity user = userRepository.findByName(name);
        if(user == null) throw new UsernameNotFoundException("User not found. " + name);
        return this.userBuilder(user.getName(), user.getPassword(), new Role[]{Role.AUTHENTICATED}, user.isActive());
    }

    private org.springframework.security.core.userdetails.User userBuilder(String mobile, String password, Role[] roles,
                                                                           boolean active) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.roleName()));
        }
        return new org.springframework.security.core.userdetails.User(mobile, password, active, true,
                true, true, authorities);
    }
}
