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

    Page<Vacancy> findByIsActiveTrueOrderByCreatedDateDesc(Pageable pageable);

    Page<Vacancy> findByAuthorId(Long authorId, Pageable pageable);

    Page<Vacancy> findByAuthorIdOrderByCreatedDateDesc(Long authorId, Pageable pageable);

    Page<Vacancy> findByCategoryId(Long categoryId, Pageable pageable);

    @Query(
            value = """
                    select v
                    from Vacancy v
                    where v.isActive = true
                    order by (
                        select count(r)
                        from RespondedApplicant r
                        where r.vacancy.id = v.id
                    ) desc
                    """,
            countQuery = """
                    select count(v)
                    from Vacancy v
                    where v.isActive = true
                    """
    )
    Page<Vacancy> findAllActiveOrderByResponsesCount(Pageable pageable);

    @Query(
            value = """
                    select v
                    from Vacancy v
                    where v.author.id = :authorId
                    order by (
                        select count(r)
                        from RespondedApplicant r
                        where r.vacancy.id = v.id
                    ) desc
                    """,
            countQuery = """
                    select count(v)
                    from Vacancy v
                    where v.author.id = :authorId
                    """
    )
    Page<Vacancy> findByAuthorIdOrderByResponsesCount(Long authorId, Pageable pageable);
}