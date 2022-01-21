package com.javadatabase.app;

//import com.sun.source.tree.WhileLoopTree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "600w91w77W";

        try {

            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            //statement.execute("UPDATE Courses SET name='Веб-разработчик с нуля до PRO' WHERE id=1");

            //ResultSet resultSet = statement.executeQuery("SELECT * FROM purchaselist");
            ResultSet resultSet = statement.executeQuery("SELECT crs.name , COUNT(MONTH (sub.subscription_date))/(TIMESTAMPDIFF (MONTH , min(sub.subscription_date), max(sub.subscription_date))) as avgMonth FROM Courses crs JOIN Subscriptions sub ON crs.id = sub.course_id GROUP BY crs.name");
            //ResultSet resultSet = statement.executeQuery("SELECT crs.name , COUNT(MONTH (sub.subscription_date))/(TIMESTAMPDIFF (MONTH , min (sub.subscription_date), max (sub.subscription_date)) as avgMonth FROM Courses crs JOIN Subscriptions sub ON crs.id = sub.course_id GROUP BY crs.name");
            while (resultSet.next())
            {
                String courseName = resultSet.getString("name");
                System.out.println("Curse: " + courseName);

                String courseName2 = resultSet.getString(  "avgMonth");
                System.out.println("Middle: "+ courseName2);
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception exception)
        {

        }
        //System.out.println( "Hello World!" );
    }
}
