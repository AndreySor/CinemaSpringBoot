package com.school21.cinemaspringboot.service.Impl;

import com.school21.cinemaspringboot.exception.NotAllDataException;
import com.school21.cinemaspringboot.exception.ObjectAlreadyExistsException;
import com.school21.cinemaspringboot.model.Role;
import com.school21.cinemaspringboot.model.User;
import com.school21.cinemaspringboot.repository.UserRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${images.folder.path}")
    private String imagesPath;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " was not found");
        }
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(),
                roles);
    }

    public void saveUser(User user) {
        if (user == null || user.getLogin() == null
                || user.getPassword() == null || user.getFirstName() == null
                || user.getLastName() == null || user.getEmail() == null || user.getPhone() == null) {
            throw new NotAllDataException("Please enter all data");
        }
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new ObjectAlreadyExistsException("A user with this login already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }

    public void uploadAvatar(MultipartFile file, Long userId) throws IOException {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
        String fileName = date + file.getOriginalFilename();

        String filePath = imagesPath + userId + File.separator + fileName;

        if (!Files.exists(Paths.get(imagesPath + userId))) {
            if (!Files.exists(Paths.get(imagesPath))) {
                Files.createDirectories(Paths.get(imagesPath));
            }
            Files.createDirectories(Paths.get(imagesPath + userId));
        }
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        User user = userRepository.getById(userId);
        user.setAvatar(fileName);
        userRepository.save(user);
    }

    public String getAvatar(User user) {
        byte[] file = new byte[0];
        try {
            file = FileUtils.readFileToByteArray(new File(imagesPath + user.getId() + File.separator + user.getAvatar()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(file);
    }
}
