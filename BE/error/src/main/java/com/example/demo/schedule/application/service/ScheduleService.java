package com.example.demo.schedule.application.service;

import com.example.demo.schedule.application.dto.CreateScheduleRequest;
import com.example.demo.schedule.application.dto.CreateScheduleResponse;
import com.example.demo.schedule.application.dto.SpecificScheduleResopnse;
import com.example.demo.schedule.application.dto.YearCalendarResponse;
import com.example.demo.schedule.application.model.ScheduleModel;
import com.example.demo.schedule.application.model.converter.ScheduleEntityConverter;
import com.example.demo.schedule.application.model.converter.ScheduleRequestConverter;
import com.example.demo.schedule.application.model.converter.ScheduleResponseConverter;
import com.example.demo.schedule.application.usecase.CreateScheduleUsecase;
import com.example.demo.schedule.application.usecase.GetSpecificScheduleUsecase;
import com.example.demo.schedule.persistence.ScheduleEntity;
import com.example.demo.schedule.persistence.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor // -> 이걸 사용하니까 requestConverter 에러가 발생하지 않음.
@Transactional(readOnly = true) // 왜 service 제일 상단에서 트랜잭션은 리드 온리인지? -> 의심하는 부분은 해당 서비스 모든 메서드는 리드 온리 특정 메서드만 라이트 가능하게끔 트랜잭션널 별도 정의하는건가?
public class ScheduleService implements CreateScheduleUsecase,
                                        GetSpecificScheduleUsecase {

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

    @Override
    public SpecificScheduleResopnse getSpecificSchedule(final Long eventId) {
        ScheduleModel model = findSchedule(eventId);
        return responseConverter.from(model);
        // 이게 계속 yyyy-MM-dd 형식으로 들어오지 않았다.
        // SpecificScheduleResponse의 start,end Date의 타입을 바꾸어 주었다.
    }

//    @Override
//    public YearCalendarResponse getYearSchedule(final int year) {
//        //
//        YearCalendarResponse response = entityConverter.from();
//        return null;
//    }

    private ScheduleModel findSchedule(final Long eventId) {
        return scheduleRepository
                .findById(eventId)
                .map(entityConverter::from) //여기서 왜 Optional로 감싸야 하는지 그런데 또 왜 orElseThrow를 던지면 optional로 안감싸도 되는건지?
                .orElseThrow(() -> new NoSuchElementException("no found eventId :" + eventId));
    }
}

