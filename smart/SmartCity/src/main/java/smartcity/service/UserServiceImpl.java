package ru.smartcity.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.smartcity.model.User;
import ru.smartcity.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void add(User user) {
        try {
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            this.repository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("User has already added!");
        }
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        User user = this.repository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("No such User")
        );
        if (!this.passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Incorrect password");
        }
        return user;
    }

    @Override
    public User get(long id) {
        return this.repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User does not exists!")
        );
    }

    @Override
    public User update(User user) {
        User base = get(user.getId());
        base.setUsername(user.getUsername());
        base.setPassword(user.getPassword());
        base.setBirthDate(user.getBirthDate());
        base.setFirstName(user.getFirstName());
        base.setLastName(user.getLastName());
        try {
            return this.repository.save(base);
        } catch (Exception e) {
            throw new IllegalArgumentException("User is already exists!");
        }
    }

    @Override
    public User updateLikes(User user) {
        User base = get(user.getId());
        base.setUserLikes(user.getUserLikes());
        try {
            return this.repository.save(base);
        } catch (Exception e) {
            throw new IllegalArgumentException("User likes did not change!");
        }
    }

    @Override
    public User delete(long id) {
        User user = this.get(id);
        this.repository.delete(user);
        return user;
    }
}
