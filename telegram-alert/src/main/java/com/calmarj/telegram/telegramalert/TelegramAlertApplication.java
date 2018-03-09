package com.calmarj.telegram.telegramalert;

import com.calmarj.telegram.telegramalert.bot.ChannelAlertBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TelegramAlertApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramAlertApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TelegramAlertApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ApiContextInitializer.init();

        ApiContext.register(BotSession.class, DefaultBotSession.class);
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new ChannelAlertBot("551850110:AAFVtWmBIMl6TnfMIU9wTLS8LWmouq_VNOk"));
        } catch (TelegramApiException e) {
            LOGGER.error("Failed to register bot", e);
        }
    }
}
