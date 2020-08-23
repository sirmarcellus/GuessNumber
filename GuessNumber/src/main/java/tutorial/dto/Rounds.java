/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Austin
 */
public class Rounds {
    private int roundId;
    private int gameId;
    private LocalDateTime guessTime;
    private String userGuess;
    private String gameResult;

    public Rounds() {
    }

    public Rounds(int gameId, String userGuess) {
        this.gameId = gameId;
        this.userGuess = userGuess;
    }

    public Rounds(int roundId, int gameId, LocalDateTime guessTime, String userGuess, String gameResult) {
        this.roundId = roundId;
        this.gameId = gameId;
        this.guessTime = guessTime;
        this.userGuess = userGuess;
        this.gameResult = gameResult;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public LocalDateTime getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(LocalDateTime guessTime) {
        this.guessTime = guessTime;
    }

    public String getUserGuess() {
        return userGuess;
    }

    public void setUserGuess(String userGuess) {
        this.userGuess = userGuess;
    }

    public String getGameResult() {
        return gameResult;
    }

    public void setGameResult(String gameResult) {
        this.gameResult = gameResult;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.roundId;
        hash = 41 * hash + this.gameId;
        hash = 41 * hash + Objects.hashCode(this.guessTime);
        hash = 41 * hash + Objects.hashCode(this.userGuess);
        hash = 41 * hash + Objects.hashCode(this.gameResult);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rounds other = (Rounds) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.userGuess, other.userGuess)) {
            return false;
        }
        if (!Objects.equals(this.gameResult, other.gameResult)) {
            return false;
        }
        if (!Objects.equals(this.guessTime, other.guessTime)) {
            return false;
        }
        return true;
    }
    
    
}
