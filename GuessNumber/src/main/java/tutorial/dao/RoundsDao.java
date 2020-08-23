/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.dao;

import java.util.List;
import tutorial.dto.Rounds;

/**
 *
 * @author Austin
 */
public interface RoundsDao {
    
    List<Rounds> getAllRounds(int gameId) throws RoundsDaoPersistanceException;
    
    Rounds getRoundId(int roundId) throws RoundsDaoPersistanceException;
    
    Rounds addRound(Rounds round) throws RoundsDaoPersistanceException;
    
}
