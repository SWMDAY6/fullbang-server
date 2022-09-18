package day6.fullbang.service;

import org.springframework.stereotype.Service;

import day6.fullbang.domain.User;
import day6.fullbang.dto.request.UserForm;
import day6.fullbang.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void join(UserForm userForm) {

        User user = new User();
        user.setId(userForm.getId());
        user.setName(userForm.getName());
        user.setPassword(userForm.getPassword());

        userRepository.join(user);
    }
}
