/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.mysql.MySqlDAOFactory;

/**
 *
 * @author kashwaa
 */
public abstract class DAOFactory {
    public static final int MY_SQL = 0;
    
    public abstract AreaDAO getAreaDAO();
    public abstract UserDAO getUserDAO();
    
    public static DAOFactory getDAOFactory(int factoryType){
        switch(factoryType){
            case MY_SQL:
                return new MySqlDAOFactory();
            default:
                throw new UnsupportedOperationException("Type is not supported!");
        }
    }
}
