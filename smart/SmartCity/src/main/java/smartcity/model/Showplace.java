package ru.smartcity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.smartcity.util.ShowplaceType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "showplaces")
public class Showplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private ShowplaceType type;
    @NonNull
    @Column(nullable = false, unique = true)
    private String name;
    private String info;
    private Integer rating;
    private String address;
    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "userLikes")
    private List<User> likedUsers = new ArrayList<>();

    public Showplace(@NonNull ShowplaceType type, @NonNull String name, String info, Integer rating, String address) {
        this.type = type;
        this.name = name;
        this.info = info;
        this.rating = rating;
        this.address = address;
    }
}
