package ru.smartcity.service;

import ru.smartcity.model.Showplace;
import ru.smartcity.util.ShowplaceType;

import java.util.List;

public interface ShowplaceService {

    void add(Showplace showplace);
    Showplace getById(long id);
    List<Showplace> get();
    List<Showplace> getByType(ShowplaceType type);
    Showplace update(Showplace showplace);
    Showplace delete(long showplaceId);

}
