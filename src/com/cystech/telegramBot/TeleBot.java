package com.cystech.telegramBot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class TeleBot extends TelegramLongPollingBot {

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "TeleBot";
	}

	@Override
	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		if(update.hasMessage() && update.getMessage().hasText()){
			String text = update.getMessage().getText();
			long chatId = update.getMessage().getChatId();
			
			SendMessage message = new SendMessage();
			message.setChatId(chatId);
			message.setText(text);
			
			try{
				execute(message);
			}catch(TelegramApiException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "12345:qwertyuiopASDGFHKMK";
	}
	
}
