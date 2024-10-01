package com.example.demo.schedule.application.service;

import com.example.demo.schedule.application.dto.*;
import com.example.demo.schedule.domain.model.ScheduleModel;
import com.example.demo.schedule.domain.model.converter.ScheduleRequestConverter;
import com.example.demo.schedule.domain.model.converter.ScheduleResponseConverter;
import com.example.demo.schedule.domain.service.ScheduleDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRequestConverter requestConverter;
    private final ScheduleResponseConverter responseConverter;
    private final ScheduleDomainService domainService;


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
        return responseConverter.from(model);
    }


    public List<AllPublicCalendarResponse> getPublicSchedule() {
        List<ScheduleModel> model = domainService.filterPublic();
        return responseConverter.toPublicModel(model);
    }


    public List<AllPrivateCalendarResponse> getPrivateSchedule() {
        List<ScheduleModel> model = domainService.filterPrivate();
        return responseConverter.toPrivateModel(model);
    }
}