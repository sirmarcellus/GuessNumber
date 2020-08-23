/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tutorial.dto.Game;

/**
 *
 * @author Austin
 */
@Repository
public class GameDaoDbImpl implements GameDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Game> getAllGames() {
        final String sqlSelectAll = "SELECT * FROM game";
        return jdbc.query(sqlSelectAll, new GameMapper());
    }

    @Override
    public Game getOneGameById(int gameId) {
        final String sqlGetOneGame = "SELECT * FROM game WHERE gameId = ?";
        return jdbc.queryForObject(sqlGetOneGame, new GameMapper(), gameId);
    }

    @Transactional
    @Override
    public Game addGame(Game game) {
        final String sqlAddGame = "INSERT INTO game(solution) VALUES (?)";
        jdbc.update(sqlAddGame, game.getSolution());
        
        int increGameId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(increGameId);

        return game;
    }

    @Override
    public void updateGame(Game game) {
        final String sqlUpdate = "UPDATE game SET isDone = ? WHERE gameId = ?";
        jdbc.update(sqlUpdate, game.isIsDone(), game.getGameId());
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int i) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setSolution(rs.getString("solution"));
            game.setIsDone(rs.getBoolean("isDone"));
            return game;
        }

    }

}
