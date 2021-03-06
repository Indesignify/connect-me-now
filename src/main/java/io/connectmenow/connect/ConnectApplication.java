package io.connectmenow.connect;

import io.connectmenow.connect.model.dto.CreateUserDTO;
import io.connectmenow.connect.model.entities.UserStatus;
import io.connectmenow.connect.model.entities.UsersEntity;
import io.connectmenow.connect.repository.UserRepository;
import io.connectmenow.connect.services.converters.UserConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConnectApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConnectApplication.class, args);
  }

  @Bean
  public CommandLineRunner generateInitialData(
      UserRepository userRepository, UserConverter userConverter) {

    return (args) -> {

      // user 1
      userRepository.save(userConverter.convertCreateUserDTOToUsersEntity(CreateUserDTO.builder()
          .firstName("Daniel")
          .lastName("Ivanov")
          .nickname("DanIvan")
          .password("123456")
          .email("123@mail.ru")
          .avatar("http://www.example.com/1.jpg")
          .build()));

      // user 2
      userRepository.save(userConverter.convertCreateUserDTOToUsersEntity(CreateUserDTO.builder()
          .firstName("Anna")
          .lastName("Ivanova")
          .nickname("Annushka")
          .password("125646")
          .email("annushka@mail.ru")
          .avatar("http://www.example.com/2.jpg")
          .build()));

      // user 3
      userRepository.save(userConverter.convertCreateUserDTOToUsersEntity(CreateUserDTO.builder()
          .firstName("Natasha")
          .lastName("Ivanova")
          .nickname("Poput4ik")
          .password("122231")
          .email("Natasha@mail.ru")
          .avatar("http://www.example.com/3.jpg")
          .build()));

      // user 4
      userRepository.save(userConverter.convertCreateUserDTOToUsersEntity(CreateUserDTO.builder()
          .firstName("Ivan")
          .lastName("Vasilyev")
          .nickname("Ivaichse")
          .password("121212")
          .email("Ivanov123@mail.ru")
          .avatar("http://www.example.com/4.jpg")
          .build()));

      // user 5
      userRepository.save(userConverter.convertCreateUserDTOToUsersEntity(CreateUserDTO.builder()
          .firstName("Gleb")
          .lastName("Gitelman")
          .nickname("EtoYa")
          .password("125646")
          .email("Gitelman@mail.ru")
          .avatar("http://www.example.com/5.jpg")
          .build()));

      // user 6
      userRepository.save(userConverter.convertCreateUserDTOToUsersEntity(CreateUserDTO.builder()
          .firstName("Natasha")
          .lastName("Vasilyeva")
          .nickname("Erohina")
          .password("qwerty")
          .email("Natasha@gmail.com")
          .avatar("http://www.example.com/6.jpg")
          .build()));

      final UsersEntity user1 = userRepository.findById(1L).get();
      user1.setStatus(UserStatus.ONLINE);
      final UsersEntity user2 = userRepository.findById(2L).get();
      user2.setStatus(UserStatus.ONLINE);
      final UsersEntity user3 = userRepository.findById(3L).get();
      user3.setStatus(UserStatus.ONLINE);
      final UsersEntity user4 = userRepository.findById(4L).get();
      user4.setStatus(UserStatus.ONLINE);
      final UsersEntity user5 = userRepository.findById(5L).get();
      user5.setStatus(UserStatus.ONLINE);
      final UsersEntity user6 = userRepository.findById(6L).get();
      user6.setStatus(UserStatus.ONLINE);

//
//      user1.getFriends().add(user2);
//      user1.getFriends().add(user3);
//
//      user2.getFriends().add(user1);
//      user2.getFriends().add(user3);
//
//      user3.getFriends().add(user1);
//
//      user4.getFriends().add(user1);
//      user4.getFriends().add(user2);
//      user4.getFriends().add(user3);
//
      userRepository.save(user1);
      userRepository.save(user2);
      userRepository.save(user3);
      userRepository.save(user4);
      userRepository.save(user5);
      userRepository.save(user6);
    };
  }
}
