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
import tutorial.dto.Rounds;

/**
 *
 * @author Austin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoundsDaoDbImplTest {
    
    @Autowired
    RoundsDao rdDao;
    
    @Autowired
    GameDao gameDao;
    
    public RoundsDaoDbImplTest() {
    }
    

    /**
     * Test of getAllRounds method, of class RoundsDaoDbImpl.
     * @throws tutorial.dao.GameDaoPersistanceException
     * @throws tutorial.dao.RoundsDaoPersistanceException
     */
    @Test
    public void testGetAddAllRounds() throws GameDaoPersistanceException, RoundsDaoPersistanceException {
        int gameId = 1;
        
        Game game1 = new Game();
        game1.setSolution("2345");
        game1.setIsDone(false);
        gameDao.addGame(game1);
        
        Rounds round1 = new Rounds();
        round1.setGameId(gameId);
        round1.setUserGuess("1786");
        round1.setGameResult("e:0:p:0");
        rdDao.addRound(round1);
        
        Rounds round2 = new Rounds();
        round2.setGameId(gameId);
        round2.setUserGuess("2345");
        round2.setGameResult("e:4:p:0");
        rdDao.addRound(round2);
        
        List<Rounds> rds = rdDao.getAllRounds(gameId);
        
        assertEquals(2, rds.size());
        assertNotNull(round1 = rdDao.getRoundId(round1.getRoundId()));
        
    }


    
}
