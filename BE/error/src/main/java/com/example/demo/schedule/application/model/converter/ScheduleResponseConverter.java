package com.example.demo.schedule.application.model.converter;

import com.example.demo.schedule.application.dto.*;
import com.example.demo.schedule.application.model.ScheduleModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ScheduleResponseConverter {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public CreateScheduleResponse from(Long eventId) {
        return CreateScheduleResponse.builder().eventId(eventId).build();
    }

    public UpdateScheduleResponse fromUpdate(Long eventId) {
        return UpdateScheduleResponse.builder().eventId(eventId).build();
    }



    public SpecificScheduleResopnse from(ScheduleModel model) {
        return SpecificScheduleResopnse.builder()
                .eventId(model.getEventId())
                .eventName(model.getEventName())
                .eventStartDate(String.valueOf(LocalDateTime.parse(model.getEventStartDate().toString(), formatter)))
                .eventEndDate(String.valueOf(LocalDateTime.parse(model.getEventEndDate().toString(), formatter)))
                .eventInfo(model.getEventInfo())
                .eventPlace(model.getEventPlace())
                .build();
    }

//    public interface CalendarResponseConverter<R extends CalendarResponse> {
//        List<R> toModel(List<ScheduleModel> models);
//    }
//
//    public <T extends CalendarResponse> List<T> toModel(List<ScheduleModel> models, Function<ScheduleModel, T> responseBuilder) {
//        return models.stream()
//                .map(responseBuilder)
//                .collect(Collectors.toList());
//    }


    public List<YearCalendarResponse> toYearModel(List<ScheduleModel> models) {

        List<YearCalendarResponse> response = new ArrayList<>();
        for (ScheduleModel model : models) {
            YearCalendarResponse schedule = YearCalendarResponse.builder()
                    .eventId(model.getEventId())
                    .eventStartDate(String.valueOf(LocalDateTime.parse(model.getEventStartDate().toString(), formatter)))
                    .eventEndDate(String.valueOf(LocalDateTime.parse(model.getEventEndDate().toString(), formatter)))
                    .build();
            response.add(schedule);
        }
        return response;
    }

    public List<AllCalendarResponse> toAllModel(List<ScheduleModel> models) {
        List<AllCalendarResponse> response = new ArrayList<>();
        for (ScheduleModel model : models) {
            AllCalendarResponse schedule = AllCalendarResponse.builder()
                    .eventId(model.getEventId())
                    .eventName(model.getEventName())
                    .eventStartDate(String.valueOf(LocalDateTime.parse(model.getEventStartDate().toString(), formatter)))
                    .eventEndDate(String.valueOf(LocalDateTime.parse(model.getEventEndDate().toString(), formatter)))
                    .build();
            response.add(schedule);
        }
        return response;
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


    public List<MonthCalendarResponse> toMonthModel(List<ScheduleModel> models) {

        List<MonthCalendarResponse> response = new ArrayList<>();
        for (ScheduleModel model : models) {
            MonthCalendarResponse schedule = MonthCalendarResponse.builder()
                    .eventId(model.getEventId())
                    .eventStartDate(String.valueOf(LocalDateTime.parse(model.getEventStartDate().toString(), formatter)))
                    .eventEndDate(String.valueOf(LocalDateTime.parse(model.getEventEndDate().toString(), formatter)))
                    .build();
            response.add(schedule);
        }
        return response;
    }

    public List<WeekCalendarResponse> toWeekModel(List<ScheduleModel> models) {
        List<WeekCalendarResponse> response = new ArrayList<>();
        for (ScheduleModel model : models) {
            WeekCalendarResponse schedule = WeekCalendarResponse.builder()
                    .eventId(model.getEventId())
                    .eventName(model.getEventName())
                    .eventStartDate(String.valueOf(LocalDateTime.parse(model.getEventStartDate().toString(), formatter)))
                    .eventEndDate(String.valueOf(LocalDateTime.parse(model.getEventEndDate().toString(), formatter)))
                    .build();
            response.add(schedule);
        }
        return response;
    }
}
