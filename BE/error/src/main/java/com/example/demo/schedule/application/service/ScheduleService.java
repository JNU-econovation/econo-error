package com.example.demo.schedule.application.service;

import com.example.demo.filter.application.model.FilterModel;
import com.example.demo.filter.persistence.FilterEntity;
import com.example.demo.schedule.application.dto.*;
import com.example.demo.schedule.application.model.ScheduleModel;
import com.example.demo.schedule.application.model.converter.ScheduleEntityConverter;
import com.example.demo.schedule.application.model.converter.ScheduleRequestConverter;
import com.example.demo.schedule.application.model.converter.ScheduleResponseConverter;
import com.example.demo.schedule.application.usecase.*;
import com.example.demo.schedule.persistence.ScheduleEntity;
import com.example.demo.schedule.persistence.ScheduleRepository;
import com.example.demo.schedule.persistence.ScheduleType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
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
        DeleteScheduleUsecase,
        GetWeekScheduleUsecase,
        GetAllScheduleUsecase
        //GetAllPrivateScheduleUsecase,
        //GetAllPublicScheduleUsecase
        {

    @Override
    public List<MonthCalendarResponse> getMonthSchedule(int year, int month) {
        return null;
    }

    @Override
    public List<YearCalendarResponse> getYearSchedule(int year) {
        return null;
    }

    private final ScheduleRequestConverter requestConverter;
    private final ScheduleEntityConverter entityConverter;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleResponseConverter responseConverter;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public CreateScheduleResponse create(final CreateScheduleRequest request) {

        //ScheduleModel model = modelMapper.map(request, ScheduleModel.class);
        ScheduleModel model = requestConverter.from(request);
        Long saveId = createSchedule(model);
        return responseConverter.from(saveId);
    }

    private Long createSchedule(ScheduleModel model) {

        ScheduleEntity entity = entityConverter.toEntity(model);
        ScheduleEntity save = scheduleRepository.save(entity);
        //Long memberId = save.getMember().getId();
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
                .map(entityConverter::from) //TODO
                .orElseThrow(() -> new NoSuchElementException("no found eventId :" + eventId));
    }

    @Override
    public List<AllCalendarResponse> getAllSchedule() {
        List<ScheduleModel> model = filterEntitiesByAll();
        return responseConverter.toAllModel(model);
    }

    @Override
    public List<AllPublicCalendarResponse> getPublicSchedule() {
        List<ScheduleModel> model = filterPublic();
        return responseConverter.toPublicModel(model);
    }

    private List<ScheduleModel> filterPublic() {
        Stream<ScheduleEntity> entity = scheduleRepository.streamAllPublic();
        return entity
                .map(entityConverter::from)
                .collect(Collectors.toList());
    }


    public List<AllPrivateCalendarResponse> getPrivateSchedule() {
        List<ScheduleModel> model = filterPrivate();
        return responseConverter.toPrivateModel(model);
    }

    private List<ScheduleModel> filterPrivate() {
        Stream<ScheduleEntity> entity = scheduleRepository.streamAllPrivate();
        return entity
                .map(entityConverter::from)
                .collect(Collectors.toList());
    }


    private List<ScheduleModel> filterEntitiesByAll() {
        Stream<ScheduleEntity> entities = scheduleRepository.streamAll();
        return entities
                .map(entityConverter::from)
                .collect(Collectors.toList());
    }




    private List<ScheduleModel> filterEntitiesByMonth(final int year, final int month) {
        Stream<ScheduleEntity> entities = scheduleRepository.streamAll();
        return entities
                .filter(entity -> (entity.getEventStartDate().getYear() == year) && (entity.getEventStartDate().getMonthValue() == month))
                .map(entityConverter::from)
                .collect(Collectors.toList());
    }


    @Override
    public List<WeekCalendarResponse> getWeekSchedule(final int year, final int month, final int day) {
        LocalDate wantFindWeek = LocalDate.of(year, month, day);
        List<ScheduleModel> model = filterEntitiesByWeek(wantFindWeek);
        return responseConverter.toWeekModel(model);
    }


    private List<ScheduleModel> filterEntitiesByWeek(final LocalDate wantFindWeek) {

        Stream<ScheduleEntity> entities = scheduleRepository.streamAll();
        final int weekOfYear = wantFindWeek.get(WeekFields.ISO.weekOfWeekBasedYear());
        return entities
                .filter(entity -> entity.getEventStartDate().toLocalDate().get(WeekFields.ISO.weekOfWeekBasedYear()) == weekOfYear)
                .map(entityConverter::from)
                .collect(Collectors.toList());
    }
}