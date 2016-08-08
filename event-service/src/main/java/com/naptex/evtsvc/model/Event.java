package com.naptex.evtsvc.model;

import java.util.Date;

public class Event
{
	private String message;
	private Level level;
	private Date time;

	private String origin;
	private long duration;

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Level getLevel()
	{
		return level;
	}

	public void setLevel(Level level)
	{
		this.level = level;
	}

	public Date getTime()
	{
		return time;
	}

	public void setTime(Date time)
	{
		this.time = time;
	}

	public String getOrigin()
	{
		return origin;
	}

	public void setOrigin(String origin)
	{
		this.origin = origin;
	}

	public long getDuration()
	{
		return duration;
	}

	public void setDuration(long duration)
	{
		this.duration = duration;
	}

	@Override
	public String toString()
	{
		return "Event [message=" + message + ", level=" + level + ", time="
				+ time + "]";
	}
}
