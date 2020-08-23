/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.service;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutorial.dao.GameDao;
import tutorial.dao.GameDaoPersistanceException;
import tutorial.dao.RoundsDao;
import tutorial.dao.RoundsDaoPersistanceException;
import tutorial.dto.Game;
import tutorial.dto.Rounds;

/**
 *
 * @author Austin
 */
@Service
public class GuessNumService {

    @Autowired
    GameDao gmDao;

    @Autowired
    RoundsDao rdDao;

    public List<Game> getAllGames() throws GameDaoPersistanceException {
        List<Game> newGames = gmDao.getAllGames();
        for (Game game : newGames) {
            if (!game.isIsDone()) {
                game.setSolution("####");
            }
        }
        return newGames;
    }

    public List<Rounds> getAllRounds(int gameId) throws RoundsDaoPersistanceException {
        return rdDao.getAllRounds(gameId);
    }

    public Rounds makeUserGuess(Rounds round) throws GameDaoPersistanceException, RoundsDaoPersistanceException, GameDataInvalidException, GameIsFinishedException {
        String solution = gmDao.getOneGameById(round.getGameId()).getSolution();
        
        if(gmDao.getOneGameById(round.getGameId()).isIsDone() == true){
            throw new GameIsFinishedException("Game is over no more guessing");
        }
        
        String userGuess = round.getUserGuess();
        String validUserGuess = validateUserGuess(userGuess);
        //userGuess.validateUserGuess();
        String result = calcResult(validUserGuess, solution);
        round.setGameResult(result);
        
        if(validUserGuess.equals(solution)){
            Game game = getGameById(round.getGameId());
            game.setIsDone(true);
            gmDao.updateGame(game);
        }
        
        return rdDao.addRound(round);
    }
    
    public Game getGameById(int gameId) throws GameDaoPersistanceException{
        Game game = gmDao.getOneGameById(gameId);
        if(!game.isIsDone()){
            game.setSolution("####");
        }
        
        return game;
    }
    
    public Game addGame(Game game) throws GameDaoPersistanceException{
        return gmDao.addGame(game);
    }
    
    
    public int genNewGame() throws GameDaoPersistanceException{
        Game game = new Game();
        game.setSolution(calcAnswer());
        game = gmDao.addGame(game);
        
        return game.getGameId();
    }
    
    //validate userguess
    public String validateUserGuess(String userGuess) throws GameDataInvalidException{
        char[] userChar = new char[userGuess.length()];
        for (int i = 0; i < userGuess.length(); i++) { 
            userChar[i] = userGuess.charAt(i); 
        }
        
        for(int j = 0; j<userGuess.length(); j++){
            for(int k = 0; k < j; k++){
                if(userChar[j] == userChar[k]){
                    throw new GameDataInvalidException("Repeat value in user guess");
                }
            }
        }
        return userGuess;
    }

    private String calcAnswer() {
        Random rd = new Random();

        int place1 = rd.nextInt(10);
        int place2 = rd.nextInt(10);

        while (place2 == place1) {
            place2 = rd.nextInt(10);
        }

        int place3 = rd.nextInt(10);
        while (place3 == place2 || place3 == place1) {
            place3 = rd.nextInt(10);
        }

        int place4 = rd.nextInt(10);
        while (place4 == place3 || place4 == place2 || place4 == place1) {
            place4 = rd.nextInt(10);
        }

        String sol = String.valueOf(place1) + String.valueOf(place2)
                + String.valueOf(place3) + String.valueOf(place4);

        return sol;
    }

    public String calcResult(String userGuess, String solution) throws GameDataInvalidException{
        char[] guessChar = userGuess.toCharArray();
        if(guessChar.length != 4){
            throw new GameDataInvalidException("Needs a 4 character guess");
        }
        char[] solChar = solution.toCharArray();
        int exactRight = 0;
        int posRight = 0;

        for (int i = 0; i < guessChar.length; i++) {
            if (solution.indexOf(guessChar[i]) > -1) {
                if (guessChar[i] == solChar[i]) {
                    exactRight++;

                } else {
                    posRight++;
                }
            }
        }
        String calculatedResult = "e:" + exactRight + ":p:" + posRight;

        return calculatedResult;
    }
}
