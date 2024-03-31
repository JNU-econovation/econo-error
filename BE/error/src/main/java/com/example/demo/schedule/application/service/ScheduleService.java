package com.example.demo.schedule.application.service;

import com.example.demo.schedule.application.dto.CreateScheduleRequest;
import com.example.demo.schedule.application.dto.CreateScheduleResponse;
import com.example.demo.schedule.application.dto.YearCalendarResponse;
import com.example.demo.schedule.application.model.ScheduleModel;
import com.example.demo.schedule.application.model.converter.ScheduleEntityConverter;
import com.example.demo.schedule.application.model.converter.ScheduleRequestConverter;
import com.example.demo.schedule.application.model.converter.ScheduleResponseConverter;
import com.example.demo.schedule.application.usecase.CreateScheduleUsecase;
import com.example.demo.schedule.persistence.ScheduleEntity;
import com.example.demo.schedule.persistence.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor // -> 이걸 사용하니까 requestConverter 에러가 발생하지 않음.
@Transactional(readOnly = true) // 왜 service 제일 상단에서 트랜잭션은 리드 온리인지? -> 의심하는 부분은 해당 서비스 모든 메서드는 리드 온리 특정 메서드만 라이트 가능하게끔 트랜잭션널 별도 정의하는건가?
public class ScheduleService implements CreateScheduleUsecase {

    private final ScheduleRequestConverter requestConverter; //TODO 여기서 왜 private final로 선언하는지 고민해 보자.
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

    public YearCalendarResponse getYearSchedule(YearCalendarResponse response) {
        //

        return null;
    }

    private ScheduleModel findSchedule(final Long eventId) {
        return scheduleRepository
                .findById(eventId)
                .map(entityConverter::from) //여기서 왜 Optional로 감싸야 하는지
                .orElseThrow(() -> new NoSuchElementException("no found eventId" + eventId));

    }
}

