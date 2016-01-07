package httparser;

import javax.swing.UIManager;
import java.awt.*;
import httparser.frame.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class HTTParser {

  //Construct the application
  public HTTParser() {
  }

  //Main method
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    FrmMain frame = new FrmMain();

  	boolean packFrame = false;
  	boolean bLoadUrl = false;
  	boolean bShowFrame = true; 

  	String szUrl = null;
    for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if ("-url".equalsIgnoreCase(arg)) {
				szUrl = args[++i];
			}
			else if ("-clean".equalsIgnoreCase(arg)) {
				frame.setClean(new Boolean(args[++i]).booleanValue());
			}
			else if ("-indent".equalsIgnoreCase(arg)) {
				frame.setIndent(new Boolean(args[++i]).booleanValue());
			}
			else if ("-sublink".equalsIgnoreCase(arg)) {
				frame.setSubLink(new Boolean(args[++i]).booleanValue());
			}
			else if ("-correctHtml".equalsIgnoreCase(arg)) {
				frame.setCorrectHtml(new Boolean(args[++i]).booleanValue());
			}
			else if ("-userIncludeList".equalsIgnoreCase(arg)) {
				frame.setUseIncludeList(new Boolean(args[++i]).booleanValue());
			}
			else if ("-userIncludeListAsMask".equalsIgnoreCase(arg)) {
				frame.setUseIncludeListAsMask(new Boolean(args[++i]).booleanValue());
			}
			else if ("-useTimeOut".equalsIgnoreCase(arg)) {
				frame.setUseTimeOut(new Boolean(args[++i]).booleanValue());
			}
			else if ("-editorHtmlUpd".equalsIgnoreCase(arg)) {
				frame.setEditorHtmlUpd(new Boolean(args[++i]).booleanValue());
			}
			else if ("-showPage".equalsIgnoreCase(arg)) {
				frame.setShowPage(new Boolean(args[++i]).booleanValue());
			}
			else if ("-packFrame".equalsIgnoreCase(arg)) {
				packFrame = new Boolean(args[++i]).booleanValue();
			}
			else if ("-loadUrl".equalsIgnoreCase(arg)) {
				bLoadUrl = new Boolean(args[++i]).booleanValue();
			}
			else if ("-showFrame".equalsIgnoreCase(arg)) {
				bShowFrame = new Boolean(args[++i]).booleanValue();
			}
			else if ("-browserWeb".equalsIgnoreCase(arg)) {
				frame.setBrowserWeb(args[++i]);
			}
			else if ("-maxSubLevel".equalsIgnoreCase(arg)) {
				frame.setMaxSubLevel(Integer.parseInt(args[++i]));
			}
		}
    if (bShowFrame) {
      //Validate frames that have preset sizes
      //Pack frames that have useful preferred size info, e.g. from their layout
      if (packFrame) {
        frame.pack();
      }
      else {
        frame.validate();
      }
	    //Center the window
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    Dimension frameSize = frame.getSize();
	    if (frameSize.height > screenSize.height) {
	      frameSize.height = screenSize.height;
	    }
	    if (frameSize.width > screenSize.width) {
	      frameSize.width = screenSize.width;
	    }
	    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	    // DROCA Ne fait pas l'affichage de la fenêtre
	    frame.setVisible(true);
    }
    if (szUrl!=null)
			frame.setUrl(szUrl);
    if (bLoadUrl) {
	    // Clique sur le bouton GoUrl
	    frame.doGoUrl();
    }
  }
}