package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Model.Account;
import Util.ConnectionUtil;

public class AccountService {

    public Account registerUser(Account account){
       return account;
    }

    public Boolean verifyLoginUser(Account account){
        return false;
    }
    
}
