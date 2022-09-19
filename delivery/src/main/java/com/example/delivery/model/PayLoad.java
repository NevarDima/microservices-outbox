package com.example.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayLoad {

    int id;
    String event;
    @JsonProperty("event_id")
    int eventId;
    String payload;
    @JsonProperty("created_at")
    String createdAt;
}
