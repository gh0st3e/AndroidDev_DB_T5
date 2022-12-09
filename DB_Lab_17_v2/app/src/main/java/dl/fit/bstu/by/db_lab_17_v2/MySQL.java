package dl.fit.bstu.by.db_lab_17_v2;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQL {
    public Connection Connection(String url, String username, String password){
        try{
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            Log.d("lab17","Driver is working!");
            try{
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Connection connection = DriverManager.getConnection(url,username,password);
                Log.d("lab17","Connection succesfull!");

                return connection;
            }
            catch (Exception ex){
                Log.d("lab17","Connection failed...");

                ex.printStackTrace();
            }
        }
        catch(Exception ex){
            Log.d("lab17","Driver isn't working...");

            ex.printStackTrace();
        }
        return null;
    }
}
