package beadando_01;

import beadando_01.Controllers.LoginController;
import beadando_01.Models.LoginModel;
import beadando_01.Views.LoginView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Beadando_01 {

    public static void main(String[] args) {
        LoginView view = new LoginView();
        LoginModel model = new LoginModel();
        LoginController controller = new LoginController(view, model);
                        
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

}
