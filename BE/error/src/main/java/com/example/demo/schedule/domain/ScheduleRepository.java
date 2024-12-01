package com.example.demo.schedule.domain;


import com.example.demo.schedule.infrastructure.persistence.ScheduleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.stream.Stream;


public interface ScheduleRepository {

    ScheduleEntity save(ScheduleEntity entity);

    Stream<ScheduleEntity> streamAllPublic();

    Stream<ScheduleEntity> streamAllPrivate();

    Optional<ScheduleEntity> findById(Long eventId);

    void deleteById(Long eventId);

    String findFilterColor(Long filterId);

    String findFilterName(Long filterId);
}
