package com.bo;

import java.util.Date;
import java.util.List;

import com.exception.BusinessException;
import com.to.Player;
import com.to.Team;

public interface SearchBO {

	public Player getPlayerById(String id) throws BusinessException;
	public Player getPlayerByContact(long contact) throws BusinessException;
	public Player getPlayerByEmail(String gender) throws BusinessException;
	public List<Player> getPlayerByGender(String gender) throws BusinessException;
	public List<Player> getPlayerByDOB(Date dob) throws BusinessException;
	public List<Player> getPlayerByName(String name) throws BusinessException;
	public List<Player> getPlayerByTeamName(String teamName) throws BusinessException;
	public Player registerPlayer(Player player) throws BusinessException;
	public boolean updatePlayer(String id, long newContact) throws BusinessException;
	public boolean deletePlayer(String id) throws BusinessException;
	public Team registerTeam(Team team) throws BusinessException;
	public List<Team> getAllTeams() throws BusinessException;
	public List<Player> getAllPlayers() throws BusinessException;
	
}
