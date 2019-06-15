package beadando_01.TemplateMethod;

import javax.swing.JFileChooser;

public class MusicImport extends MusicDB_IO{

    @Override
    public void SetUpDialogTitle(JFileChooser fc) {
        fc.setDialogTitle("File import");
    }

    @Override
    public int SetDialogType(JFileChooser fc) {
        return fc.showOpenDialog(null);
    }
    
}
