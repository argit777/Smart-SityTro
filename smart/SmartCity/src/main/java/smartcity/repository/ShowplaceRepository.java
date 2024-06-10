package ru.smartcity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smartcity.model.Showplace;
import ru.smartcity.util.ShowplaceType;

import java.util.List;

@Repository
public interface ShowplaceRepository extends JpaRepository<Showplace, Long> {
    List<Showplace> getShowplacesByType(ShowplaceType type);

}
