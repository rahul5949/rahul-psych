package com.psych.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psych.game.model.Game;
import com.psych.game.model.GameMode;
import com.psych.game.model.Player;
import com.psych.game.model.Question;
import com.psych.game.model.User;
import com.psych.game.repositories.GameRepository;
import com.psych.game.repositories.PlayerRepository;
import com.psych.game.repositories.QuestionRepository;
import com.psych.game.repositories.UserRepository;

@RestController
@RequestMapping("/dev-test")
public class DevTestController {
	
	@Autowired(required = true)
	private PlayerRepository playerRepoitory;
	@Autowired
	private QuestionRepository questionRepository;	
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private UserRepository userRepoitory;

	@GetMapping("/")
	public String hello() {
		return "Hello, World!";		
	}
	
	@GetMapping("/populate")
	public String PopulateDB() {
		for(Player player : playerRepoitory.findAll()) {
			player.getGames().clear();
			playerRepoitory.save(player);
		}
		
		gameRepository.deleteAll();
		playerRepoitory.deleteAll();
		questionRepository.deleteAll();
		  
		  
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
		  
		  Game game = new Game();
		  game.setGameMode(GameMode.IS_THIS_A_FACT);
		  game.setLeader(luffy);
		  game.getPlayers().add(luffy);
		  gameRepository.save(game);
		  
		  questionRepository.save(new Question("What is the most important Poneglyph", 
				  "Rio Poneglyph", GameMode.IS_THIS_A_FACT));
		  
		  questionRepository.save(new Question("How far can luff stretch?", 
				  "56 Gomu Rio Gomus", GameMode.IS_THIS_A_FACT));
		
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
	
	@GetMapping("/users")
	public List<User> getAllUser(){
		return userRepoitory.findAll();
	}
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable(name="id") Long id){
		return userRepoitory.findById(id).orElseThrow();
	}
	
	@GetMapping("/games")
	public List<Game> getAllGames(){
		return gameRepository.findAll();
	}
	@GetMapping("/game/{id}")
	public Game getGameById(@PathVariable(name="id") Long id){
		return gameRepository.findById(id).orElseThrow();
	}
}
