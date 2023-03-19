package com.plazoletapowerUp.infrastructure.out.feing.adapter;

import com.plazoletapowerUp.domain.client.ITwilioClientPort;
import com.plazoletapowerUp.domain.model.MessageModel;
import com.plazoletapowerUp.infrastructure.out.feing.restClient.ITwilioRestClient;
import com.plazoletapowerUp.infrastructure.out.feing.mapper.IMessageRequestMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TwilioClientAdapter implements ITwilioClientPort {

    private final ITwilioRestClient twilioRestClient;
    private final IMessageRequestMapper messageRequestMapper;

    @Override
    public void sendMessage(MessageModel messageModel) {
        twilioRestClient.sendMessage(messageRequestMapper.toRequestDto(messageModel));
    }
}
