package beadando_01.Controllers;

import beadando_01.Builder.Song;
import beadando_01.Models.MusicDBModel;
import beadando_01.TemplateMethod.MusicDB_IO;
import beadando_01.TemplateMethod.MusicExport;
import beadando_01.TemplateMethod.MusicImport;
import beadando_01.Views.MusicDBView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MusicDBController {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());
    
    private MusicDBView view;
    private MusicDBModel model;
    
    public MusicDBController(MusicDBView view, MusicDBModel model){
        this.view = view;
        this.model = model;
        
        view.AddAddSongActionListener(new AddSongListener());
        view.AddDeleteSongActionListener(new DeleteListener());
        view.AddImportActionListener(new ImportListener());
        view.AddExportActionListener(new ExportListener());
        
        try {
            model.RefreshList(view.getList());
        } catch (SQLException ex) {
            Logger.getLogger(MusicDBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    class AddSongListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae){              
            
            if (!view.getAlbum().isEmpty() && !view.getArtist().isEmpty() && !view.getTitle().isEmpty()) {
                Song newSong;
                try {
                    newSong = model.AddSong(view.getTitle(), view.getArtist(), view.getAlbum());                
                    view.getList().addElement(newSong.ToString());
                    LOGGER.info("Elem sikeresen listához adva.");
                } catch (SQLException ex) {
                    Logger.getLogger(MusicDBController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                JOptionPane.showMessageDialog(view, "Minden mezőt ki kell tölteni!");
                LOGGER.info("Nem sikerült az elem listához adása. (Minden mezőt ki kell tölteni!)");
            }                        
        }                        
    }
    
    class DeleteListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent ae){
            int selectedIndex = view.getIndex();
            if (selectedIndex != -1) {
                view.getList().remove(selectedIndex);
                LOGGER.info("Elem sikeresen törölve.");
            }
            else{
                JOptionPane.showMessageDialog(view, "Jelöld ki a törlendő elemet!");
                LOGGER.info("Nem sikerült az elem törlése. (Nincs kijelölt elem!)");
            }
        }
    }
    
    class ImportListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae){
            
            JFileChooser fc = view.getFileChooser();
            MusicDB_IO io = new MusicImport();            
            LOGGER.info("Elem olvasása...");
            if(io.SetUp(fc) == JFileChooser.APPROVE_OPTION){
                try {
                    File myfile = fc.getSelectedFile();
                    List<String> temp = new ArrayList<>();
                    Files.lines(myfile.toPath()).forEach(s -> temp.add(s));  
                    for (String string : temp) {
                        String[] tempA = string.split("[- ()]+");
                        try {
                            model.AddSong(tempA[1], tempA[0], tempA[2]);
                        } catch (SQLException ex) {
                            Logger.getLogger(MusicDBController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }                    
                    LOGGER.info("Sikeres beolvasás.");
                    
                } catch (IOException ex) {
                    Logger.getLogger(MusicDBController.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (NullPointerException ex){
                    LOGGER.info("Sikertelen beolvasás.");
                }
                try {
                    model.RefreshList(view.getList());
                } catch (SQLException ex) {
                    Logger.getLogger(MusicDBController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    class ExportListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae){
            
            JFileChooser fc = view.getFileChooser();
            MusicDB_IO io = new MusicExport();            
            LOGGER.info("Elem írása...");
            if(io.SetUp(fc) == JFileChooser.APPROVE_OPTION){
                try(FileWriter fw = new FileWriter(fc.getSelectedFile()+".txt")) {
                    for (int i = 0; i < view.getList().getSize(); i++) {
                        fw.write(view.getList().getElementAt(i).toString() + System.lineSeparator());
                    }
                }
                catch(IOException ex) {
                    Logger.getLogger(MusicDBController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
        
}
