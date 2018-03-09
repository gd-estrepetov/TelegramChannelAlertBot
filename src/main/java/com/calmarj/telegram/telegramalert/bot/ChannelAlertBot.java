package com.calmarj.telegram.telegramalert.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class ChannelAlertBot extends TelegramLongPollingBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelAlertBot.class);

    private final String botToken;

    public ChannelAlertBot(String botToken) {
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(update.getMessage().getText());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            LOGGER.error("Error happened during sending message", e);
        }
    }

    @Override
    public String getBotUsername() {
        return "ChannelAlertBot";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
