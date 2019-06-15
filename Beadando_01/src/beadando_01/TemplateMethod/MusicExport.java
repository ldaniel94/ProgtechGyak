package beadando_01.TemplateMethod;

import javax.swing.JFileChooser;

public class MusicExport extends MusicDB_IO{

    @Override
    public void SetUpDialogTitle(JFileChooser fc) {
        fc.setDialogTitle("File export");
    }

    @Override
    public int SetDialogType(JFileChooser fc) {
        return fc.showSaveDialog(null);
    }
    
}
