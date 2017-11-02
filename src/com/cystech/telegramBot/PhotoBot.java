package com.cystech.telegramBot;

import java.util.Comparator;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
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
		}else if(update.hasMessage() && update.getMessage().hasPhoto()){
			long chatId = update.getMessage().getChatId();
			List<PhotoSize> photos = update.getMessage().getPhoto();
			String fId = photos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst().orElse(null).getFileId();
			int fWidth = photos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst().orElse(null).getWidth();
			int fHeight = photos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst().orElse(null).getHeight();
			String caption = "File ID: " + fId + "\nWidth: " + String.valueOf(fWidth) + "\nHeight: " + String.valueOf(fHeight);
			SendPhoto msg = new SendPhoto();
			msg.setChatId(chatId);
			msg.setPhoto(fId);
			msg.setCaption(caption);
			
			try{
				sendPhoto(msg);
			}catch(TelegramApiException e){
				
			}
		}
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "12345:qwertyuiopASDGFHKMK";
	}
}
