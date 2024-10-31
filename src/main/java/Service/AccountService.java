package Service;

import DAO.AccountDAO;
import Model.Account;


public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account registerUser(Account account){
        if(account.getUsername() != "" && account.getPassword().length() >= 4 && !this.accountDAO.verifyUser(account.getAccount_id())){
            return this.accountDAO.registerUser(account);
        }
       return null;
    }

    public Account verifyLoginUser(Account account){
        return this.accountDAO.verifyLoginUser(account);
    }
    
}
