/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.dao;

/**
 *
 * @author Austin
 */
public class RoundsDaoPersistanceException extends Exception{
    public RoundsDaoPersistanceException(String message) {
        super(message);
    }

    public RoundsDaoPersistanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
