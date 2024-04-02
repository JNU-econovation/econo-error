package com.example.demo.schedule.application.model.converter;

import com.example.demo.schedule.application.dto.CreateScheduleResponse;
import com.example.demo.schedule.application.dto.SpecificScheduleResopnse;
import com.example.demo.schedule.application.dto.YearCalendarResponse;
import com.example.demo.schedule.application.model.ScheduleModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleResponseConverter {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public CreateScheduleResponse from(Long eventId) {
        return CreateScheduleResponse.builder().eventId(eventId).build();
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

    public List<YearCalendarResponse> toModel(List<ScheduleModel> models) {

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
}
