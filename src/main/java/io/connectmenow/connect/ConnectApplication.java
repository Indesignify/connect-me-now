package io.connectmenow.connect;

import static io.connectmenow.connect.model.entities.UserStatus.OFFLINE;
import static io.connectmenow.connect.model.entities.UserStatus.ONLINE;

import io.connectmenow.connect.model.entities.UsersEntity;
import io.connectmenow.connect.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@SpringBootApplication
public class ConnectApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConnectApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {

    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setInterceptors(new ArrayList<>());

    return restTemplate;
  }

//  @Bean
//  public CommandLineRunner demo(
//      UserRepository repository,
//      RestTemplate restTemplate) {
//
//    return (args) -> {
//      final UsersEntity user1 = UsersEntity.builder()
//          .firstName("Daniel")
//          .lastName("Chkheidze")
//          .nickname("Aaddddd")
//          .email("blabla@mail.ru")
//          .password("123456")
//          .status(ONLINE)
//          .build();
//
//      final UsersEntity user2 = UsersEntity.builder()
//          .firstName("Anna")
//          .lastName("Ivanova")
//          .nickname("Annushka")
//          .email("annushka@mail.ru")
//          .password("125646")
//          .status(OFFLINE)
//          .build();
//
//      final UsersEntity user3 = UsersEntity.builder()
//              .firstName("Natasha")
//              .lastName("Ivanova")
//              .nickname("Poput4ik")
//              .email("Natasha@mail.ru")
//              .password("125646")
//              .status(OFFLINE)
//              .build();
//
//      final UsersEntity user4 = UsersEntity.builder()
//              .firstName("Ivan")
//              .lastName("Ivanov")
//              .nickname("Ivaichse")
//              .email("Ivanov123@mail.ru")
//              .password("125646")
//              .status(OFFLINE)
//              .build();
//
//      final UsersEntity user5 = UsersEntity.builder()
//              .firstName("Gleb")
//              .lastName("Gitelman")
//              .nickname("EtoYa")
//              .email("Gitelman@mail.ru")
//              .password("125646")
//              .status(OFFLINE)
//              .build();
//
//      repository.save(user1);
//      repository.save(user2);
//      repository.save(user3);
//      repository.save(user4);
//      repository.save(user5);
//
//
//      user1.getFriends().add(user2);
//      user2.getFriends().add(user1);
//      user1.getFriends().add(user5);
//      user5.getFriends().add(user1);
//
//      repository.save(user1);
//      repository.save(user2);
//      repository.save(user3);
//      repository.save(user4);
//      repository.save(user5);
//
//    };
//  }
}
