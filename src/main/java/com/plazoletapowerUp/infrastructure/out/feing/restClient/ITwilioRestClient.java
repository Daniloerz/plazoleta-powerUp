package com.plazoletapowerUp.infrastructure.out.feing.restClient;

import com.plazoletapowerUp.infrastructure.out.feing.dto.MessageRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "twilio-powerup")
public interface ITwilioRestClient {

    @PostMapping("/twilio/send-message")
    Boolean sendMessage (MessageRequestDto messageRequestDto);
}
