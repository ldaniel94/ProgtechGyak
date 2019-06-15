package beadando_01.Controllers;

import beadando_01.Models.LoginModel;
import beadando_01.Models.RegisterModel;
import beadando_01.Views.LoginView;
import beadando_01.Views.RegisterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class RegisterController {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());
    
    private RegisterView view;
    private RegisterModel model;
    
    public RegisterController(RegisterView view, RegisterModel model){
        this.view = view;
        this.model = model;
        
        view.AddRegisterActionListener(new RegisterListener());
        view.AddCancelActionListener(new CancelListener());
    }
    
    class RegisterListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            LOGGER.info("Regisztrációs kísérlet.");
            try {
                if(model.Register(view.getName(), view.getPassword(), view.getConfPassword())){
                    JOptionPane.showMessageDialog(view, "Rikeres regisztráció!");
                    LoginView logView = new LoginView();
                    LoginModel logModel = new LoginModel();
                    LoginController logController = new LoginController(logView, logModel);
                    logView.setLocationRelativeTo(null);
                    logView.setVisible(true);
                    view.setVisible(false);
                    LOGGER.info("Sikeres regisztráció.");
                }
                else{
                    JOptionPane.showMessageDialog(view, "Hibás felhasználónév vagy jelszó!");
                    LOGGER.info("Sikertelen regisztráció. (Hibás felhasználónév vagy jelszó!)");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Adatbázis hiba!");
                LOGGER.log(Level.INFO, "Adatbázis hiba: {0}", ex);
            }
        }
    }
    
    class CancelListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            LOGGER.info("Regisztráció visszavonva.");
            LoginView logView = new LoginView();
            LoginModel logModel = new LoginModel();
            LoginController logController = new LoginController(logView, logModel);
            logView.setLocationRelativeTo(null);
            logView.setVisible(true);
            view.setVisible(false);
        }
    }
}
