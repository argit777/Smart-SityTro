package ru.smartcity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.smartcity.model.Showplace;
import ru.smartcity.model.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLikesServiceImpl implements UserLikesService{
    private final UserService userService;
    private final ShowplaceService showplaceService;

    @Override
    public void add(long userId, long showplaceId) {
        User user = userService.get(userId);
        Showplace showplace = showplaceService.getById(showplaceId);
        List<Showplace> userLikes = user.getUserLikes();
        if (!userLikes.contains(showplace)){
            userLikes.add(showplace);
            user.setUserLikes(userLikes);
            this.userService.updateLikes(user);
        }else {
            throw new IllegalArgumentException("User likes does not change!");
        }
    }

    @Override
    public void delete(long userId, long showplaceId) {
        User user = userService.get(userId);
        Showplace showplace = showplaceService.getById(showplaceId);
        List<Showplace> userLikes = user.getUserLikes();
        if (userLikes.contains(showplace)){
            userLikes.remove(showplace);
            user.setUserLikes(userLikes);
            this.userService.updateLikes(user);
        }else {
            throw new IllegalArgumentException("User does not have this showplace in likes!");
        }
    }

    @Override
    public List<Showplace> getUserLikes(long userId) {
        User user = this.userService.get(userId);
        return user.getUserLikes();
    }
}
