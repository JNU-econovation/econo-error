package com.example.demo.schedule.domain.model.converter;

import com.example.demo.filter.persistence.FilterRepository;
import com.example.demo.schedule.application.dto.*;
import com.example.demo.schedule.domain.model.ScheduleModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleResponseConverter {

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private FilterRepository filterRepository;

    public CreateScheduleResponse from(Long eventId) {
        return CreateScheduleResponse.builder().eventId(eventId).build();
    }


    public UpdateScheduleResponse fromUpdate(Long eventId) {
        return UpdateScheduleResponse.builder().eventId(eventId).build();
    }


    public SpecificScheduleResopnse from(ScheduleModel model, String filterColor) {
        return SpecificScheduleResopnse.builder()
                .eventId(model.getEventId())
                .eventName(model.getEventName())
                //.eventStartDate(String.valueOf(LocalDateTime.parse(model.getEventStartDate().toString(), FORMATTER)))
                //.eventEndDate(String.valueOf(LocalDateTime.parse(model.getEventEndDate().toString(), FORMATTER)))
                .eventStartDate(model.getEventStartDate().toString())
                .eventEndDate(model.getEventEndDate().toString())
                .eventInfo(model.getEventInfo())
                .eventPlace(model.getEventPlace())
                //.filterColor(model.getFilter() != null ? model.getFilter().getFilterColor() : null)
                //.filterColor(filterRepository.findFilterColor(model.getFilterId()))
                .filterColor(filterColor)
                .build();
    }

    public List<AllPublicCalendarResponse> toPublicModel(List<ScheduleModel> models) {
        return models.stream()
                .map(this::toPublicCalendarResponse)
                .collect(Collectors.toList());
    }


    private AllPublicCalendarResponse toPublicCalendarResponse(ScheduleModel model) {
        return AllPublicCalendarResponse.builder()
                .eventId(model.getEventId())
                .eventName(model.getEventName())
                .eventStartDate(model.getEventStartDate().toString())
                .eventEndDate(model.getEventEndDate().toString())
                .eventPlace(model.getEventPlace())
                .eventInfo(model.getEventInfo())
                .scheduleType(model.getScheduleType())
                .filterId(model.getFilter() != null ? model.getFilter().getFilterId() : null)
                .filterName(model.getFilter() != null ? model.getFilter().getFilterName() : null)
                .filterColor(model.getFilter() != null ? model.getFilter().getFilterColor() : null)
                .build();
    }


    public List<AllPrivateCalendarResponse> toPrivateModel(List<ScheduleModel> models) {
        return models.stream()
                .map(this::toPrivateCalendarResponse)
                .collect(Collectors.toList());
    }


    private AllPrivateCalendarResponse toPrivateCalendarResponse(ScheduleModel model) {
        return AllPrivateCalendarResponse.builder()
                .eventId(model.getEventId())
                .eventName(model.getEventName())
                .eventStartDate(model.getEventStartDate().toString())
                .eventEndDate(model.getEventEndDate().toString())
                .eventPlace(model.getEventPlace())
                .eventInfo(model.getEventInfo())
                .scheduleType(model.getScheduleType())
                .filterId(model.getFilter() != null ? model.getFilter().getFilterId() : null)
                .filterName(model.getFilter() != null ? model.getFilter().getFilterName() : null)
                .filterColor(model.getFilter() != null ? model.getFilter().getFilterColor() : null)
                .build();
    }
}
