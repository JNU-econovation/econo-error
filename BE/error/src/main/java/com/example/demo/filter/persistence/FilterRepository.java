package com.example.demo.filter.persistence;

import com.example.demo.schedule.persistence.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface FilterRepository extends JpaRepository<FilterEntity, Long> {
    @Query("select p from FilterEntity p") Stream<FilterEntity> streamAll();

}
