package beadando_01.TemplateMethod;

import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public abstract class MusicDB_IO {
        
    public int SetUp(JFileChooser fc){
        SetUpDialogTitle(fc);
        SetUpFileFilter(fc);
        return SetDialogType(fc);
    }

    public abstract void SetUpDialogTitle(JFileChooser fc);
    
    private void SetUpFileFilter(JFileChooser fc){
        fc.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
    }
    
    public abstract int SetDialogType(JFileChooser fc);
}
