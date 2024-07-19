package com.example.demo.auth.infra.oauth.slack.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SlackChatPostMessageResponse implements SlackApiResponse {

    private boolean ok;

    @JsonProperty("channel")
    private String channel;

    @JsonProperty("ts")
    private String timestamp;

    private Message message;

    private String error;

    @Override
    public String getError() {
        return error;
    }

    public String getMessage() {
        return message.getText();
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Message {
        private String text;
        private String userName;
        private String botId;
        private Attachment[] attachments;
        private String type;
        private String subType;
        private String ts;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class Attachment {
        private String text;
        private String id;
        private String fallback;
    }
}