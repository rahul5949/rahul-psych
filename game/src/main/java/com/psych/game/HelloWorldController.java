package com.psych.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psych.game.model.GameMode;
import com.psych.game.model.Player;
import com.psych.game.model.Question;
import com.psych.game.repositories.PlayerRepository;
import com.psych.game.repositories.QuestionRepository;

@RestController
@RequestMapping("/dev-test")
public class HelloWorldController {
	
	@Autowired(required = true)
	private PlayerRepository playerRepoitory;
	
	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/")
	public String hello() {
		return "Hello, World!";		
	}
	
	@GetMapping("populate")
	public String PopulateDB() {
		  Player luffy = new Player.Builder()
				  .alias("Monkey D. Luffy")
				  .email("luffy@abc.com")
				  .saltedHashedPassword("strawhat")
				  .build();
		  playerRepoitory.save(luffy);
		  

		  Player robin = new Player.Builder()
				  .alias("Nico Robin")
				  .email("robin@abc.com")
				  .saltedHashedPassword("poneglyph")
				  .build();
		  playerRepoitory.save(robin);
		  
		  questionRepository.save(new Question("What is the most important Poneglyph", 
				  "Rio Poneglyph", GameMode.IS_THIS_A_FACT));
		return "populated";
	
	}
	
	@GetMapping("/questions")
	public List<Question> getAllQuestions(){
		return questionRepository.findAll();
	}
	
	@GetMapping("/question/{id}")
	public Question getQuestionById(@PathVariable(name="id") Long id){
		return questionRepository.findById(id).orElseThrow();
	}
	
	@GetMapping("/players")
	public List<Player> getAllPlayer(){
		return playerRepoitory.findAll();
	}
	@GetMapping("/player/{id}")
	public Player getPlayerById(@PathVariable(name="id") Long id){
		return playerRepoitory.findById(id).orElseThrow();
	}
}
