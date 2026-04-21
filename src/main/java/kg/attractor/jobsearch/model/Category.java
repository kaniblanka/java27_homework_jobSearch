package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Resume> resumes;

    @OneToMany(mappedBy = "category")
    private List<Vacancy> vacancies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true)
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children;
}