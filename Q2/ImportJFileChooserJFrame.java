package Q2;

import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ImportJFileChooserJFrame extends JFrame {
	
	
	public ImportJFileChooserJFrame()
	{
		super("Please choose file for reading");
		
	}
	public Path getFilePath()
	{
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int result = fileChooser.showOpenDialog(this);
		
		if(result == JFileChooser.CANCEL_OPTION)
		{
			this.setVisible(false);
			return null;
		}
		return fileChooser.getSelectedFile().toPath();
	}

}
