package beadando_01.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import beadando_01.Models.LoginModel;
import beadando_01.Models.MusicDBModel;
import beadando_01.Models.RegisterModel;
import beadando_01.Views.LoginView;
import beadando_01.Views.MusicDBView;
import beadando_01.Views.RegisterView;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;

public class LoginController {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());
    
    private LoginView view;
    private LoginModel model;
    
    public LoginController(LoginView view, LoginModel model){
        this.view = view;
        this.model = model;
        
        view.AddLoginActionListener(new LoginListener());
        view.AddRegisterActionListener(new RegisterListener());        
    }
    
    class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            LOGGER.info("Bejelentkezési kísérlet.");
            try {
                if(model.Login(view.getName(), view.getPassword())){
                    MusicDBView mdbView = new MusicDBView();
                    MusicDBModel mdbModel = new MusicDBModel();
                    MusicDBController mdbController = new MusicDBController(mdbView, mdbModel);
                    mdbView.setLocationRelativeTo(null);
                    mdbView.setVisible(true);
                    view.setVisible(false);
                    LOGGER.info("Sikeres bejelentkezés.");
                }
                else{
                    JOptionPane.showMessageDialog(view, "Hibás felhasználónév vagy jelszó!");
                    LOGGER.info("Sikertelen bejelentkezés. (Hibás felhasználónév vagy jelszó!)");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Adatbázis hiba!");
                LOGGER.log(Level.INFO, "Adatbázis hiba: {0}", ex);
            }
            
        }
        
    }
    
    class RegisterListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            LOGGER.info("Regisztrációs kísérlet.");
            RegisterView regView = new RegisterView();
            RegisterModel regModel = new RegisterModel();
            RegisterController regController = new RegisterController(regView, regModel);
            regView.setLocationRelativeTo(null);
            regView.setVisible(true);
            view.setVisible(false);
        }
        
    }
}
