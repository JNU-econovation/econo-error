package com.example.demo.schedule.infrastructure.persistence;

import com.example.demo.schedule.domain.ScheduleRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

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
        Optional<ScheduleEntity> schedule = em.createQuery("SELECT s FROM ScheduleEntity s WHERE s.eventId = eventId", ScheduleEntity.class)
                .setParameter("evnetId", eventId)
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
}
