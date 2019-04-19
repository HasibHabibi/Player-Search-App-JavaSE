package com.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bo.SearchBO;
import com.bo.SearchBOImpl;
import com.exception.BusinessException;
import com.to.Player;
import com.to.Team;

public class SearchMain {

	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to Player Search APP V1.0");
		System.out.println("--------------------------------------");
		int ch=0;
		SearchBO searchBO = new SearchBOImpl();
		do {
			System.out.println("Main Menu");
			System.out.println("-----------");
			System.out.println("1)Register new Player");
			System.out.println("2)Register new Team");
			System.out.println("3)View Teams");
			System.out.println("4)View Player");
			System.out.println("5)Update player Contact by ID");
			System.out.println("6)Search Menu");
			System.out.println("7)Delete player by ID");
			System.out.println("8)EXIT");
			System.out.println("Enter your choice(1-8) - ");
			try {
				ch=Integer.parseInt(br.readLine());
				
				switch (ch) {
							
					
				
				case 1:
					System.out.println("Enter name");
					String newName = br.readLine();
					System.out.println("Enter DOB");
					String newDate = br.readLine();
					Date newDob = SearchMain.convertStringToDate(newDate);
					System.out.println("Enter player email");
					String newEmail = br.readLine();
					System.out.println("Enter Gender: M/F");
					String newGender = br.readLine().toUpperCase();
					System.out.println("Enter player contact");
					Long newContact = Long.parseLong(br.readLine());
					System.out.println("Enter team name");
					String newTeamName = br.readLine();
					Player player = new Player(newName, newDob, newEmail, newGender, newTeamName, newContact);
					try {
						searchBO.registerPlayer(player);
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					if(player.getId()!=null) {
						System.out.println("Player registered with below details");
						System.out.println(player);
					} else {
						System.out.println("Registration failed");
					}


					break;
					
				case 2:
					System.out.println("Enter new team ID");
					int team_id = Integer.parseInt(br.readLine());
					System.out.println("Enter new team name");
					String teamname = br.readLine();
					System.out.println("Enter new coach name");
					String coachname = br.readLine();
					Team team = new Team(team_id, teamname, coachname);
					try {
						searchBO.registerTeam(team);
						System.out.println("Team registered successfully");
						System.out.println(team);
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					break;
					
					
				case 3:
					try {
						List<Team> teamList = searchBO.getAllTeams();
						if(teamList!=null && teamList.size()>0) {
							System.out.println("You found "+teamList.size()+" no of teams ");
							System.out.println("Printing all teams");
							for (Team team2 : teamList) {
								System.out.println(team2);
							}
						}
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					
					break;
					
					
				case 4:
					try {
						List<Player> playerList = searchBO.getAllPlayers();
						if(playerList!=null && playerList.size()>0) {
							System.out.println("You found "+playerList.size()+" no of players ");
							System.out.println("Printing all of Player table");
							for (Player player2 : playerList) {
								System.out.println(player2);
							}
						}
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					break;
					
					
					
				
				case 5:
					System.out.println("Enter player id");
					String id2 = br.readLine();
					System.out.println("Enter player new contact");
					Long contact2 = Long.parseLong(br.readLine());
					try {
						if(searchBO.updatePlayer(id2, contact2)) {
							System.out.println("Player's contact with "+id2+" updated successfully");
						} else {
							System.out.println("update has failed");
						}
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
						
					break;
					
					
				case 6:
					searchPlayer.searchPlayerMenu();
					break;
					
					
				case 7:
					System.out.println("Enter player id");
					String delete = br.readLine();
					try {
						if(searchBO.deletePlayer(delete)) {
							System.out.println("Player with "+delete+" was deleted successfully");
						} else {
							System.out.println("delete has failed");
						}
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					
					break;
					
					
					
				case 8:
					System.out.println("Thank you for using App!");
					break;
					
				
					
				default: System.out.println("Invalid input, try again!");
				break;
					
					
				}
			} catch (NumberFormatException | IOException e) {
				System.out.println("Choice should be Integer only");
			}
		}while(ch!=8);
	}
	
	public static Date convertStringToDate(String dateString) {
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		try {
			date = df.parse(dateString);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return date;
		
	}

}
