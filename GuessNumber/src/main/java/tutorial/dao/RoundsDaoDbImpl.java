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
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tutorial.dto.Rounds;

/**
 *
 * @author Austin
 */
@Repository
public class RoundsDaoDbImpl implements RoundsDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Rounds> getAllRounds(int gameId) {
        final String sqlGetRounds = "SELECT * FROM rounds WHERE gameId = ? ORDER BY guessTime ASC";
        List<Rounds> rounds = jdbc.query(sqlGetRounds, new RoundMapper(), gameId);
        return rounds;
    }

    @Override
    public Rounds getRoundId(int roundId) {
        final String sqlGetOneRound = "SELECT * FROM rounds WHERE roundId = ?";
        return jdbc.queryForObject(sqlGetOneRound, new RoundMapper(), roundId);
    }

    @Override
    @Transactional
    public Rounds addRound(Rounds round) {
        final String sqlAddRound = "INSERT INTO rounds(gameId, userGuess, result) VALUES (?,?,?)";
        jdbc.update(sqlAddRound, round.getGameId(), round.getUserGuess(), round.getGameResult());
        
        int increRoundId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(increRoundId);
        return getRoundId(increRoundId);
        
    }
    
    private static final class RoundMapper implements RowMapper<Rounds> {

        @Override
        public Rounds mapRow(ResultSet rs, int i) throws SQLException {
            Rounds round = new Rounds();
            round.setRoundId(rs.getInt("roundId"));
            round.setGameId(rs.getInt("gameId"));
            
            Timestamp ts = rs.getTimestamp("guessTime");
            round.setGuessTime(ts.toLocalDateTime());
            
            round.setUserGuess(rs.getString("userGuess"));
            round.setGameResult(rs.getString("result"));
            return round;
        }
        
    }
    
}
