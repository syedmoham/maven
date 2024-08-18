package com.syedm.springtests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class SpringTestsApplication implements CommandLineRunner {

	@Autowired
	TaskExecutorExample taskExecutorExample;

	public static void main(String[] args) {
		SpringApplication.run(SpringTestsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		taskExecutorExample.printMessage();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	@GetMapping("/testapi")
	public ResponseEntity<Person> runTestApi(@RequestParam String name, @RequestParam String email)  {
		Person person = new Person(name, email, "NJ");
		return new ResponseEntity(person, HttpStatus.OK);
	}
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {
	private String name;
	private String email;
	private String state;
}

@Component
class TaskExecutorExample  {
	// inner runnable class which whose run function can be executed
	private class MessagePrinterTask implements Runnable  {
		private String message;
		public MessagePrinterTask(String message)  {
			this.message = message;
		}
		@Override
		public void run() {
			System.out.println(message);
		}
	}

	private TaskExecutor taskExecutor;

	public TaskExecutorExample(TaskExecutor taskExecutor)  {
		this.taskExecutor = taskExecutor;
	}

	public void printMessage()  {
		for (int i = 0; i < 10; i++)  {
			taskExecutor.execute(new MessagePrinterTask("Message" + i));
		}
	}
}