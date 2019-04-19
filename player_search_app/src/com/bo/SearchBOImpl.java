package com.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dao.SearchDAO;
import com.dao.SearchDAOImpl;
import com.exception.BusinessException;
import com.to.Player;
import com.to.Team;

public class SearchBOImpl implements SearchBO{
	private SearchDAO searchDAO;

	@Override
	public Player getPlayerById(String id) throws BusinessException {
		Player player = null;
		if(id.matches("[Pp]{1}[a-zA-Z]{2}[0-9]{7}")) {
			//code here for DAO
			searchDAO = getSearchDAO();
			player = searchDAO.getPlayerById(id);
		} else {
			throw new BusinessException("Entered player id "+id+" is invalid");
		}
		return player;
	}

	@Override
	public Player getPlayerByContact(long contact) throws BusinessException {
		Player player = null;
		if(Long.toString(contact).length()==10) {
			searchDAO = getSearchDAO();
			player = searchDAO.getPlayerByContact(contact);
		} else {
			throw new BusinessException("Entered contact "+contact+" is invalid");
		}
		return player;
	}

	@Override
	public Player getPlayerByEmail(String email) throws BusinessException {
		Player player = null;
		if(email.matches("^(.+)@(.+)$")) {
			//code here for DAO
			searchDAO = getSearchDAO();
			player = searchDAO.getPlayerByEmail(email);
		} else {
			throw new BusinessException("Entered player email "+email+" is invalid");
		}
		return player;
	}

	@Override
	public List<Player> getPlayerByGender(String gender) throws BusinessException {
		List<Player> playerList = null;
		if(gender.matches("[MF]{1}")) {
			searchDAO = getSearchDAO();
			playerList = searchDAO.getPlayerByGender(gender);
		} else {
			throw new BusinessException("Gender entered: "+gender+" was invalid");
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayerByDOB(Date dob) throws BusinessException {
		List<Player> playerList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if(sdf.format(dob).matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
			searchDAO = getSearchDAO();
			playerList = searchDAO.getPlayerByDOB(dob);
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayerByName(String name) throws BusinessException {
		List<Player> playerList = null;
		if(name.matches("[a-zA-Z]{2,20}")) {
			searchDAO = getSearchDAO();
			playerList = searchDAO.getPlayerByName(name);
		} else {
			throw new BusinessException("Name Entered: "+name+" is invalid");	
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayerByTeamName(String teamName) throws BusinessException {
		List<Player> playerList = null;
		if(teamName.matches("[a-zA-Z ]{3,15}")) {
			searchDAO = getSearchDAO();
			playerList = searchDAO.getPlayerByTeamName(teamName);
		} else {
			throw new BusinessException("Entered Team name "+teamName+" is invalid");
		}
		return playerList;
	}


	
	
	
	
	
	
	
	@Override
	public Player registerPlayer(Player player) throws BusinessException {
			if(player.getName().matches("[a-zA-Z]{2,20}")) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				if(sdf.format(player.getDob()).matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
					if(player.getEmail().matches("^(.+)@(.+)$")) {
						if(player.getGender().matches("[MF]{1}")) {
							if(Long.toString(player.getContact()).length()==10) {
								if(player.getTeamname().matches("[a-zA-Z ]{3,15}")) {
									searchDAO = getSearchDAO();
									player = searchDAO.registerPlayer(player);
								}
								else
									throw new BusinessException("Entered Team Name: "+player.getTeamname()+" is invalid");
							}
							else
								throw new BusinessException("Entered contact: "+player.getContact()+" is invalid");
						}
						else
							throw new BusinessException("Entered Gender: "+player.getGender()+" is invalid");
					}
					else
						throw new BusinessException("Entered Email: "+player.getEmail()+" is invalid");
				}
				else
					throw new BusinessException("Entered DOB: "+player.getDob()+" is invalid");
			}
		return player;
	}
	
	
	
	
	
	
	
	
	@Override
	public boolean updatePlayer(String id, long newContact) throws BusinessException {
		boolean b1 = true;
		boolean[] b = {false, false};
		if(id.matches("[Pp]{1}[a-zA-Z]{2}[0-9]{7}")) {
			b[0] = true;
		}
		if(Long.toString(newContact).length()==10) {
			b[1] = true;
		}
		for (boolean c : b) {
			if(!c) {
				b1 = false;
			}
			searchDAO = getSearchDAO();
			searchDAO.updatePlayer(id, newContact);
		}
		return b1;
	}

	@Override
	public boolean deletePlayer(String id) throws BusinessException {
		boolean b = false;
		if(id.matches("[Pp]{1}[a-zA-Z]{2}[0-9]{7}")) {
			b = true;
			searchDAO = getSearchDAO();
			searchDAO.deletePlayer(id);
		}
		return b;
	}
	
	
	
	
	
	

	@Override
	public Team registerTeam(Team team) throws BusinessException {
		
		searchDAO = getSearchDAO();
		team = searchDAO.registerTeam(team);
		return team;
	}
	
	
	
	
	
	
	
	

	@Override
	public List<Team> getAllTeams() throws BusinessException {
		List<Team> teamList = null;
		searchDAO = getSearchDAO();
		teamList = searchDAO.getAllTeams();
		return teamList;
	}

	@Override
	public List<Player> getAllPlayers() throws BusinessException {
		List<Player> playerList = null;
		searchDAO = getSearchDAO();
		playerList = searchDAO.getAllPlayers();
		return playerList;
	}
	

	private SearchDAO getSearchDAO() {
		if(searchDAO==null) {
			searchDAO=new SearchDAOImpl();
		}
		return searchDAO;
	}

}
