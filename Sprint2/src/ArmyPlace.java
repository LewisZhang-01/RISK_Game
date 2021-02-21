import java.util.LinkedList;

public class ArmyPlace {
	/*
	 * Players take it in turns to place 3 units at a time on a territory that they
	 * control and then one unit for each neutral. The user types in the name of the
	 * territories. The user should be allowed to enter a shortened version of the
	 * name, so long as it is unambiguous. After each selection, update the map.
	 */
	public ArmyPlace() {

	}

	public void place(Board board, UI ui, int playerId, int first) {
		while (checkPlayerArmyNum(board) == false) {
			Deck d = new Deck();
			LinkedList<Card> p1_cards = new LinkedList<Card>();
			LinkedList<Card> p2_cards = new LinkedList<Card>();
			if (first == 0) {
				// For normal players.
				for (playerId = first; playerId < GameData.NUM_PLAYERS_PLUS_NEUTRALS; playerId++) {
					if (playerId < 2) {
						if (getPlayerArmyNum(board, playerId) == 36) {
							if (checkPlayerArmyNum(board) == true) {
								break;
							}
						} else {
							ui.displayString("\nEnter 1 to draw a card, anything else to place armies.(player "
									+ (playerId + 1) + ")\n");
							String mode = ui.getCommand();
							if (mode.equals("1")) {
								if (playerId == 0) {
									p1_cards.add(d.draw());
								} else {
									p2_cards.add(d.draw());
								}
							}

							realPlayerPlace(ui, board, playerId,3);

							ui.displayString("\nEnter 0 to check and trade your cards, anything else to do nothing.\n");
							String mode2 = ui.getCommand();
							if (mode2.equals("0")) {
								if (playerId == 0) {
									d.display(ui, p1_cards);
									ui.displayString("Player 1 has: ");
									for (int i = 0; i < p1_cards.size(); i++) {
										ui.displayString(p1_cards.get(i).toString());
									}
									ui.displayString(
											"Please enter trade to automatically trade your cards, and anything else to skip.");
									String trade = ui.getCommand();
									if (trade.equals("trade")) {
										d.trade(ui, board, playerId, p1_cards);
									}
								} else {
									d.display(ui, p2_cards);
									ui.displayString("Player 2 has: ");
									for (int i = 0; i < p2_cards.size(); i++) {
										ui.displayString(p2_cards.get(i).toString());
									}
									ui.displayString(
											"Please enter trade to automatically trade your cards, and anything else to skip.");
									String trade = ui.getCommand();
									if (trade.equals("trade")) {
										d.trade(ui, board, playerId, p2_cards);
									}
								}
							}
						}
					} else {
						// For neutral players.
						if (getPlayerArmyNum(board, playerId) == 24) {
							if (checkPlayerArmyNum(board) == true) {
								break;
							}
						} else {
							neutralPlayerPlace(ui, board, playerId);
						}
					}
				}
			} else if (first > 0) {
				// For normal players.
				for (playerId = first; playerId < GameData.NUM_PLAYERS_PLUS_NEUTRALS; playerId++) {
					if (playerId < 2) {
						// For 1st player.
						if (getPlayerArmyNum(board, playerId) == 36) {
							if (checkPlayerArmyNum(board) == true) {
								break;
							}
						} else {
							ui.displayString("\nEnter 1 to draw a card, anything else to place armies.(player "
									+ (playerId + 1) + ")\n");
							String mode = ui.getCommand();
							if (mode.equals("1")) {
								if (playerId == 0) {
									p1_cards.add(d.draw());
								} else {
									p2_cards.add(d.draw());
								}
							}

							realPlayerPlace(ui, board, playerId,3);

							ui.displayString("\nEnter 0 to check and trade your cards, anything else to do nothing.\n");
							String mode2 = ui.getCommand();
							if (mode2.equals("0")) {
								if (playerId == 0) {
									d.display(ui, p1_cards);
									ui.displayString("Player 1 has: ");
									for (int i = 0; i < p1_cards.size(); i++) {
										ui.displayString(p1_cards.get(i).toString());
									}
									ui.displayString(
											"Please enter trade to automatically trade your cards, and anything else to skip.");
									String trade = ui.getCommand();
									if (trade.equals("trade")) {
										d.trade(ui, board, playerId, p1_cards);
									}
								} else {
									d.display(ui, p2_cards);
									ui.displayString("Player 2 has: ");
									for (int i = 0; i < p2_cards.size(); i++) {
										ui.displayString(p2_cards.get(i).toString());
									}
									ui.displayString(
											"Please enter trade to automatically trade your cards, and anything else to skip.");
									String trade = ui.getCommand();
									if (trade.equals("trade")) {
										d.trade(ui, board, playerId, p2_cards);
									}
								}
							}
						}
					} else {
						// For neutral players.
						if (getPlayerArmyNum(board, playerId) == 24) {
							if (checkPlayerArmyNum(board) == true) {
								break;
							}
						} else {
							neutralPlayerPlace(ui, board, playerId);
						}
					}
				}
				for (playerId = 0; playerId < first; playerId++) {
					if (getPlayerArmyNum(board, playerId) == 36) {
						if (checkPlayerArmyNum(board) == true) {
							break;
						}
					} else {
						ui.displayString("\nEnter 1 to draw a card, anything else to place armies.(player "
								+ (playerId + 1) + ")\n");
						String mode = ui.getCommand();
						if (mode.equals("1")) {
							if (playerId == 0) {
								p1_cards.add(d.draw());
							} else {
								p2_cards.add(d.draw());
							}
						}

						realPlayerPlace(ui, board, playerId,3);

						ui.displayString("\nEnter 0 to check and trade your cards, anything else to do nothing.\n");
						String mode2 = ui.getCommand();
						if (mode2.equals("0")) {
							if (playerId == 0) {
								d.display(ui, p1_cards);
								ui.displayString("Player 1 has: ");
								for (int i = 0; i < p1_cards.size(); i++) {
									ui.displayString(p1_cards.get(i).toString());
								}
								ui.displayString(
										"Please enter trade to automatically trade your cards, and anything else to skip.");
								String trade = ui.getCommand();
								if (trade.equals("trade")) {
									p1_cards = d.trade(ui, board, playerId, p1_cards);
								}
							} else {
								d.display(ui, p2_cards);
								ui.displayString("Player 2 has: ");
								for (int i = 0; i < p2_cards.size(); i++) {
									ui.displayString(p2_cards.get(i).toString());
								}
								ui.displayString(
										"Please enter trade to automatically trade your cards, and anything else to skip.");
								String trade = ui.getCommand();
								if (trade.equals("trade")) {
									p2_cards = d.trade(ui, board, playerId, p2_cards);
								}
							}
						}
					}
				}
			}
		}
	}

	public static String errorHandle(Board board, UI ui, int playerId, String territory) {

		int country = board.getCountry(territory);
		int occupier = board.getOccupier(country);
		// invalid input handle:
		while (country == -1 || occupier != playerId || occupier == -1) {
			// Check country.
			if (country == -1 || occupier == -1) {
				ui.displayString(
						"\n*No territory matched!\n*All territorial names can be simplified to capitalized with the first two letters.\n*For instance: \"Ontario\" use [ON] & \"E United States\" use [EUS]\n*Except \"Alaska\" use [ALA] & \"Siberia\" use [SIB] & \"Indonesia\" use [ID] & \"E Africa\" use [EAF]\n*Please enter again: \n");
			}
			// Check occupier.
			if (board.getOccupier(country) != playerId && occupier != -1) {
				ui.displayString("*You can only choose your territory (" + MapPanel.word_PLAYER_COLORS[playerId]
						+ ").\nPlease enter again: ");
			}
			territory = ui.getCommand();
			country = board.getCountry(territory);
			occupier = board.getOccupier(country);
		}
		return territory;

	}

	public static void realPlayerPlace(UI ui, Board board, int playerId, int armiesGet) {
		ui.displayString("Enter the name of territory which you want place unit. {" + getPlayerArmyNum(board, playerId)
				+ "/36}(player " + (playerId + 1) + ")[color: " + MapPanel.word_PLAYER_COLORS[playerId] + " ]");
		String territory = ui.getCommand();
		// Check if input for territory name is invalid.
		territory = errorHandle(board, ui, playerId, territory);
		ui.displayString("> " + territory + "\nPlace 3 units to this territory.");

		int unit_num = 3;
		board.placeUnits(territory, playerId, unit_num);// place 3 unit to this country.

		ui.displayMap();// refresh map.
	}

	public void neutralPlayerPlace(UI ui, Board board, int playerId) {
		int unit_num = 1;
		int id;
		for (id = 0; id < GameData.NUM_COUNTRIES; id++) {
			if (board.getOccupier(id) == playerId && board.getNumUnits(id) < 4) {
				board.addUnits(id, playerId, unit_num);
				// ui.displayString("find 1");
				break;
			}
		}

		ui.displayString("> " + GameData.COUNTRY_NAMES[id]);
		ui.displayMap();// refresh map.
	}

	public static int getPlayerArmyNum(Board board, int playerId) {
		int armyNum = 0;
		for (int id = 0; id < GameData.COUNTRY_NAMES.length; id++) {
			if (board.getOccupier(id) == playerId) {
				armyNum += board.getNumUnits(id);
			}
		}
		return armyNum;
	}

	public boolean checkPlayerArmyNum(Board board) {
		int count = 0;
		for (int id = 0; id < GameData.NUM_PLAYERS; id++) {
			if (getPlayerArmyNum(board, id) == 36) {
				count++;
			}
		}
		for (int id = GameData.NUM_PLAYERS; id < GameData.NUM_PLAYERS_PLUS_NEUTRALS; id++) {
			if (getPlayerArmyNum(board, id) == 24) {
				count++;
			}
		}
		if (count == GameData.NUM_PLAYERS_PLUS_NEUTRALS) {
			return true;
		} else {
			return false;
		}
	}

}
