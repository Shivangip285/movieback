package com.booking.users;

import com.booking.exceptions.InvalidOldPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserPrincipalService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserPrincipalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User savedUser = findUserByUsername(username);

        return new UserPrincipal(savedUser);
    }

    public User findUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return user.getPassword().equals(oldPassword);
    }

    public void changeUserPassword(User user, String password) {
        user.setPassword(password);
        userRepository.save(user);
    }

    public String changePassword(Principal principal, String oldPassword, String newPassword) throws InvalidOldPasswordException {
        User user = findUserByUsername(principal.getName());
        if(validatePassword(user, oldPassword, newPassword)){
            changeUserPassword(user, newPassword);
            return "Password changed successfully";
        }
        return "Unsuccessful";
    }

    public boolean validatePassword(User user, String oldPassword, String newPassword) throws InvalidOldPasswordException {
        if (!checkIfValidOldPassword(user, oldPassword)) {
            throw new InvalidOldPasswordException();
        }
        return true;
    }
}
