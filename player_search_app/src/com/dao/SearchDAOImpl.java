package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dbutil.OracleDbConnection;
import com.exception.BusinessException;
import com.to.Player;
import com.to.Team;

public class SearchDAOImpl implements SearchDAO{

	@Override
	public Player getPlayerById(String id) throws BusinessException {
		Player player = null;
		try (Connection connection = OracleDbConnection.getConnection()){
			String sql = "select p.id,p.name,p.dob,p.email,"
					+ "p.gender,p.contact,t.teamName "
					+ "from player p join teams t "
					+ "on p.team_id=t.team_id"
					+ " where p.id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id.toUpperCase());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				player = new Player();
				player.setId(id);
				player.setName(resultSet.getString("name"));
				player.setEmail(resultSet.getString("email"));
				player.setTeamname(resultSet.getString("teamName"));
				player.setGender(resultSet.getString("gender"));
				player.setContact(resultSet.getLong("contact"));
				player.setDob(resultSet.getDate("dob"));
				
			} else {
				throw new BusinessException("player id "+id+" not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured kindly contact SYSADMIN"+e);
		}
		return player;
	}

	@Override
	public Player getPlayerByContact(long contact) throws BusinessException {
		Player player = null;
		try (Connection connection = OracleDbConnection.getConnection()){
			String sql = "select p.id,p.name,p.dob,p.email,"
					+ "p.gender,p.contact,t.teamName "
					+ "from player p join teams t "
					+ "on p.team_id=t.team_id"
					+ " where p.contact=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, contact);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				player = new Player();
				player.setId(resultSet.getString("id"));
				player.setName(resultSet.getString("name"));
				player.setEmail(resultSet.getString("email"));
				player.setTeamname(resultSet.getString("teamName"));
				player.setGender(resultSet.getString("gender"));
				player.setContact(contact);
				player.setDob(resultSet.getDate("dob"));
				
			} else {
				throw new BusinessException("player contact "+contact+" not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured kindly contact SYSADMIN"+e);
		}
		return player;
	}

	@Override
	public Player getPlayerByEmail(String email) throws BusinessException {
		Player player = null;
		try (Connection connection = OracleDbConnection.getConnection()){
			String sql = "select p.id,p.name,p.dob,p.gender,"
					+ "p.contact,t.teamName "
					+ "from player p join teams t "
					+ "on p.team_id=t.team_id"
					+ " where p.email=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				player = new Player();
				player.setId(resultSet.getString("id"));
				player.setName(resultSet.getString("name"));
				player.setEmail(email);
				player.setTeamname(resultSet.getString("teamName"));
				player.setGender(resultSet.getString("gender"));
				player.setContact(resultSet.getLong("contact"));
				player.setDob(resultSet.getDate("dob"));
				
			} else {
				throw new BusinessException("player contact "+email+" not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured kindly contact SYSADMIN"+e);
		}
		return player;
	}

	@Override
	public List<Player> getPlayerByGender(String gender) throws BusinessException {
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = OracleDbConnection.getConnection()){
			String sql = "select p.id,p.name,p.dob,p.email,"
					+ "p.contact,t.teamName "
					+ "from player p join teams t "
					+ "on p.team_id=t.team_id"
					+ " where p.gender=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, gender);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Player player = new Player();
				player.setId(resultSet.getString("id"));
				player.setName(resultSet.getString("name"));
				player.setEmail(resultSet.getString("email"));
				player.setTeamname(resultSet.getString("teamName"));
				player.setGender(gender);
				player.setContact(resultSet.getLong("contact"));
				player.setDob(resultSet.getDate("dob"));
				playerList.add(player);
				
			} 
			if(playerList.size()==0)
			{
				throw new BusinessException("player gender - "+gender+" not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured kindly contact SYSADMIN"+e);
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayerByDOB(Date dob) throws BusinessException {
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = OracleDbConnection.getConnection()){
			String sql = "select p.id,p.name,p.email,"
					+ "p.gender,p.contact,t.teamName "
					+ "from player p join teams t "
					+ "on p.team_id=t.team_id"
					+ " where p.dob=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDate(1, new java.sql.Date(dob.getTime()));
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Player player = new Player();
				player.setId(resultSet.getString("id"));
				player.setName(resultSet.getString("name"));
				player.setEmail(resultSet.getString("email"));
				player.setTeamname(resultSet.getString("teamName"));
				player.setGender(resultSet.getString("gender"));
				player.setContact(resultSet.getLong("contact"));
				player.setDob(dob);
				playerList.add(player);
				
			} 
			if(playerList.size()==0)
			{
				throw new BusinessException("player DOB - "+dob+" not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured kindly contact SYSADMIN"+e);
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayerByName(String name) throws BusinessException {
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = OracleDbConnection.getConnection()){
			String sql = "select p.id,p.dob,p.email,"
					+ "p.gender,p.contact,t.teamName "
					+ "from player p join teams t "
					+ "on p.team_id=t.team_id"
					+ " where p.name=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Player player = new Player();
				player.setId(resultSet.getString("id"));
				player.setName(name);
				player.setEmail(resultSet.getString("email"));
				player.setTeamname(resultSet.getString("teamName"));
				player.setGender(resultSet.getString("gender"));
				player.setContact(resultSet.getLong("contact"));
				player.setDob(resultSet.getDate("dob"));
				playerList.add(player);
				
			} 
			if(playerList.size()==0)
			{
				throw new BusinessException("player name - "+name+" not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured kindly contact SYSADMIN"+e);
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayerByTeamName(String teamName) throws BusinessException {
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = OracleDbConnection.getConnection()){
			String sql = "select p.id,p.name,p.dob,p.email,"
					+ "p.gender,p.contact "
					+ "from player p join teams t "
					+ "on p.team_id=t.team_id"
					+ " where t.teamName=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teamName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Player player = new Player();
				player.setId(resultSet.getString("id"));
				player.setName(resultSet.getString("name"));
				player.setEmail(resultSet.getString("email"));
				player.setTeamname(teamName);
				player.setGender(resultSet.getString("gender"));
				player.setContact(resultSet.getLong("contact"));
				player.setDob(resultSet.getDate("dob"));
				playerList.add(player);
				
			} 
			if(playerList.size()==0)
			{
				throw new BusinessException("player with teamname - "+teamName+" not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured kindly contact SYSADMIN"+e);
		}
		return playerList;
	}

	@Override
	public Player registerPlayer(Player player) throws BusinessException {
		try (Connection connection = OracleDbConnection.getConnection()){
			String sql = "{call REGISTERPLAYER(?,?,?,?,?,?,?,?)}"; //if function then {call ?=REGISTERPLAYER(?,?,?,?,?,?,?)}
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			callableStatement.setString(2, player.getName());
			callableStatement.setDate(3, new java.sql.Date(player.getDob().getTime()) );
			callableStatement.setString(4, player.getEmail());
			callableStatement.setString(5, player.getGender());
			callableStatement.setString(6, player.getTeamname());
			callableStatement.setLong(7, player.getContact());
			callableStatement.registerOutParameter(8, java.sql.Types.VARCHAR);
			
			callableStatement.execute();
			if(callableStatement.getString(8) == null) {
				player.setId(callableStatement.getString(1));
			} else {
				throw new SQLException(callableStatement.getString(8));
			}
			player.setId(callableStatement.getString(1));
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Internal error occured kindly contact SYSADMIN"+e);
		}
		
		
		return player;
	}

	@Override
	public boolean updatePlayer(String id, long newContact) throws BusinessException {
		boolean b = false;
		try (Connection connection = OracleDbConnection.getConnection()){
			String sql = "{call updatePlayerContact(?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(1, id);
			callableStatement.setLong(2, newContact);
			
			b = callableStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Internal error occured kindly contact SYSADMIN"+e);
		}
		
		return b;
	}

	@Override
	public boolean deletePlayer(String id) throws BusinessException {
		boolean b = false;
		try (Connection connection = OracleDbConnection.getConnection()){
			String sql = "{call deletePlayer(?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(1, id);
			
			b = callableStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Internal error occured kindly contact SYSADMIN"+e);
		}
		return b;
	}

	@Override
	public Team registerTeam(Team team) throws BusinessException {
		try (Connection connection = OracleDbConnection.getConnection()){
			String sql = "{call REGISTERTEAM(?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setInt(1, team.getTeam_id());
			callableStatement.setString(2, team.getTeamName());
			callableStatement.setString(3, team.getCoachName());
			
			callableStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Internal error occured kindly contact SYSADMIN"+e);
		}
		return team;
	}

	@Override
	public List<Team> getAllTeams() throws BusinessException {
		List<Team> teamList = new ArrayList<>();
		try (Connection connection = OracleDbConnection.getConnection()){
			String sql = "select team_id, teamname, coachname "
					+ "from teams";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Team team = new Team();
				team.setTeam_id(resultSet.getInt("team_id"));
				team.setTeamName(resultSet.getString("teamname"));
				team.setCoachName(resultSet.getString("coachname"));
				
				teamList.add(team);
			}
			if(teamList.size()==0)
			{
				throw new BusinessException("No Teams found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Internal error occured kindly contact SYSADMIN"+e);
		}
		return teamList;
	}

	@Override
	public List<Player> getAllPlayers() throws BusinessException {
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = OracleDbConnection.getConnection()){
			String sql = "select p.id, p.name, p.dob, p.email, p.gender, p.contact, teamname "
					+ "from player p join teams t on p.team_id = t.team_id";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Player player = new Player();
				player.setId(resultSet.getString("id"));
				player.setName(resultSet.getString("name"));
				player.setDob(resultSet.getDate("dob")); 
				player.setEmail(resultSet.getString("email"));
				player.setGender(resultSet.getString("gender"));
				player.setContact(resultSet.getLong("contact"));
				player.setTeamname(resultSet.getString("teamname"));
				playerList.add(player);
			}
			if(playerList.size()==0)
			{
				throw new BusinessException("No Players found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Internal error occured kindly contact SYSADMIN"+e);
		}
		return playerList;
	}

}
