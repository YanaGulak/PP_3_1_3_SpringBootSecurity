package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        User userFromDB = userRepository.findUserById(id);
        if (userFromDB == null) {
            throw new UsernameNotFoundException(String.format("Пользователь с id = %d не найден", id));
        }
        return userFromDB;
    }

    //
    @Transactional
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // кодируем пароль
        user.setRoles(getMyListRoles(user)); //сверяемся совпадают ли роли юзера, полученные из формы, списку ролей из roleRepository
        userRepository.saveUser(user); // сохраняем пользователя
    }

    @Transactional
    @Override
    public void updateUser(User updateUser, Long id) {
        User user = userRepository.findUserById(id);
        user.setName(updateUser.getName());
        user.setLastName(updateUser.getLastName());
        user.setAge(updateUser.getAge());
        user.setEmail(updateUser.getEmail());
        user.setUsername(updateUser.getUsername());
        user.setRoles(getMyListRoles(updateUser));
        if (!user.getPassword().equals(updateUser.getPassword())) {
            user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        }
        userRepository.updateUser(user);
    }

    //костыль вместо удобного JpaRepositories
    //Связи роли с юзерами, метод для внутренних нужд класса
    private List<Role> getMyListRoles(User user) {
        List<Role> usersRoles = user.getRoles();
        List<Role> allRoles = roleRepository.findAll();
        List<Role> myRoleList = new ArrayList<Role>();
        for (Role role : allRoles) {
            for (Role userRole : usersRoles) {
                if (role.getRole().equals(userRole.getRole())) {
                    myRoleList.add(role);
                }
            }
        }
        return myRoleList;
    }


    @Transactional
    @Override
    public void deleteUserById(long id) {
        userRepository.deleteUserById(id);
    }

}