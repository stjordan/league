package com.league.model;

import com.google.common.base.Objects;
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

	@Override
	public boolean equals(final Object obj) {

		final boolean equals;
		if (this == obj) {
			equals = true;
		} else if (obj instanceof Season) {
			final Season that = (Season) obj;
			equals = this.id == that.id;
		} else {
			equals = false;
		}
		return equals;
	}

	@Override
	public int hashCode() {
		return java.util.Objects.hash(id);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id).toString();
	}

}
