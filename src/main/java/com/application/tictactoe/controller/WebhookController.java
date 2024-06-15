package com.application.tictactoe.controller;


import com.application.tictactoe.service.MessageService;
import com.linecorp.bot.messaging.model.Message;
import com.linecorp.bot.spring.boot.handler.annotation.EventMapping;
import com.linecorp.bot.spring.boot.handler.annotation.LineMessageHandler;
import com.linecorp.bot.webhook.model.MessageEvent;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@LineMessageHandler
@AllArgsConstructor
public class WebhookController {
    private final MessageService messageService;

    @EventMapping
    public List<Message> handleMessageEvent(MessageEvent event) {
        return messageService.handleMessage(event.source().userId(), event.message());
    }
}

