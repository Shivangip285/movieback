package com.booking.users;

import com.booking.exceptions.InvalidCurrentPasswordException;
import com.booking.exceptions.NewPasswordMatchedPreviousPasswordsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserPrincipalService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordLoggerRepository passwordLoggerRepository;

    @Autowired
    public UserPrincipalService(UserRepository userRepository, PasswordLoggerRepository passwordLoggerRepository) {
        this.userRepository = userRepository;
        this.passwordLoggerRepository = passwordLoggerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User savedUser = findUserByUsername(username);

        return new UserPrincipal(savedUser);
    }

    public User findUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public PasswordLogger findPasswordsByUserID(Long userId) throws UsernameNotFoundException {
        return passwordLoggerRepository.findByUser_id(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return user.getPassword().equals(oldPassword);
    }

    public boolean checkIfNewPasswordIsSameAsOlderPasswords(User user, String newPassword){
        try{
            PasswordLogger passwordLogger = findPasswordsByUserID(user.getId());
            return (user.getPassword().equals(newPassword)||(passwordLogger.getOlderPassword()!=null&&passwordLogger.getOlderPassword().equals(newPassword))||(passwordLogger.getOldestPassword()!=null&&passwordLogger.getOldestPassword().equals(newPassword)));

        }catch(Exception e){
            return (user.getPassword().equals(newPassword));
        }
    }

    public void changeUserPassword(User user, String password) {
        PasswordLogger passwordLogger;
        try {
            passwordLogger = findPasswordsByUserID(user.getId());
            passwordLogger.setOldestPassword(passwordLogger.getOlderPassword());
            passwordLogger.setOlderPassword(user.getPassword());
        }
        catch (Exception e){
            passwordLogger = new PasswordLogger(user,user.getPassword(),null);
        }
        user.setPassword(password);
        userRepository.save(user);
        passwordLoggerRepository.save(passwordLogger);
    }

    public String changePassword(Principal principal, String oldPassword, String newPassword) throws InvalidCurrentPasswordException, NewPasswordMatchedPreviousPasswordsException {
        User user = findUserByUsername(principal.getName());
        System.out.println("Change password function:"+user.getId());
        if(validatePassword(user,oldPassword, newPassword)){
            changeUserPassword(user, newPassword);
            return "Password changed successfully";
        }
        return "Unsuccessful";
    }

    public boolean validatePassword(User user, String oldPassword, String newPassword) throws InvalidCurrentPasswordException, NewPasswordMatchedPreviousPasswordsException {
        if (!checkIfValidOldPassword(user, oldPassword)) {
            throw new InvalidCurrentPasswordException();
        }
        if(checkIfNewPasswordIsSameAsOlderPasswords(user, newPassword)) {
            throw new NewPasswordMatchedPreviousPasswordsException();
        }
        return true;
    }
}
