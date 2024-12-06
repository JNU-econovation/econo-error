package com.example.demo.schedule.infrastructure.persistence;

import com.example.demo.filter.persistence.FilterEntity;
import com.example.demo.schedule.domain.ScheduleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class ScheduleJpaRepository implements ScheduleRepository {

    private final EntityManager em;

    public ScheduleJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public ScheduleEntity save(ScheduleEntity entity) {
        if (entity.getEventId() == null) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    public List<ScheduleEntity> findOneSemesterPublicSchedule(LocalDateTime requestDate) {
        int month = requestDate.getMonthValue();
        int year = requestDate.getYear();

        LocalDateTime startDate;
        LocalDateTime endDate;

        if (month >= 2 && month <= 7) {
            // 2월 ~ 7월
            startDate = LocalDateTime.of(year, 2, 1, 0, 0);
            endDate = LocalDateTime.of(year, 7, 31, 23, 59, 59);
        } else if (month >= 8 && month <= 12) {
            // 8월 ~ 다음해 1월
            startDate = LocalDateTime.of(year, 8, 1, 0, 0);
            endDate = LocalDateTime.of(year + 1, 1, 31, 23, 59, 59);
        } else { // month == 1
            // 전년도 8월 ~ 해당 연도 1월
            startDate = LocalDateTime.of(year - 1, 8, 1, 0, 0);
            endDate = LocalDateTime.of(year, 1, 31, 23, 59, 59);
        }

        String jpql = "SELECT s FROM ScheduleEntity s " +
                "WHERE s.eventStartDate BETWEEN :startDate AND :endDate";

        TypedQuery<ScheduleEntity> query = em.createQuery(jpql, ScheduleEntity.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        return query.getResultList();
    }




    public List<ScheduleEntity> findWeekPublic(LocalDateTime startDate, LocalDateTime endDate) {
        return em.createQuery("SELECT s FROM ScheduleEntity s " +
                        "WHERE s.scheduleType = :scheduleType " +
                        "AND s.eventStartDate BETWEEN :startDate AND :endDate", ScheduleEntity.class)
                .setParameter("scheduleType", "PUBLIC") // scheduleType 파라미터 설정
                .setParameter("startDate", startDate)  // startDate 파라미터 설정
                .setParameter("endDate", endDate)      // endDate 파라미터 설정
                .getResultList();
    }



    public List<ScheduleEntity> findWeekendPublicSchedule() {
        List<ScheduleEntity> entity = em.createQuery("SELECT s FROM ScheduleEntity s \n" +
                "WHERE s.scheduleType = 'PUBLIC' \n" +
                "AND s.eventStartDate BETWEEN CURRENT_DATE AND CURRENT_DATE + 5\n", ScheduleEntity.class).getResultList();
        return entity;
    }


    public Stream<ScheduleEntity> streamAllPublic() {
        Stream<ScheduleEntity> entity = em.createQuery("SELECT s FROM ScheduleEntity s WHERE s.scheduleType = 'PUBLIC'", ScheduleEntity.class).getResultStream();
        return entity;
    }


    public Stream<ScheduleEntity> streamAllPrivate() {
        Stream<ScheduleEntity> entities = em.createQuery("SELECT s FROM ScheduleEntity s WHERE s.scheduleType = 'PRIVATE'", ScheduleEntity.class)
                .getResultStream();
        return entities;
    }

    @Override
    public Optional<ScheduleEntity> findById(Long eventId) {
        Optional<ScheduleEntity> schedule = em.createQuery("SELECT s FROM ScheduleEntity s WHERE s.eventId =:eventId", ScheduleEntity.class)
                .setParameter("eventId", eventId)
                .getResultStream()
                .findFirst();
        return schedule;
    }

    @Override
    public void deleteById(Long eventId) {
        ScheduleEntity entity = em.find(ScheduleEntity.class, eventId);
        if (entity != null) {
            em.remove(entity);
        }
    }


    @Override
    public String findFilterColor(Long filterId) {
        return em.createQuery("SELECT f.filterColor FROM FilterEntity f WHERE f.filterId = :filterId", String.class)
                .setParameter("filterId", filterId)
                .getResultStream()
                .findFirst() // 첫 번째 결과 가져오기
                .orElse(null);
    }

    @Override
    public String findFilterName(Long filterId) {
        return em.createQuery("SELECT f.filterName FROM FilterEntity f WHERE f.filterId = :filterId", String.class)
                .setParameter("filterId", filterId)
                .getResultStream()
                .findFirst() // 첫 번째 결과 가져오기
                .orElse(null);
    }
}
