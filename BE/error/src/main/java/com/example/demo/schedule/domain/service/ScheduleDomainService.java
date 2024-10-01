package com.example.demo.schedule.domain.service;

import com.example.demo.schedule.domain.model.ScheduleModel;
import com.example.demo.schedule.infrastructure.ScheduleEntityConverter;
import com.example.demo.schedule.infrastructure.persistence.ScheduleEntity;
import com.example.demo.schedule.domain.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class ScheduleDomainService {


    private final ScheduleEntityConverter entityConverter;
    private final ScheduleRepository scheduleRepository;


    public Long create(ScheduleModel model) {
        ScheduleEntity entity = entityConverter.toEntity(model);
        ScheduleEntity save = scheduleRepository.save(entity);
        return save.getEventId();
    }

    public void update(ScheduleModel model, ScheduleModel requestModel) {
        ScheduleModel update = model.update(requestModel);
        ScheduleEntity entity = entityConverter.toEntity(update);
        scheduleRepository.save(entity);
    }

    public void delete(Long eventId) {
        scheduleRepository.deleteById(eventId);
    }

    public ScheduleModel findSchedule(final Long eventId) {
        return scheduleRepository
                .findById(eventId)
                .map(entityConverter::from)
                .orElseThrow(() -> new NoSuchElementException("no found eventId :" + eventId));
    }


    public List<ScheduleModel> filterPublic() {
        Stream<ScheduleEntity> entity = scheduleRepository.streamAllPublic();
        return entity
                .map(entityConverter::from)
                .collect(Collectors.toList());
    }

    public List<ScheduleModel> filterPrivate() {
        Stream<ScheduleEntity> entity = scheduleRepository.streamAllPrivate();
        return entity
                .map(entityConverter::from)
                .collect(Collectors.toList());
    }
}
