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

public class searchPlayer {

	public static void searchPlayerMenu() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ch=0;
		SearchBO searchBO = new SearchBOImpl();
		do {
			System.out.println("Player Search Main Menu");
			System.out.println("--------------------------");
			System.out.println("1)Get Player By Id");
			System.out.println("2)Get Player By Name");
			System.out.println("3)Get Player By Email");
			System.out.println("4)Get Player by dob");
			System.out.println("5)Get Player by contact");
			System.out.println("6)Get Player by gender");
			System.out.println("7)Get Player by teamname");
			System.out.println("8)Exit Search menu");
			System.out.println("Enter your choice(1-8) - ");
			try {
				ch=Integer.parseInt(br.readLine());
				
				switch (ch) {
				
				
				case 1:
					System.out.println("Enter player id");
					String id = br.readLine();
					//code here for BO
					try {
						Player player = searchBO.getPlayerById(id);
						if(player!=null) {
							System.out.println("Player found with id "+id);
							System.out.println("Player details "+player);
						}
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					break;
					
					
				
				case 2:
					System.out.println("Enter name");
					String name = br.readLine();
					try {
						List<Player> playerList2 = searchBO.getPlayerByName(name);
						if (playerList2!=null && playerList2.size()>0) {
							System.out.println("Players with name: "+name+" was found "+playerList2.size()+" many times");
							System.out.println("Player/s details");
							for (Player player : playerList2) {
								System.out.println(player);
							}
						}
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					break;
					
					
					
				
				case 3:
					System.out.println("Enter player email");
					String email = br.readLine();
					//code here for BO
					try {
						Player player = searchBO.getPlayerByEmail(email);
						if(player!=null) {
							System.out.println("Player found with email "+email);
							System.out.println("Player details "+player);
						}
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					break;
				
					
					
					
				case 4:
					System.out.println("Enter DOB");
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					try {
						Date dob = formatter.parse(br.readLine());
						List<Player> playerList4 = searchBO.getPlayerByDOB(dob);
						if(playerList4!=null && playerList4.size()>0) {
							System.out.println("Players with DOB: "+dob+" was found "+playerList4.size()+" many times");
							System.out.println("Player/s details");
							for (Player player : playerList4) {
								System.out.println(player);
							}
						}
					} catch (ParseException | BusinessException e) {
						System.out.println(e.getMessage());
					}
					break;
					
					
					
				
				case 5:
					System.out.println("Enter player contact");
					Long contact = Long.parseLong(br.readLine());
					//code here for BO
					try {
						Player player = searchBO.getPlayerByContact(contact);
						if(player!=null) {
							System.out.println("Player found with contact "+contact);
							System.out.println("Player details "+player);
						}
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					break;
					
					
					
				
				case 6:
					System.out.println("Enter Gender: M/F");
					String gender = br.readLine();
					try {
						List<Player> playerList6 = searchBO.getPlayerByGender(gender.toUpperCase());
						if(playerList6!=null && playerList6.size()>0) {
							System.out.println("Players with Gender: "+gender+" was found "+playerList6.size()+" many times");
							System.out.println("Player/s details");
							for (Player player : playerList6) {
								System.out.println(player);
							}
						}
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					break;
					
					
					
				
				case 7:
					System.out.println("Enter team name");
					String teamName = br.readLine();
					//code here for BO
					try {
						List<Player> playerList7 = searchBO.getPlayerByTeamName(teamName);
						if(playerList7!=null && playerList7.size()>0) {
							System.out.println("You found "+playerList7.size()+" no of players "+teamName);
							System.out.println("Player/s Details");
							for (Player player : playerList7) {
								System.out.println(player);
							}
						}
					} catch (BusinessException e) {
						System.out.println(e.getMessage());
					}
					break;
					
					
					
				case 8:
					break;
					

				default: System.out.println("Invalid input, try again!");
					break;
					
					
					
				}
			} catch (NumberFormatException | IOException e) {
				System.out.println("Choice should be Integer only");
			}
		}while(ch!=8);
	}
}
