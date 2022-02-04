package me.mohammad.rockpaperscissors;

import java.util.List;

public abstract class Option {
	
	private String id;
	private List<String> beats;
	
	public Option(final String id, final List<String> beats) {
		this.id = id;
		this.beats = beats;
	}
	
	public String getID() {
		return id;
	}
	
	public List<String> getBeats() {
		return beats;
	}
	
	public boolean canBeat(final Option option) {
		return beats.contains(option.getID());
	}
	
}
