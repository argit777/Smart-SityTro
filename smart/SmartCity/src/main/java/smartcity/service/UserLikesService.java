package ru.smartcity.service;

import ru.smartcity.model.Showplace;

import java.util.List;

public interface UserLikesService {
    void add(long userId, long showplaceId);
    void delete(long userId, long showplaceId);
    List<Showplace> getUserLikes(long userId);
}
