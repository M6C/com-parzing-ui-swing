package httparser.panel;

import httparser.frame.FrmMain;
import httparser.panel.parent.PanelBrowser;
import httparser.thread.ThrdLoadUrl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.HyperlinkEvent;

public class PanelJavaBrowser extends PanelBrowser {

	private final static String DEFAULT_URL = "Url 'Http://' || 'File://'";

	private String url = DEFAULT_URL;
  private URL urlContext = null;

	private Vector heap = new Vector();
	private int indexHeap = -1;
  private BorderLayout borderLayout2 = new BorderLayout();
  private BorderLayout borderLayout3 = new BorderLayout();
  private GridLayout gridLayout1 = new GridLayout();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JEditorPane editorHtml = new JEditorPane();

  private TitledBorder titledBorder1;
  private JTextField txtUrl = new JTextField();
  private JButton btnBackUrl = new JButton();
  private JButton btnGoUrl = new JButton();
  private JButton btnNextUrl = new JButton();

  private JPanel jPanel_Url = new JPanel();
  private JLabel jLabel1 = new JLabel();
  private JPanel jPanel1 = new JPanel();

  private FrmMain frmMain = null;

  public PanelJavaBrowser(FrmMain frmMain) {
  	this.frmMain = frmMain;
      try {
          jbInit();
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void main(String[] args) {
      try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e) {}

      JFrame frame = new JFrame("JDIC API Demo - Browser");

      Container contentPane = frame.getContentPane();

      contentPane.setLayout(new GridLayout(1, 1));
      contentPane.add(new PanelJavaBrowser(null));

      frame.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
              System.exit(0);
          }
      });

      frame.pack();
      frame.setVisible(true);
  }

  private void jbInit() throws Exception {
	    borderLayout2.setHgap(10);
	    borderLayout2.setVgap(10);
	    borderLayout3.setHgap(10);
	    borderLayout3.setVgap(10);

	    titledBorder1 = new TitledBorder("");

      editorHtml.setText("");
      editorHtml.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        public void mouseMoved(MouseEvent e) {
          editorHtml_mouseMoved(e);
        }
      });
      editorHtml.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(PropertyChangeEvent e) {
          editorHtml_propertyChange(e);
        }
      });
      editorHtml.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
        public void hyperlinkUpdate(HyperlinkEvent e) {
          editorHtml_hyperlinkUpdate(e);
        }
      });
      editorHtml.setContentType("text/html; charset=ISO-8859-1");
      editorHtml.setEditable(false);

      gridLayout1.setColumns(3);
		  gridLayout1.setHgap(5);
		  gridLayout1.setVgap(5);

      txtUrl.setBorder(titledBorder1);
      txtUrl.setText(getUrl());
      jLabel1.setMaximumSize(new Dimension(25, 17));
      jLabel1.setMinimumSize(new Dimension(25, 17));
      jLabel1.setPreferredSize(new Dimension(25, 17));
      jLabel1.setText("Url : ");
      jPanel1.setLayout(gridLayout1);
      jPanel1.add(btnBackUrl, null);
      jPanel1.add(btnNextUrl, null);
      jPanel1.add(btnGoUrl, null);
      jPanel_Url.setLayout(borderLayout3);
      jPanel_Url.add(txtUrl,  BorderLayout.CENTER);
      jPanel_Url.add(jPanel1,  BorderLayout.EAST);
      jPanel_Url.add(jLabel1, BorderLayout.WEST);

      btnBackUrl.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          btnBackUrl_actionPerformed(e);
        }
      });
      btnBackUrl.setText("Back");
      btnGoUrl.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          btnGoUrl_actionPerformed(e);
        }
      });
      btnGoUrl.setText("Go");
      btnNextUrl.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          btnNextUrl_actionPerformed(e);
        }
      });
      btnNextUrl.setText("Next");

      jScrollPane1.getViewport().add(editorHtml, null);

      this.setLayout(borderLayout2);
      this.add(jScrollPane1,  BorderLayout.CENTER);
      this.add(jPanel_Url,  BorderLayout.NORTH);
  }

  void editorHtml_propertyChange(PropertyChangeEvent e) {
    if ( e.getPropertyName().equals("page") )
      // La page a été chargé
    	frmMain.setEditorHtmlUpd(true);
  }

  void editorHtml_mouseMoved(MouseEvent e) {
    Point pointMouse = e.getPoint();
    Component cpntHtml = editorHtml.getComponentAt(pointMouse);
    if ( cpntHtml!=null )
    {
      while( cpntHtml.contains(pointMouse) )
      {
        Component cpnt = cpntHtml.getComponentAt(pointMouse);
        if ( (cpnt==null)||(cpnt.equals(cpnt)) )
          break;
        else
          cpntHtml = cpnt;
      }
/*
      StringBuffer stbTitle = new StringBuffer();
      stbTitle.append(" Height:").append(cpntHtml.getHeight());
      stbTitle.append(" Width:").append(cpntHtml.getWidth());
      stbTitle.append(" X:").append(cpntHtml.getX());
      stbTitle.append(" Y:").append(cpntHtml.getY());
      this.setTitle(stbTitle.toString());
*/
    }
/*
    int iCount = editorHtml.getComponentCount();
    for (int i=0 ; i<iCount ; i++)
    {
      cpntHtml = editorHtml.getComponent(i);
      if ( cpntHtml!=null )
      {
        StringBuffer stbTitle = new StringBuffer("i:").append(i);
        stbTitle.append(" Height:").append(cpntHtml.getHeight());
        stbTitle.append(" Width:").append(cpntHtml.getWidth());
        stbTitle.append(" X:").append(cpntHtml.getX());
        stbTitle.append(" Y:").append(cpntHtml.getY());
        System.out.println(stbTitle.toString());
      }
    }
*/
  }

  void editorHtml_hyperlinkUpdate(HyperlinkEvent e) {
    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
    {
  	  try{
  	  	setURLContext(e.getURL());

  		// Ouvre la connection
  		URLConnection conn = getURLContext().openConnection();
	    /* Essai avec le paramètrage proxy*/
  		String proxyHost = frmMain.getTxtProxyHost();//"webproxy-rgs.telintrans.fr";
  		String proxyUser = frmMain.getTxtProxyUser();//"droca";
  		String proxyPwd = frmMain.getTxtProxyPwd();//"d230roca";
  		String proxyPort = frmMain.getTxtProxyPort();//"3128";
	    if (proxyHost!=null && !"".equals(proxyHost)) {
	  		String authentication = proxyUser + ":" + proxyPwd;
	  		String encodedPassword = "Basic " + new sun.misc.BASE64Encoder().encode(authentication.getBytes());
	  		System.getProperties().put("http.proxySet", "true");
	  		System.getProperties().put("http.proxyHost", proxyHost);
	  		System.getProperties().put("http.proxyPort", proxyPort);
	  		conn.setRequestProperty("Proxy-Authorization", encodedPassword);
	    }

        editorHtml.setPage(getURLContext()); 
  	  } catch (MalformedURLException murle) { 
          editorHtml.setContentType("text/html"); 
          editorHtml.setText("<html><body>MalformedURLExcepti on: " 
  		  + murle.getMessage() 
  		  + "<br>Malformed URL: Can not load url: " 
  		  + getURLContext().toExternalForm() 
  		  + "</body></html>"); 
  		  }catch(IOException ioe){ 
  			editorHtml.setContentType("text/html"); 
  			editorHtml.setText("<html><body>IOException: " 
  		  + ioe.getMessage() + "<br>Could not load url: " 
  		  + getURLContext().toExternalForm() + "</body></html>"); 
  	   } 
  		  setTxtUrl(e.getURL().toString());
  		  doGoUrl();
    }
  }

  void btnGoUrl_actionPerformed(ActionEvent e) {
	  	runLoadUrlProcess();
	
	    if ( ( indexHeap>=0) && (indexHeap<(heap.size()-1)) )
	      heap.setSize(indexHeap+1);
	    if ( (heap.size()==0) || (!((String)heap.lastElement()).equals(this.txtUrl.getText())) )
	    {
	      heap.add(this.txtUrl.getText());
	      indexHeap++;
	    }
	
	    btnNextUrl.setEnabled((indexHeap+1)<heap.size());
	    btnBackUrl.setEnabled(indexHeap>0);
	}
	void btnNextUrl_actionPerformed(ActionEvent e) {
	  if ( heap.size() > (indexHeap+1) )
	  {
	    this.txtUrl.setText((String)heap.elementAt(++indexHeap));
	    runLoadUrlProcess();
	    btnNextUrl.setEnabled((indexHeap+1)<heap.size());
	    btnBackUrl.setEnabled(indexHeap>0);
	  }
	}
	
	void btnBackUrl_actionPerformed(ActionEvent e) {
	  if ( ( heap.size() > 0 ) && ( indexHeap>= 0 ) )
	  {
	    this.txtUrl.setText((String)heap.elementAt(--indexHeap));
	    runLoadUrlProcess();
	    btnNextUrl.setEnabled((indexHeap+1)<heap.size());
	    btnBackUrl.setEnabled(indexHeap>0);
	  }
	}
  protected void runLoadUrlProcess()
  {
  	setURLContext(this.getTxtUrl());

    if ( frmMain.getRunnerLoadUrl() != null )
    	frmMain.getRunnerLoadUrl().interrupt();

    frmMain.setRunnerLoadUrl(new ThrdLoadUrl(frmMain));
    frmMain.getRunnerLoadUrl().start();
  }

  /* (non-Javadoc)
	 * @see httparser.panel.IPanelBrowser#setURLContext(java.lang.String)
	 */
  public void setURLContext(String url) {
  	if (!url.equals(DEFAULT_URL))
  		try { this.setURLContext(new URL(url)); } catch ( MalformedURLException ex ) { ex.printStackTrace(); };
  }
  /* (non-Javadoc)
	 * @see httparser.panel.IPanelBrowser#setURLContext(java.net.URL)
	 */
  public void setURLContext(URL url){
    this.urlContext = url;
  }
  /* (non-Javadoc)
	 * @see httparser.panel.IPanelBrowser#getURLContext()
	 */
  public URL getURLContext(){
    return this.urlContext;
  }
  /* (non-Javadoc)
	 * @see httparser.panel.IPanelBrowser#getTxtUrl()
	 */
  public String getTxtUrl() {
    return txtUrl.getText();
  }
  /* (non-Javadoc)
	 * @see httparser.panel.IPanelBrowser#setTxtUrl(java.lang.String)
	 */
  public void setTxtUrl(String txtUrl) {
    this.txtUrl.setText(txtUrl);
  }
  /* (non-Javadoc)
	 * @see httparser.panel.IPanelBrowser#doGoUrl()
	 */
  public void doGoUrl(){
    btnGoUrl.doClick();
  }
	/* (non-Javadoc)
	 * @see httparser.panel.IPanelBrowser#setUrl(java.lang.String)
	 */
	public void setUrl(String url) {
		this.url = url;
    txtUrl.setText(url);
	}
	/* (non-Javadoc)
	 * @see httparser.panel.IPanelBrowser#getUrl()
	 */
	public String getUrl() {
		return url;
	}

  public String getTxtHtml() {
    return editorHtml.getText();
  }
  public void setTxtHtml(String txtHtml) {
//    this.editorHtml.setText(txtHtml);
  	try { editorHtml.setText(txtHtml); } catch ( Exception ex ) {  }
  	this.editorHtml.setCaretPosition(0);
  }
  public void setPage(URL url) throws IOException {
		this.editorHtml.setPage(url);
  }

	public void update() {
		this.editorHtml.update(this.editorHtml.getGraphics());
	}
}

