package com.cystech.telegramBot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class LoggingTestBot extends TelegramLongPollingBot {

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "LoggingTestBot";
	}

	@Override
	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		if(update.hasMessage() && update.getMessage().hasText()){
			String firstName = update.getMessage().getChat().getFirstName();
			String lastName = update.getMessage().getChat().getLastName();
			String userName = update.getMessage().getChat().getUserName();
			long userId = update.getMessage().getChat().getId();
			
			String text = update.getMessage().getText();
			long chatId = update.getMessage().getChatId();
			String response = text;
			
			SendMessage message = new SendMessage();
			message.setChatId(chatId);
			message.setText(text);
			
			
			log(firstName, lastName, userName, userId, text, response);
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
	
	private static void log(String firstName, String lastName, String userName, long userId, String text, String response){
		System.out.print("\n--------------------");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		System.out.println("Message from: " + firstName + " " + lastName + ", user ID = " + userId + ".");
		System.out.println("Text: " + text);
		System.out.println("Bot response: " + response);
	}
}
