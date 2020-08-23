/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.service;

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

/**
 *
 * @author Austin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GuessNumServiceTest {
    
    @Autowired
    GuessNumService serv;
    
    public GuessNumServiceTest() {
    }
    


    @Test
    public void testAlonePartialLogic() throws GameDataInvalidException{
        String userGuess = "3456";
        String solution = "5689";
        
        String res = serv.calcResult(userGuess, solution);
        assertEquals("e:0:p:2", res);
    }
    
    @Test
    public void testExactAnswer() throws GameDataInvalidException{
        String userGuess = "3456";
        String solution = "3456";
        
        String res = serv.calcResult(userGuess, solution);
        assertEquals("e:4:p:0", res);
    }
    
    @Test
    public void testNoRightVal() throws GameDataInvalidException{
        String userGuess = "3456";
        String solution = "1278";
        
        String res = serv.calcResult(userGuess, solution);
        assertEquals("e:0:p:0", res);
    }
    
    @Test
    public void testAllPartial() throws GameDataInvalidException{
        String userGuess = "3456";
        String solution = "6543";
        
        String res = serv.calcResult(userGuess, solution);
        assertEquals("e:0:p:4", res);
    }
    
    @Test
    public void testOffByOnePos() throws GameDataInvalidException{
        String userGuess = "3456";
        String solution = "3546";
        
        String res = serv.calcResult(userGuess, solution);
        assertEquals("e:2:p:2", res);
    }
    // tests needed  
    //validate data testing :: test
    //game exists :: check
    // game answer is not shown :: test
    // test generated answer is unique and 4 values :: check :: test
    // add GameIsFinishd Exception  :: check :: test
    
}
