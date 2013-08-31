package com.league.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Season {

	@Id
	private long id;

	/** For Objectify use only */
	@SuppressWarnings("unused")
	private Season() {
		this(0L);
	}

	public Season(final long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

}
