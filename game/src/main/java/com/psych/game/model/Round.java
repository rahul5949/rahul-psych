package com.psych.game.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="rounds")
public class Round extends Auditable {
	@ManyToOne
	@Getter
	@Setter
	private Game game;
	
	@Getter
	@Setter
	private Question question;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@Getter
	@Setter
	Map<Player, PlayerAnswer> playerAnswers = new HashMap<>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@Getter
	@Setter
	Map<Player, PlayerAnswer> selectedAnswers = new HashMap<>();
	
	private EllenAnswer ellenAnswer;
	
	@NotNull
	@Getter
	@Setter
	private int roundNumber;
	
	
	
	
	
	
}
