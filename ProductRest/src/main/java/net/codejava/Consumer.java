package net.codejava;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	@KafkaListener(topics= "Topic" , groupId="NikSup")
	public void listenToTopic(String msg)
	{
		System.out.println("The message received is"+ msg);
	}
}
