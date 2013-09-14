package com.league.model;

import java.util.Date;

import com.google.common.base.Function;
import com.google.common.collect.Range;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Week {

	public static final Function<Week, Date> GET_LOCK = new Function<Week, Date>() {

		@Override
		public Date apply(final Week input) {
			return input.getLock();
		}

	};

	/**
	 * Transforms a {@link Week} into a [{@link #start}, {@link #end}]
	 * {@link Range}
	 */
	public static final Function<Week, Range<Date>> TO_RANGE = new Function<Week, Range<Date>>() {

		@Override
		public Range<Date> apply(final Week input) {
			return Range.closed(input.getStart(), input.getEnd());
		}

	};

	@Parent
	private Key<Season> season;

	@Id
	private long id;

	private Date start;

	private Date lock;

	private Date end;

	/** For Objectify use only */
	@SuppressWarnings("unused")
	private Week() {
		this(null, 0L, null, null, null);
	}

	public Week(final Key<Season> season, final long id, final Date start,
			final Date lock, final Date end) {
		this.season = season;
		this.id = id;
		this.start = start;
		this.lock = lock;
		this.end = end;
	}

	public Week(final long id, final Date lock) {
		this(null, id, null, lock, null);
	}

	public Key<Season> getSeason() {
		return season;
	}

	public void setSeason(final Key<Season> season) {
		this.season = season;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(final Date start) {
		this.start = start;
	}

	public Date getLock() {
		return lock;
	}

	public void setLock(final Date lock) {
		this.lock = lock;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(final Date end) {
		this.end = end;
	}

}
