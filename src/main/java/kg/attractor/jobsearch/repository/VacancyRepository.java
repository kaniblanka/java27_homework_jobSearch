package kg.attractor.jobsearch.repository;

import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    Page<Vacancy> findByIsActiveTrue(Pageable pageable);

    Page<Vacancy> findByAuthorId(Long authorId, Pageable pageable);

    Page<Vacancy> findByCategoryId(Long categoryId, Pageable pageable);

    @Query("""
        select v
        from Vacancy v
        left join v.respondedApplicants r
        where v.isActive = true
        group by v.id, v.name, v.description, v.category, v.salary, v.expFrom, v.expTo, v.isActive, v.author, v.createdDate, v.updateTime
        order by count(r.id) desc
        """)
    Page<Vacancy> findAllActiveOrderByResponsesCount(Pageable pageable);

    @Query("""
            select v
            from Vacancy v
            where v.isActive = true
            order by v.createdDate desc
            """)
    Page<Vacancy> findAllActiveOrderByCreatedDate(Pageable pageable);
}