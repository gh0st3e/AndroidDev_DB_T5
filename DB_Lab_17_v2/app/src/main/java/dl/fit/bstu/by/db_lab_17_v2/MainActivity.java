package dl.fit.bstu.by.db_lab_17_v2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //global
    Connection connection;
    //Edits
    EditText userQueryET;
    EditText userSelectQueryET;
    EditText moneyP_ET;
    EditText employeesP_ET;
    EditText idForDelET;
    EditText moneyForFuncET;
    EditText passwordET;
    //ListView
    ListView companiesListView;

    //List
    private List<Company> companies;
    private ArrayAdapter<Company> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "jdbc:mysql://MYSQL5030.site4now.net/db_a90c9a_android?verifyServerCertificate=false&serverTimezone=UTC&useSSL=false";
        String username = "a90c9a_android";
        String password = "android8403";

        userQueryET = findViewById(R.id.userQuery);
        userSelectQueryET = findViewById(R.id.userSelectQuery);
        moneyP_ET = findViewById(R.id.moneyP);
        employeesP_ET = findViewById(R.id.employeesP);
        idForDelET = findViewById(R.id.idForDel);
        moneyForFuncET = findViewById(R.id.moneyForFunc);
        passwordET = findViewById(R.id.password);

        companiesListView = findViewById(R.id.companiesListView);
        companies = new ArrayList() {
        };

        MySQL db = new MySQL();
        connection = db.Connection(url,username,password);

        loadCompanies();
    }

    private void loadCompanies(){
        companies.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM companies");
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int employees = rs.getInt(3);
                int money = rs.getInt(4);
                companies.add(new Company(id,name,employees,money));
            }
            ArrayAdapter<Company> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, companies);
            companiesListView.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void execUserQueryBtn(View view) {
        String userQuery = userQueryET.getText().toString();
        Log.d("lab17",userQuery);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(userQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UserSelectQueryBtn(View view) {
        String userQuery = userSelectQueryET.getText().toString();
        companies.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(userQuery);
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int employees = rs.getInt(3);
                int money = rs.getInt(4);
                companies.add(new Company(id,name,employees,money));
            }
            ArrayAdapter<Company> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, companies);
            companiesListView.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SelectQueryWIthPBtn(View view) {
        String selectWithPQuery = "SELECT * FROM companies WHERE ";

        String moneyP = moneyP_ET.getText().toString();
        String employeesP = employeesP_ET.getText().toString();

        if (moneyP == null && employeesP==null){
            return;
        }

        selectWithPQuery += "money="+moneyP+" AND employees="+employeesP;

        companies.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectWithPQuery);
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int employees = rs.getInt(3);
                int money = rs.getInt(4);
                companies.add(new Company(id,name,employees,money));
            }
            ArrayAdapter<Company> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, companies);
            companiesListView.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteRowBtn(View view) {
        String idForDel = idForDelET.getText().toString();
        if(idForDel != null){
            int id = companies.get(Integer.parseInt(idForDel)-1).ID;

            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM companies WHERE id="+String.valueOf(id));
                loadCompanies();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void RefreshListBtn(View view) {
        loadCompanies();
    }


    public void InsertRowsBtn(View view) {
        String insertRows1 = "INSERT INTO companies VALUES(15,'Innowise',20000,1000000)";
        String insertRows2 = "INSERT INTO companies VALUES(16,'ITechArt',5000,3228)";
        String insertRows3 = "INSERT INTO companies VALUES(17,'Epam',2,0)";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertRows1);
            statement.executeUpdate(insertRows2);
            statement.executeUpdate(insertRows3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadCompanies();
    }

    public void CallProcedureBtn(View view) {
        companies.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("CALL getall");
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int employees = rs.getInt(3);
                int money = rs.getInt(4);
                companies.add(new Company(id,name,employees,money));
            }
            ArrayAdapter<Company> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, companies);
            companiesListView.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CallFuncBtn(View view) {
        String moneyForFunc = moneyForFuncET.getText().toString();
        if(moneyForFunc!=null){
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT count_get("+moneyForFunc+")");
                while(rs.next()){
                    int count = rs.getInt(1);
                    Toast.makeText(this,"companies with money more then selected = "+String.valueOf(count),Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void showInfoBtn(View view) {
        String password = passwordET.getText().toString();
        if (password.equals("android8403")){
            try {
                String result = "";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SHOW TABLE STATUS");
                while(rs.next()){
                    result+="Name: " + rs.getString(1) + "\n";
                    result+="Engine: " + rs.getString(2) + "\n";
                    result+="Version: " + rs.getString(3) + "\n";
                    result+="Row_format: " + rs.getString(4) + "\n";
                    result+="Rows: " + rs.getString(5) + "\n";
                    result+="Avg_row_length: " + rs.getString(6) + "\n";
                    result+="Data_length: " + rs.getString(7) + "\n";
                    result+="Max_data_length: " + rs.getString(8) + "\n";
                    result+="Index_length: " + rs.getString(9) + "\n";
                    result+="Data_free: " + rs.getString(10) + "\n";
                    result+="Auto_increment: " + rs.getString(11) + "\n";
                    result+="Create_time: " + rs.getString(12);
                }
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage(result);
                b.setTitle("Структура").setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog ad = b.create();
                ad.show();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}