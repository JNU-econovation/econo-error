package com.example.demo.schedule.persistence;

import com.example.demo.schedule.application.model.ScheduleModel;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
//import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    @Query("select p from ScheduleEntity p") Stream<ScheduleEntity> streamAll();

    @Query("SELECT s FROM ScheduleEntity s WHERE s.scheduleType = 'PUBLIC'")
    Stream<ScheduleEntity> streamAllPublic();

    @Query("SELECT s FROM ScheduleEntity s WHERE s.scheduleType = 'PRIVATE'")
    Stream<ScheduleEntity> streamAllPrivate();

    @Query("SELECT p.eventId, p.eventName, p.eventStartDate, p.createdDate FROM ScheduleEntity p")
    List<ScheduleModel> getYearCalendar();
}
