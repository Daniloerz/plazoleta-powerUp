package com.plazoletapowerUp.domain.client;

import com.plazoletapowerUp.domain.model.MessageModel;

public interface ITwilioClientPort {
    void sendMessage(MessageModel messageModel);
}
