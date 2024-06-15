package com.application.tictactoe.service;

import com.linecorp.bot.messaging.model.Message;
import com.linecorp.bot.webhook.model.MessageContent;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MessageService {

    List<Message> handleMessage(String userLineId, MessageContent message);
}
