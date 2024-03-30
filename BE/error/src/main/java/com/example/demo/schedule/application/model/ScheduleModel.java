package com.example.demo.schedule.application.model;


import com.example.demo.common.support.AbstractModel;
import com.example.demo.schedule.persistence.ScheduleType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ScheduleModel implements AbstractModel {

    private Long eventId;
    private String eventName;
    private LocalDateTime eventStartDate;
    private LocalDateTime eventEndDate;
    private String eventInfo;
    //private ScheduleType scheduleType;
    private String eventPlace;

//    private Long createSchedule(){
//
//    }
}
