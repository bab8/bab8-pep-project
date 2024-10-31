package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class AccountDAO {

    public Account registerUser(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Prepare sql logic
            String sql = "INSERT INTO account(username, password) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Set statement parameters
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            //execute sql
            ps.executeUpdate();
            ResultSet accountKeyResultSet = ps.getGeneratedKeys();
            if(accountKeyResultSet.next()){
                int generatedAccountID = (int) accountKeyResultSet.getLong(1);
                return new Account(generatedAccountID, account.getUsername(), account.getPassword());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Boolean VerifyLoginUser(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Prepare sql logic
            String sql = "SELECT account WHERE username=? AND password=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set statement parameters
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            //execute sql
            ResultSet accountResultSet =  ps.executeQuery();
            if(accountResultSet.next()){
                //TODO: may need further check if account ID exists
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
}
