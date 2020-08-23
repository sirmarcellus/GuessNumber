/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.dto;

import java.util.Objects;

/**
 *
 * @author Austin
 */
public class Game {
    private int gameId;
    private String solution;
    private boolean isDone;

    public Game() {
    }

    public Game(String solution, boolean isDone) {
        this.solution = solution;
        this.isDone = isDone;
    }

    public Game(int gameId, String solution, boolean isDone) {
        this.gameId = gameId;
        this.solution = solution;
        this.isDone = isDone;
    }
    
    

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public boolean isIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.gameId;
        hash = 43 * hash + Objects.hashCode(this.solution);
        hash = 43 * hash + (this.isDone ? 1 : 0);
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
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.isDone != other.isDone) {
            return false;
        }
        if (!Objects.equals(this.solution, other.solution)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", solution=" + solution + ", isDone=" + isDone + '}';
    }
    
    

    
}
