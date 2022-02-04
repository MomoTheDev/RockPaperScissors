package me.mohammad.rockpaperscissors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class RockPaperScissors {
	
	public static final String prefix;
	
	private Map<String, Option> options;
	private List<String> optionKeys;
	private Scanner scanner;
	private boolean playing;
	
	static {
		prefix = "[RPC] ";
	}
	
	protected RockPaperScissors() {
		options = new HashMap<>();
		scanner = new Scanner(System.in);
		registerDefaultOptions();
		startGameCycle();
		scanner.close();
	}
	
	private void startGameCycle() {
		playing = true;
		while (playing) {
			handleInput();
		}
	}
	
	private boolean handleInput() {
		System.out.printf("%sWhat's your choice?\n", prefix);
		final String next = scanner.nextLine().toLowerCase();
		if (next.equalsIgnoreCase("exit")) {
			playing = false;
			System.exit(0);
			return true;
		}
		final Option computersOption = options.get(optionKeys.get(ThreadLocalRandom.current().nextInt(optionKeys.size())));
		if (!(optionKeys.contains(next)))
			return println(String.format("%sYou're choice isn't available! Try again?", prefix), true);
		if (next.equals(computersOption.getID()))
			return println(String.format("%sYou picked the option as me! Try again?", prefix), true);
		final Option nextOption = options.get(next);
		if (nextOption.canBeat(computersOption))
			return println(String.format("%sI picked %s, you win!", prefix, computersOption.getID()), true);
		if (computersOption.canBeat(nextOption))
			return println(String.format("%sI picked %s, you lose!", prefix, computersOption.getID()), true);
		return false;
	}
	
	private boolean println(final String message, final boolean state) {
		System.out.println(message);
		return state;
	}
	
	private void registerDefaultOptions() {
		registerOption("rock", (Option) new RockOption());
		registerOption("paper", (Option) new PaperOption());
		registerOption("scissor", (Option) new ScissorsOption());
		optionKeys = new ArrayList<>(options.keySet());
	}
	
	public boolean registerOption(final String key, final Option option) {
		if (options.containsKey(key))
			return false;
		return options.put(key, option) != null;
	}
	
}
