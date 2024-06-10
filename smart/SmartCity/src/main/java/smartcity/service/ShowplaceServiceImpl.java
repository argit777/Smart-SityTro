package ru.smartcity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.smartcity.model.Showplace;
import ru.smartcity.model.User;
import ru.smartcity.repository.ShowplaceRepository;
import ru.smartcity.util.ShowplaceType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowplaceServiceImpl implements ShowplaceService {

    private final UserServiceImpl userService;
    private final ShowplaceRepository showplaceRepository;

    @Override
    public void add(Showplace showplace) {
        try {
            this.showplaceRepository.save(showplace);
        } catch (Exception e) {
            throw new IllegalArgumentException("Showplace is already added!");
        }
    }

    @Override
    public Showplace getById(long id) {
        return this.showplaceRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Showplace does not exists!"));
    }

    @Override
    public List<Showplace> get() {
        return this.showplaceRepository.findAll();
    }

    @Override
    public List<Showplace> getByType(ShowplaceType type) {
        return this.showplaceRepository.getShowplacesByType(type);
    }

    @Override
    public Showplace update(Showplace showplace) {
        Showplace base = this.getById(showplace.getId());
        base.setInfo(showplace.getInfo());
        base.setName(showplace.getName());
        base.setType(showplace.getType());
        base.setAddress(showplace.getAddress());
        base.setRating(showplace.getRating());
        try {
            return this.showplaceRepository.save(base);
        } catch (Exception e) {
            throw new IllegalArgumentException("Showplace is already exists!");
        }
    }

    @Override
    public Showplace delete(long showplaceId) {
        Showplace byId = this.getById(showplaceId);
        this.showplaceRepository.delete(byId);
        return byId;
    }
}
