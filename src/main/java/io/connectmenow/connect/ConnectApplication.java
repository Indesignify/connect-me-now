package io.connectmenow.connect;

import static io.connectmenow.connect.model.entities.Status.OFFLINE;
import static io.connectmenow.connect.model.entities.Status.ONLINE;

import io.connectmenow.connect.model.entities.UsersEntity;
import io.connectmenow.connect.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Log4j2
@SpringBootApplication
public class ConnectApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConnectApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {

    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setInterceptors(new ArrayList<>());
//		restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
//			@Override
//			public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
//
//
//			}
//		})

    return restTemplate;
  }

  @Bean
  public CommandLineRunner demo(
      UserRepository repository,
      RestTemplate restTemplate) {

    return (args) -> {
      // save a couple of customers

      final UsersEntity user1 = UsersEntity.builder()
          .firstName("Daniel")
          .lastName("Chkheidze")
          .nickname("Aaddddd")
          .email("blabla@mail.ru")
          .password("123456")
          .status(ONLINE)
          .build();

      final UsersEntity user2 = UsersEntity.builder()
          .firstName("Anna")
          .lastName("Ivanova")
          .nickname("Annushka")
          .email("annushka@mail.ru")
          .password("125646")
          .status(OFFLINE)
          .build();

      user1.getFriends().add(user2);

      repository.save(user2);
      repository.save(user1);

      user2.getFriends().add(user1);

      repository.save(user1);
      repository.save(user2);

//			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (Customer customer : repository.findAll()) {
//				log.info(customer.toString());
//			}
//			log.info("");
//
//			// fetch an individual customer by ID
//			repository.findById(1L)
//					.ifPresent(customer -> {
//						log.info("Customer found with findById(1L):");
//						log.info("--------------------------------");
//						log.info(customer.toString());
//						log.info("");
//					});
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Bauer'):");
//			log.info("--------------------------------------------");
//			repository.findByLastName("Bauer").forEach(bauer -> {
//				log.info(bauer.toString());
//			});
//			// for (Customer bauer : repository.findByLastName("Bauer")) {
//			// 	log.info(bauer.toString());
//			// }
//			log.info("");
    };
  }
}
