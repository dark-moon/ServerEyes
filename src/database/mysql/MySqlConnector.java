package database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kashwaa
 */
public class MySqlConnector {

    public class MysqlConnector {

        private Connection cnn;
        private final String dbName;
        private final String mysqlUser;
        private final String mysqlPassword;
        private final String server;

        /**
         *Initializes a connector to a database that can be asked for a connection.
         * (Not thread safe).
         * @param server the server name of IP_address.
         * @param dbName The name of the database.
         * @param mysqlUserName The user-name used to connect to the database
         * server.
         * @param mysqlPassword The password used to connect to the database
         * server.
         * @throws SQLException
         * @throws java.lang.ClassNotFoundException
         */
        public MysqlConnector(String server,String dbName, String mysqlUserName, String mysqlPassword) throws SQLException, ClassNotFoundException {
            this.server = server;
            this.dbName = dbName;
            this.mysqlUser = mysqlUserName;
            this.mysqlPassword = mysqlPassword;
            initConnection();
        }

        private void initConnection() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.jdbc.Driver");
            cnn = DriverManager.getConnection("jdbc:mysql://"+ server +"/" + 
                    dbName, mysqlUser, mysqlPassword);
        }

        /**
         * @return the database Connection;
         */
        public Connection getConnection() {
            return cnn;
        }
    }
}
