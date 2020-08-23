/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.dao;

import java.util.List;
import tutorial.dto.Game;

/**
 *
 * @author Austin
 */
public interface GameDao {
    
    List<Game> getAllGames() throws GameDaoPersistanceException;
    
    Game getOneGameById(int gameId) throws GameDaoPersistanceException;
    
    Game addGame(Game game) throws GameDaoPersistanceException;
    
    void updateGame(Game round) throws GameDaoPersistanceException;
;
}
