package com.psych.game.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stats")
public class Stat extends Auditable {
	@Getter
	@Setter
	private long gotPsychedCount = 0;
	@Getter
	@Setter
	private long pyschedOthersCount = 0;
	@Getter
	@Setter
	private long correctAnwerCount = 0;
}
