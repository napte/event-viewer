package com.naptex.evtsvc.model;

import java.util.Date;

import com.google.gson.Gson;

public class Event
{
	private String id;
	private String message;
	private Level level;
	private Date time;

	private String origin;
	private long duration;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

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

	public String toJson()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	/*
	 * ====== DO NOT rename this method ======
	 * 
	 * Method must be named as "fromString" so that the JSON POST entities of
	 * type Event can be processed automatically by REST API.
	 * 
	 * An example of bad coupling between a model class and REST API.
	 */
	public static Event fromString(String json)
	{
		Gson gson = new Gson();
		return gson.fromJson(json, Event.class);
	}

	@Override
	public String toString()
	{
		return "Event [message=" + message + ", level=" + level + ", time="
				+ time + "]";
	}
}
