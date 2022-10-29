package org.example;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Main {



    private static final Logger logger = LogManager.getLogger(org.example.Main.class);
    private final static String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "log4j2.xml";

    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS EMPLEADO;";
    private static final String CREATE_TABLE
            = "CREATE TABLE EMPLEADO (ID INT PRIMARY KEY, NOMBRE VARCHAR (50), APELLIDO VARCHAR (50), DNI CHAR (8), TIPO VARCHAR (50))";


    private static final String SQLUPDATE = "UPDATE EMPLEADO SET NOMBRE='VICTORIA' WHERE ID=3";

    private static final String SELECT_ALL = "SELECT * FROM EMPLEADO";

    public static void main(String[] args) throws SQLException {

        var connection = obtenerConexion();
        var statement = connection.createStatement();
        statement.execute(DROP_TABLE_IF_EXISTS);
        statement.execute(CREATE_TABLE);
        punto1(connection, Empleado.EmpleadoStatic("'1'","'Ariel'","'Rubel'","'21094467'","'pichy'"));
        punto1(connection, Empleado.EmpleadoStatic("'2'","'Tomy'","'Dopazo'","'23094467'","'crack'"));
        punto1(connection, Empleado.EmpleadoStatic("'2'","'Victoria'","'Garcia'","'22094467'","'Jefa'"));
        punto1(connection, Empleado.EmpleadoStatic("'3'","'Celina'","'Dion'","'21094467'","'pichy'"));

        punto2(connection, SQLUPDATE);
    }


    private static void punto1(Connection connection, String INSERT_EMPLEADO) throws SQLException {


        var statement = connection.createStatement();
        //var resultSet = statement.executeQuery(SELECT_ALL);->Solo por si crece el ejercicio

        try {
            statement.execute(INSERT_EMPLEADO);
            //System.out.println(INSERT_EMPLEADO+": INSERTADO OK");

        } catch (Exception SQLException) {
            logger.error(SQLException.getMessage() + INSERT_EMPLEADO);


        }
    }


    private static void punto2(Connection connection,String SQLUPDATE) throws SQLException {
        var statement = connection.createStatement();

        try {
            statement.execute(SQLUPDATE);
            logger.debug("ejecutado SQLUPDATE:"+SQLUPDATE);
            System.out.println(SQLUPDATE+": UPDATE OK");

        } catch (Exception SQLException) {
            logger.debug(SQLException.getMessage());
            System.out.println(SQLUPDATE+": **** ERROR ****");

        }

    }


    private static void startLogger() throws IOException {
        var source = new ConfigurationSource(new FileInputStream(log4jConfigFile));
        Configurator.initialize(null, source);
    }

    private static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:~/digital1;DB_CLOSE_ON_EXIT=TRUE");
    }
}
