/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.dao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tutorial.dto.Game;

/**
 *
 * @author Austin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GameDaoDbImplTest {
    
    @Autowired
    GameDao gameDao;
    
    public GameDaoDbImplTest() {
    }
    

    /**
     * Test of getAllGames method, of class GameDaoDbImpl.
     * @throws tutorial.dao.GameDaoPersistanceException
     */
    @Test
    public void testGetAllGames() throws GameDaoPersistanceException {
        Game game1 = new Game();
        game1.setSolution("2345");
        game1.setIsDone(false);
        gameDao.addGame(game1);
        
        Game game2 = new Game();
        game2.setSolution("3456");
        game2.setIsDone(false);
        gameDao.addGame(game2);
        
        List<Game> gamesList = gameDao.getAllGames();
        
        assertEquals(2, gamesList.size());
        assertTrue(gamesList.contains(game1));
        assertTrue(gamesList.contains(game2));
        
    }


    /**
     * Test of addGame method, of class GameDaoDbImpl.
     */
    @Test
    public void testAddGetGame() throws GameDaoPersistanceException {
        Game game1 = new Game();
        game1.setSolution("2345");
        game1.setIsDone(false);
        gameDao.addGame(game1);
        
        Game fromTheDao = gameDao.getOneGameById(game1.getGameId());
        assertEquals(game1, fromTheDao);
    }

    /**
     * Test of updateGame method, of class GameDaoDbImpl.
     */
    @Test
    public void testUpdateGame() throws GameDaoPersistanceException {
        Game game1 = new Game();
        game1.setSolution("2345");
        game1.setIsDone(false);
        gameDao.addGame(game1);
        
        Game fromTheDao = gameDao.getOneGameById(game1.getGameId());
        assertEquals(game1, fromTheDao);
        
        game1.setIsDone(true);
        
        gameDao.updateGame(game1);
        
        assertNotEquals(game1, fromTheDao);
        
        fromTheDao = gameDao.getOneGameById(game1.getGameId());
        assertEquals(game1, fromTheDao);
        
    }
    
}
