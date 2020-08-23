/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tutorial.dao.GameDaoPersistanceException;
import tutorial.dao.RoundsDaoPersistanceException;
import tutorial.dto.Game;
import tutorial.dto.Rounds;
import tutorial.service.GameDataInvalidException;
import tutorial.service.GameIsFinishedException;
import tutorial.service.GuessNumService;

/**
 *
 * @author Austin
 */
@RestController
@RequestMapping("/api")
public class GameController {
    
    @Autowired
    GuessNumService serv;
    
    @PostMapping("/begin")
    public int createNewGame() throws GameDaoPersistanceException{
        return serv.genNewGame();
    }
    
    @PostMapping("/guess")
    public Rounds makeUserGuess(@RequestBody Rounds round) throws RoundsDaoPersistanceException, GameDaoPersistanceException, GameDataInvalidException, GameIsFinishedException{
        return serv.makeUserGuess(round);
    }
    
    @GetMapping("/game")
    public List<Game> getAllGames() throws GameDaoPersistanceException{
        return serv.getAllGames();
    }
    
    @GetMapping("/game/{gameId}")
    public Game getSoloGame(@PathVariable("gameId") int gameId) throws GameDaoPersistanceException{
        return serv.getGameById(gameId);
    }
    
    @GetMapping("/rounds/{gameId}")
    public List<Rounds> getRounds(@PathVariable("gameId") int gameId) throws RoundsDaoPersistanceException{
        return serv.getAllRounds(gameId);
    }
    
    
}
