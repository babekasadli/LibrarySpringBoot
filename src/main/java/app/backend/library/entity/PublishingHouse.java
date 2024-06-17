package app.backend.library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "publishing_houses")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "books")
public class PublishingHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String contactNumber;

    @OneToMany(mappedBy = "publishingHouse", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Book> books;
}
