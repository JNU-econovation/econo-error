package com.example.demo.schedule.application.service;

import com.example.demo.filter.application.service.FilterService;
import com.example.demo.schedule.application.dto.*;
import com.example.demo.schedule.domain.model.ScheduleModel;
import com.example.demo.schedule.domain.model.converter.ScheduleRequestConverter;
import com.example.demo.schedule.domain.model.converter.ScheduleResponseConverter;
import com.example.demo.schedule.domain.service.ScheduleDomainService;
import com.example.demo.schedule.infrastructure.persistence.ScheduleEntity;
import com.example.demo.schedule.infrastructure.persistence.ScheduleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRequestConverter requestConverter;
    private final ScheduleResponseConverter responseConverter;
    private final ScheduleDomainService domainService;
    private final ScheduleJpaRepository scheduleJpaRepository;
    private final FilterService filterService;


    @Transactional
    public CreateScheduleResponse createSchedule(final CreateScheduleRequest request) {
        ScheduleModel model = requestConverter.from(request);
        Long saveId = domainService.create(model);
        return responseConverter.from(saveId);
    }


    @Transactional
    public UpdateScheduleResponse updateSchedule(final Long eventId, final UpdateScheduleRequest request) {
        ScheduleModel model = domainService.findSchedule(eventId);
        ScheduleModel requestModel = requestConverter.from(eventId, request);
        domainService.update(model, requestModel);
        return responseConverter.fromUpdate(model.getEventId());
    }


    @Transactional
    public void deleteSchedule(final Long eventId) {
        ScheduleModel schedule = domainService.findSchedule(eventId);
        domainService.delete(schedule.getEventId());
    }


    public SpecificScheduleResopnse getSpecificSchedule(final Long eventId) {
        ScheduleModel model = domainService.findSchedule(eventId);
        String filterColor = scheduleJpaRepository.findFilterColor(model.getFilterId());
        return responseConverter.from(model, filterColor);
    }


    public List<AllPublicCalendarResponse> getPublicSchedule() {
        List<ScheduleModel> model = domainService.filterPublic();
        return responseConverter.toPublicModel(model);
    }


    public List<AllPrivateCalendarResponse> getPrivateSchedule() {
        List<ScheduleModel> model = domainService.filterPrivate();
        return responseConverter.toPrivateModel(model);
    }

    public List<ScheduleEntity> findWeekendSchedule() {
        //List<WeekendSchedule> schedules = new ArrayList<>();

//        List<ScheduleEntity> test = scheduleJpaRepository.findWeekendPublicSchedule();
//        if (test.size() == 0) {
//            System.out.println("empty");
//        }
//        return test;

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(5);

        List<ScheduleEntity> schedules = scheduleJpaRepository.findWeekPublic(startDate, endDate);
        return schedules;
    }
}