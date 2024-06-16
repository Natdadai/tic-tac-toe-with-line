package com.application.tictactoe.utils;

import com.linecorp.bot.messaging.model.TextMessage;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@AllArgsConstructor
public class MessageUtils {
    private MessageSource messageSource;

    public TextMessage getMessage(String code) {
        return new TextMessage(messageSource.getMessage(code, null, Locale.ENGLISH));
    }

    public TextMessage getMessage(String code, Object... args) {
        MessageSourceAccessor accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
        return new TextMessage(accessor.getMessage(code, args));
    }


}
