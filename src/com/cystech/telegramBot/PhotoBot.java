package com.cystech.telegramBot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class PhotoBot extends TelegramLongPollingBot {

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "PhotoBot";
	}

	@Override
	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		if (update.hasMessage() && update.getMessage().hasText()) {
			String text = update.getMessage().getText();
			long chatId = update.getMessage().getChatId();

			if (text.equals("/start")) {
				SendMessage message = new SendMessage();
				message.setChatId(chatId);
				message.setText(text);
				
				try {
					execute(message);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}else if(text.equals("/pic")){
				SendPhoto message = new SendPhoto();
				message.setChatId(chatId);
				message.setPhoto("AgADAgAD6qcxGwnPsUgOp7-MvnQ8GecvSw0ABGvTl7ObQNPNX7UEAAEC");
				message.setCaption("Photo");
				
				try{
					sendPhoto(message);
				}catch(TelegramApiException e){
					e.printStackTrace();
				}
			}else if(text.equals("/markup")){
				SendMessage message = new SendMessage();
				message.setChatId(chatId);
				message.setText("Here is the keyboard");
				
				ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
				List<KeyboardRow> keyboard = new ArrayList<>();
				KeyboardRow row = new KeyboardRow();
				
				row.add("Row 1 Button 1");
				row.add("Row 1 Button 2");
				
				keyboard.add(row);
				row = new KeyboardRow();
				
				row.add("Row 2 Button 1");
				row.add("Row 2 Button 2");
				
				keyboard.add(row);
				
				keyboardMarkup.setKeyboard(keyboard);
				message.setReplyMarkup(keyboardMarkup);
				
				try{
					execute(message);
				}catch(TelegramApiException e){
					e.printStackTrace();
				}
			}else if(text.equals("Row 1 Button 1")){
				SendPhoto message = new SendPhoto();
				message.setChatId(chatId);
				message.setPhoto("AgADAgAD6qcxGwnPsUgOp7-MvnQ8GecvSw0ABGvTl7ObQNPNX7UEAAEC");
				message.setCaption("Photo");
				
				try{
					sendPhoto(message);
				}catch(TelegramApiException e){
					e.printStackTrace();
				}
			}else if(text.equals("/hide")){
				SendMessage message = new SendMessage();
				message.setChatId(chatId);
				message.setText("Keyboard Hidden");
				ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
				message.setReplyMarkup(keyboardMarkup);
				
				try{
					execute(message);
				}catch(TelegramApiException e){
					e.printStackTrace();
				}
			}else{
				SendMessage message = new SendMessage();
				message.setChatId(chatId);
				message.setText("Unknown Command");
				
				try{
					execute(message);
				}catch(TelegramApiException e){
					e.printStackTrace();
				}
			}

		}
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "12345:qwertyuiopASDGFHKMK";
	}
}
