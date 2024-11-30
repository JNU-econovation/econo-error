package com.example.demo.filter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface FilterRepository extends JpaRepository<FilterEntity, Long> {
    //@Query("select p from FilterEntity p") Stream<FilterEntity> streamAll();
    @Query("select p from FilterEntity p where p.memberId = :memberId")
    Stream<FilterEntity> streamAll(@Param("memberId") Long memberId);

    @Query("select f.filterColor from FilterEntity f where f.filterId = :filterId")
    String findFilterColorByFilterId(@Param("filterId") Long filterId);

    @Query("SELECT f.filterColor FROM FilterEntity f WHERE f.filterId = :filterId")
    String findFilterColor(@Param("filterId") Long filterId);

}
