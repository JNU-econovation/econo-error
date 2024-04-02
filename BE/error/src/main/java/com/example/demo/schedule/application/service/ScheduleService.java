package com.example.demo.schedule.application.service;

import com.example.demo.schedule.application.dto.*;
import com.example.demo.schedule.application.model.ScheduleModel;
import com.example.demo.schedule.application.model.converter.ScheduleEntityConverter;
import com.example.demo.schedule.application.model.converter.ScheduleRequestConverter;
import com.example.demo.schedule.application.model.converter.ScheduleResponseConverter;
import com.example.demo.schedule.application.usecase.*;
import com.example.demo.schedule.persistence.ScheduleEntity;
import com.example.demo.schedule.persistence.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService implements CreateScheduleUsecase,
                                        GetSpecificScheduleUsecase,
                                        GetYearScheduleUsecase,
                                        GetMonthScheduleUsecase,
                                        UpdateScheduleUsecase,
                                        DeleteScheduleUsecase {

    private final ScheduleRequestConverter requestConverter;
    private final ScheduleEntityConverter entityConverter;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleResponseConverter responseConverter;


    @Override
    @Transactional
    public CreateScheduleResponse create(final CreateScheduleRequest request){

        ScheduleModel model = requestConverter.from(request);
        Long saveId = createSchedule(model);

        return responseConverter.from(saveId);
    }

    private Long createSchedule(ScheduleModel model) {
        ScheduleEntity entity = entityConverter.toEntity(model);
        ScheduleEntity save = scheduleRepository.save(entity);
        return save.getEventId();
    }

    @Override
    @Transactional
    public UpdateScheduleResponse update(
            final Long eventId, final UpdateScheduleRequest request) {

        ScheduleModel model = findSchedule(eventId);
        ScheduleModel requestModel = requestConverter.from(eventId, request);
        updateSchedule(model, requestModel);

        return responseConverter.fromUpdate(model.getEventId());
    }

    @Override
    @Transactional
    public void delete(final Long eventId) {
        ScheduleModel schedule = findSchedule(eventId);
        scheduleRepository.deleteById(schedule.getEventId());
    }


    private void updateSchedule(ScheduleModel model, ScheduleModel requestModel) {
        ScheduleModel update = model.update(requestModel);
        ScheduleEntity entity = entityConverter.toEntity(update);
        scheduleRepository.save(entity);
    }


    @Override
    public SpecificScheduleResopnse getSpecificSchedule(final Long eventId) {
        ScheduleModel model = findSchedule(eventId);
        return responseConverter.from(model);
    }

    private ScheduleModel findSchedule(final Long eventId) {
        return scheduleRepository
                .findById(eventId)
                .map(entityConverter::from) //여기서 왜 Optional로 감싸야 하는지 그런데 또 왜 orElseThrow를 던지면 optional로 안감싸도 되는건지?
                .orElseThrow(() -> new NoSuchElementException("no found eventId :" + eventId));
    }



    @Override
    public List<YearCalendarResponse> getYearSchedule(final int year) {
        List<ScheduleModel> model = filterEntitiesByYear(year);

        return responseConverter.toYearModel(model);
    }

    private List<ScheduleModel> filterEntitiesByYear(final int year) {
        Stream<ScheduleEntity> entities = scheduleRepository.streamAll();
        return entities
                .filter(entity -> entity.getEventStartDate().getYear() == year)
                .map(entityConverter::from)
                .collect(Collectors.toList());
    }



    @Override
    public List<MonthCalendarResponse> getMonthSchedule(final int year, final int month) {
        List<ScheduleModel> model = filterEntitiesByMonth(year, month);
        return responseConverter.toMonthModel(model);
    }

    private List<ScheduleModel> filterEntitiesByMonth(final int year, final int month) {
        Stream<ScheduleEntity> entities = scheduleRepository.streamAll();
        return entities
                .filter(entity -> (entity.getEventStartDate().getYear() == year) && (entity.getEventStartDate().getMonthValue() == month))
                .map(entityConverter::from)
                .collect(Collectors.toList());
    }



}

