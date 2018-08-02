/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.mysql;

import database.AreaDAO;
import database.DAOFactory;
import database.UserDAO;

/**
 *
 * @author kashwaa
 */
public class MySqlDAOFactory extends DAOFactory{

    @Override
    public AreaDAO getAreaDAO() {
        return new MySqlAreaDAO();
    }

    @Override
    public UserDAO getUserDAO() {
        return new MySqlUserDAO();
    }
    
}
