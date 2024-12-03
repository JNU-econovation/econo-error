package com.example.demo.schedule.infrastructure;

import com.example.demo.auth.infra.oauth.slack.exception.SlackApiException;
import com.example.demo.schedule.application.service.ScheduleService;
import com.example.demo.schedule.infrastructure.persistence.ScheduleEntity;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:/application.properties")
public class SlackService {

    @Value("${spring.config.activate.on-profile.oauth.provider.slack.token}")
    private String slackToken;
    private final ScheduleService scheduleService;
    private final static String CHANNEL_NAME = "에코노베이션-일정-소개-페이지";

    public void sendSlackMessage(String message, String channel){


        try{
            MethodsClient methods = Slack.getInstance().methods(slackToken);

            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(CHANNEL_NAME)
                    .text(message)
                    .build();

            methods.chatPostMessage(request);

            log.info("Slack " + channel + " 에 메시지 보냄");

        } catch (SlackApiException | IOException e) {
            log.error(e.getMessage());
        } catch (com.slack.api.methods.SlackApiException e) {
            throw new RuntimeException(e);
        }
    }


    public String makeSlackMessage() {
        log.info("send slack message");
        List<ScheduleEntity> schedules = scheduleService.findWeekendSchedule();

        if (schedules.isEmpty()) {
            return "이번주는 일정이 존재하지 않습니다.";
        }

        StringBuilder messageBuilder = new StringBuilder();

        for (int i = 0; i < schedules.size(); i++) {
            if (i > 0) {
                messageBuilder.append("\n\n"); // 일정 사이에 한 줄 추가
            }
            messageBuilder.append(formatSchedule(schedules.get(i)));
        }

        return messageBuilder.toString();
    }

    public String formatSchedule(ScheduleEntity schedule) {
        LocalDateTime startDate = schedule.getEventStartDate();

        // 날짜와 시간을 지정된 형식으로 변환
        String formattedDate = startDate.format(DateTimeFormatter.ofPattern("M월 d일 (E) HH:mm", Locale.KOREAN));

        return String.format("(%s)\n- 일시: %s\n- 장소: %s",
                schedule.getEventName(),
                formattedDate,
                schedule.getEventPlace());
    }
}
