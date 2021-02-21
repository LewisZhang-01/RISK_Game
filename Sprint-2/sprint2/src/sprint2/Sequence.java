package sprint2;

/*
 * BadGuys
 * Zhonghe Chen 19203048
 * Zhi Zhang 18210054
 * Yunlong Cheng 18210611
 * 
 * */
import java.util.Arrays;

public class Sequence {

	private int Dice() {
		return (int) (Math.random() * 6) + 1;
	}

	public int sequence(UI ui, int playerId) {
		int[] dice = new int[GameData.NUM_PLAYERS];
		for (playerId = 0; playerId < GameData.NUM_PLAYERS; playerId++) {
			dice[playerId] = Dice();
		}
		int max = dice[0];
		int index = 0;
		for (int i = 0; i < dice.length; i++) {
			if (max < dice[i]) {
				max = dice[i];
				index = i;
			}
		}
		Arrays.sort(dice);
		if (dice[dice.length - 1] == dice[dice.length - 2]) {
			ui.displayString("\nReroll!\n");
			sequence(ui, playerId);
		} else {
			ui.displayString("\nThe player" + (index + 1) + " play first.\n");
		}
		return index;
	}
}
