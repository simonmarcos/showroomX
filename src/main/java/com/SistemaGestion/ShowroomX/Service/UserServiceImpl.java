package com.SistemaGestion.ShowroomX.Service;

import com.SistemaGestion.ShowroomX.Model.User;
import com.SistemaGestion.ShowroomX.Repository.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {

    private final IUser dao;

    @Autowired
    public UserServiceImpl(IUser dao) {
        this.dao = dao;
    }

    public User save(User user) {
        if (user.getNameUser().equals("") || user.getPassword().equals("") || user.getRoles().equals("")) {
            return null;
        }
        if (!user.getNameUser().chars().allMatch(Character::isLetterOrDigit)) {
            return null;
        }

        //Encrypting Password
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return dao.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userResponse = dao.findByNameUser(username).orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario " + username));

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + userResponse.getRoles()));

        if (authorityList.size() == 0)
            throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");

        //Create Token

        return new org.springframework.security.core.userdetails.User(userResponse.getNameUser(), userResponse.getPassword(), userResponse.getStatus(), true, true, true, authorityList);
    }
}
