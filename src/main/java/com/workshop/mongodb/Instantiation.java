package com.workshop.mongodb;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.workshop.mongodb.domain.Post;
import com.workshop.mongodb.domain.User;
import com.workshop.mongodb.dto.AuthorDTO;
import com.workshop.mongodb.repository.PostRepository;
import com.workshop.mongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd/mm/YYY");
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null,sdf1.parse("21/03/2018"),"Partiu viagem","Vou viajar para são paulo!", new AuthorDTO(maria));
		Post post2 = new Post(null,sdf1.parse("21/03/2018"),"Bom dia","Hoje acordei feliz!", new AuthorDTO(maria));
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
