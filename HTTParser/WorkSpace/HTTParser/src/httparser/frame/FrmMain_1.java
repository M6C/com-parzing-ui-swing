package httparser.frame;

import httparser.panel.PanelJdicBrowser;
import httparser.panel.PanelJavaBrowser;
import httparser.panel.parent.IPanelBrowser;
import httparser.panel.parent.PanelBrowser;
import httparser.table.ItemTableDwnld;
import httparser.table.ItemTableLink;
import httparser.table.TableDwnld;
import httparser.table.TableLink;
import httparser.table.TableLinkExclude;
import httparser.table.TableLinkInclude;
import httparser.thread.AncestorThread;
import httparser.thread.ThrdGetLink;
import httparser.thread.ThrdParseUrl;
import httparser.thread.ThrdLoadUrl;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.HyperlinkEvent;

import ressource.FxHtml;
import ressource.FxString;
import ressource.bean.BeanTag;

import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class FrmMain extends JFrame /*implements Runnable*/ {

	//private final static String DEFAULT_URL = "http://www.yahoo.fr";
	//private final static String DEFAULT_URL = "http://www.dvdfly.com/default.asp";
	//private final static String DEFAULT_URL = "http://www.dvdfly.com/film_detail.asp?MOV_ID=5712";
	//private final static String DEFAULT_URL = "http://www.google.fr";
	//private final static String DEFAULT_URL = "http://www.gaes.org/index.html?rang=2";
	//Test pour la correction des pages malformés
	//private final static String DEFAULT_URL = "http://www.loto-euromillions.net/euromillions-tirages.php";
	//private final static String DEFAULT_URL = "http://www.codinghorror.com";
	//private final static String DEFAULT_URL = "file://C:/Documents and Settings/Administrateur/Mes documents/David/Temp/WorkSpace/HTTParser/wrk/www.gaes.org/Data/euromillions-tirages.php.html";
//EXTERNALISE	private final static String DEFAULT_URL = "Url 'Http://' || 'File://'";

//EXTERNALISE	private String url = DEFAULT_URL;
	private boolean bClean = false;//true;
  private boolean bIndent = false;//true;
  private boolean bSubLink = false;
  private boolean bCorrectHtml = false;//true;
  private boolean bUseIncludeList = false;
  private boolean bUseTimeOut = false;
  private boolean bEditorHtmlUpd = false;
  private boolean bShowPage = true;//false;
  private boolean bParse = false;
  private boolean bCloseAtEnd = false;

  private JPanel contentPane;
  private JMenuBar jMenuBar1 = new JMenuBar();
  private JMenu jMenuFile = new JMenu();
  private JMenuItem jMenuFileExit = new JMenuItem();
  private JMenu jMenuHelp = new JMenu();
  private JMenuItem jMenuHelpAbout = new JMenuItem();
  private JToolBar jToolBar = new JToolBar();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  private ImageIcon image1;
  private ImageIcon image2;
  private ImageIcon image3;
  private JLabel statusBar = new JLabel();
  private BorderLayout borderLayout1 = new BorderLayout();
//EXTERNALISE  private JPanel jPanel_View = new JPanel();
//	private PanelBrowser jPanel_View = new PanelView(this);
	private PanelBrowser jPanel_View = null;//new PanelJdicBrowser(this);
  private JPanel jPanel_Source = new JPanel();
  private JPanel jPanel_ParserAuto = new JPanel();
  private JPanel jPanel_Exclude = new JPanel();
  private JPanel jPanel_Option = new JPanel();
  private JTabbedPane jTabPane = new JTabbedPane();
//EXTERNALISE  private BorderLayout borderLayout2 = new BorderLayout();
//EXTERNALISE  private JScrollPane jScrollPane1 = new JScrollPane();
//EXTERNALISE  private JPanel jPanel_Url = new JPanel();
//EXTERNALISE  private JLabel jLabel1 = new JLabel();
//EXTERNALISE  private BorderLayout borderLayout3 = new BorderLayout();
//EXTERNALISE  private TitledBorder titledBorder1;
//  private BorderLayout borderLayout4 = new BorderLayout();
//EXTERNALISE  private JTextField txtUrl = new JTextField();
//EXTERNALISE  private JPanel jPanel1 = new JPanel();
//EXTERNALISE  private JButton btnBackUrl = new JButton();
//EXTERNALISE  private GridLayout gridLayout1 = new GridLayout();
//EXTERNALISE  private JButton btnGoUrl = new JButton();
//EXTERNALISE  private JButton btnNextUrl = new JButton();

  // Thread de Chargement d'une page
  private ThrdLoadUrl runnerLoadUrl = null;
  private ThrdParseUrl runnerLink = null;
//EXTERNALISE  private URL urlContext = null;

  private TableDwnld jTableDownload = new TableDwnld();
  private TableLink jTableLink = new TableLink();
  private TableLinkExclude jTableLinkExclude = new TableLinkExclude();
  private TableLinkInclude jTableLinkInclude = new TableLinkInclude();

  // Pile des Url Visitées
//EXTERNALISE  private Vector heap = new Vector();
//EXTERNALISE  private int indexHeap = -1;
  private BorderLayout borderLayout6 = new BorderLayout();
  private JPanel jPanel2 = new JPanel();
  private BorderLayout borderLayout7 = new BorderLayout();
  private JSplitPane jSplitPane1 = new JSplitPane();
  private JSplitPane jSplitPane2 = new JSplitPane();
  private JPanel jPanel3 = new JPanel();
  private JPanel jPanel4 = new JPanel();
  private JPanel jPanel5 = new JPanel();
  private BorderLayout borderLayout8 = new BorderLayout();
  private BorderLayout borderLayout9 = new BorderLayout();
  private JScrollPane scrollPane = new JScrollPane(jTableDownload);
  private XYLayout xYLayout1 = new XYLayout();
  private JScrollPane jScrollPane3 = new JScrollPane();
  private JLabel jLabel2 = new JLabel();
  private JScrollPane jScrollPane4 = new JScrollPane();
  private JLabel jLabel3 = new JLabel();
  private JButton btnAdd = new JButton();
  private JButton btnDel = new JButton();
  private JTextField txtPath = new JTextField();
  private JButton btnPath = new JButton();
  private JComboBox cboxType = new JComboBox();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JButton btnParse = new JButton();
  private BorderLayout borderLayout_Source = new BorderLayout();
  private JScrollPane jScrollPane5 = new JScrollPane();
  private JPanel jPanel_Source_ToolBar = new JPanel();
  private GridLayout gridLayout_Source_ToolBar = new GridLayout();
  private BorderLayout borderLayout_Source_ToolBar_Right = new BorderLayout();
  private JPanel jPanel_Source_ToolBar_Right = new JPanel();
  private BorderLayout borderLayout_Source_ToolBar_Left = new BorderLayout();
  private JPanel jPanel_Source_ToolBar_Left = new JPanel();
  private JTextArea editorSource = new JTextArea();
  private JPanel jPanel_Source_ToolBar_Right_Button = new JPanel();
  private GridLayout gridLayout_Source_ToolBar_Right_Button = new GridLayout();
  private JPanel jPanel_Source_ToolBar_Left_Button = new JPanel();
  private GridLayout gridLayout_Source_ToolBar_Left_Button = new GridLayout();
  private JButton btnViewSource = new JButton();
  private JButton btnAddBlock = new JButton();
  private JTextArea txtTextStart = new JTextArea();
  private JTextArea txtTextEnd = new JTextArea();
  private JLabel jLabel6 = new JLabel();
  private JTextField txtName = new JTextField();
  private TitledBorder titledBorder2;
  private JButton btnReadSource = new JButton();
  private JButton btnModify = new JButton();
  private JButton btnIndentSource = new JButton();
  private JButton btnCorrectSource = new JButton();
  private BorderLayout borderLayout5 = new BorderLayout();
  private JPanel jPanel7 = new JPanel();
  private BorderLayout borderLayout12 = new BorderLayout();
  private JPanel jPanel8 = new JPanel();
  private JPanel jPanel9 = new JPanel();
  private XYLayout xYLayout2 = new XYLayout();
  private BorderLayout borderLayout13 = new BorderLayout();
  private JScrollPane jScrollPane2 = new JScrollPane(jTableLink);
  private JButton btnGetLink = new JButton();
  private JScrollPane jScrollPane6 = new JScrollPane();
  private JButton btnAddLinkExclude = new JButton();
  private JButton btnDelLinkExclude = new JButton();
  private JScrollPane jScrollPane7 = new JScrollPane();
  private JButton btnAddLinkInclude = new JButton();
  private JButton btnDelLinkInclude = new JButton();
  private JTextField txtBuildLinkInclude = new JTextField();
  private JTextField txtFrom = new JTextField();
  private JTextField txtTo = new JTextField();
  private JLabel jLabel7 = new JLabel();
  private JLabel jLabel8 = new JLabel();
  private JLabel jLabe20 = new JLabel();
  private JButton btnBuildLinkInclude = new JButton();
  private XYLayout xYLayout3 = new XYLayout();
  private JLabel jLabel9 = new JLabel();
  private JTextField txtTimeOutDwnUrl = new JTextField();
  private JCheckBox chkUseIncludeList = new JCheckBox();
  private JLabel jLabel10 = new JLabel();
  private JCheckBox chkSubLink = new JCheckBox();
  private JLabel jLabel11 = new JLabel();
  private JButton btnSave = new JButton();
  private JButton btnRead = new JButton();
  private JCheckBox chkClean = new JCheckBox();
  private JCheckBox chkIndent = new JCheckBox();
  private JCheckBox chkCorrectHtml = new JCheckBox();
  private JLabel jLabel12 = new JLabel();
  private JLabel jLabel13 = new JLabel();
  private JCheckBox chkUseTimeOut = new JCheckBox();
  private JLabel jLabel14 = new JLabel();
  private JCheckBox chkShowPage = new JCheckBox();
  private JLabel jLabel15 = new JLabel();
  private JButton btnUp = new JButton();
  private JButton btnDown = new JButton();
  private JButton btnDelAllLinkInclude = new JButton();
  private JComboBox cmbDestination = new JComboBox();
  private JLabel jLabel16 = new JLabel();
  private JLabel jLabel17 = new JLabel();
  private JTextField txtProxyHost = new JTextField();
  private JLabel jLabel18 = new JLabel();
  private JTextField txtProxyUser = new JTextField();
  private JLabel jLabel19 = new JLabel();
  private JTextField txtProxyPwd = new JTextField();
  private JLabel jLabel20 = new JLabel();
  private JTextField txtProxyPort = new JTextField();
  private JLabel jLabel21 = new JLabel();
  private JLabel jLabelDataEncode = new JLabel();
  private JLabel jLabel23 = new JLabel();
  private JTextField txtDataEncode = new JTextField();
	private String txtSource = "";

  private JLabel jLabelMethode = new JLabel();
  private JComboBox cmbMethode = new JComboBox();

  private JLabel jLabelBrowser= new JLabel();
  private JComboBox cmbBrowser = new JComboBox();

//  private JEditorPane editorHtml = new JEditorPane();

  //Construct the frame
  public FrmMain() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    image1 = new ImageIcon(httparser.frame.FrmMain.class.getResource("../ressource/icone/openFile.gif"));
    image2 = new ImageIcon(httparser.frame.FrmMain.class.getResource("../ressource/icone/closeFile.gif"));
    image3 = new ImageIcon(httparser.frame.FrmMain.class.getResource("../ressource/icone/help.gif"));

    jLabelMethode.setText("Methode :");
    cmbMethode.addItem(AncestorThread.METHODE_SEQUENCE);
    cmbMethode.addItem(AncestorThread.METHODE_TEXT_REGEX);
    cmbMethode.addItem(AncestorThread.METHODE_TEXT_LITTERALE);

    jLabelBrowser.setText("Browser Web :");
    cmbBrowser.addItem(AncestorThread.BROWSER_JDIC);
    cmbBrowser.addItem(AncestorThread.BROWSER_JAVA);

    //setIconImage(Toolkit.getDefaultToolkit().createImage(FrmMain.class.getResource("[Your Icon]")));
    contentPane = (JPanel) this.getContentPane();
//  EXTERNALISE    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(800, 700));
    this.setTitle("HTTParser");
    statusBar.setText(" ");
    jMenuFile.setText("File");
    jMenuFileExit.setText("Exit");
    jMenuFileExit.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuFileExit_actionPerformed(e);
      }
    });
    jMenuHelp.setText("Help");
    jMenuHelpAbout.setText("About");
    jMenuHelpAbout.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuHelpAbout_actionPerformed(e);
      }
    });
    jButton1.setIcon(image1);
    jButton1.setToolTipText("Open File");
    jButton2.setIcon(image2);
    jButton2.setToolTipText("Close File");
    jButton3.setIcon(image3);
    jButton3.setToolTipText("Help");
//  EXTERNALISE    jPanel_View.setLayout(borderLayout2);
//  EXTERNALISE    jPanel_Url.setLayout(borderLayout3);
//  EXTERNALISE    jLabel1.setMaximumSize(new Dimension(25, 17));
//  EXTERNALISE    jLabel1.setMinimumSize(new Dimension(25, 17));
//  EXTERNALISE    jLabel1.setPreferredSize(new Dimension(25, 17));
//  EXTERNALISE    jLabel1.setText("Url : ");
//  EXTERNALISE    txtUrl.setBorder(titledBorder1);
//  EXTERNALISE    txtUrl.setText(getUrl());
//  EXTERNALISE    borderLayout2.setHgap(10);
//  EXTERNALISE    borderLayout2.setVgap(10);
//  EXTERNALISE    borderLayout3.setHgap(10);
//  EXTERNALISE    borderLayout3.setVgap(10);
//  EXTERNALISE    editorHtml.setText("");
//  EXTERNALISE    editorHtml.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
//  EXTERNALISE      public void mouseMoved(MouseEvent e) {
//  EXTERNALISE        editorHtml_mouseMoved(e);
//  EXTERNALISE      }
//  EXTERNALISE    });
//  EXTERNALISE    editorHtml.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
//  EXTERNALISE      public void propertyChange(PropertyChangeEvent e) {
//  EXTERNALISE        editorHtml_propertyChange(e);
//  EXTERNALISE      }
//  EXTERNALISE    });
//  EXTERNALISE    editorHtml.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
//  EXTERNALISE      public void hyperlinkUpdate(HyperlinkEvent e) {
//  EXTERNALISE        editorHtml_hyperlinkUpdate(e);
//  EXTERNALISE      }
//  EXTERNALISE    });
//  EXTERNALISE    jPanel1.setLayout(gridLayout1);
//  EXTERNALISE    btnBackUrl.addActionListener(new java.awt.event.ActionListener() {
//  EXTERNALISE      public void actionPerformed(ActionEvent e) {
//  EXTERNALISE        btnBackUrl_actionPerformed(e);
//  EXTERNALISE      }
//  EXTERNALISE    });
//  EXTERNALISE    btnBackUrl.setText("Back");
//  EXTERNALISE    btnGoUrl.addActionListener(new java.awt.event.ActionListener() {
//  EXTERNALISE      public void actionPerformed(ActionEvent e) {
//  EXTERNALISE        btnGoUrl_actionPerformed(e);
//  EXTERNALISE      }
//  EXTERNALISE    });
//  EXTERNALISE    btnGoUrl.setText("Go");
//  EXTERNALISE    btnNextUrl.addActionListener(new java.awt.event.ActionListener() {
//  EXTERNALISE      public void actionPerformed(ActionEvent e) {
//  EXTERNALISE        btnNextUrl_actionPerformed(e);
//  EXTERNALISE      }
//  EXTERNALISE    });
//  EXTERNALISE    btnNextUrl.setText("Next");
//  EXTERNALISE    gridLayout1.setColumns(3);
//  EXTERNALISE    gridLayout1.setHgap(5);
//  EXTERNALISE    gridLayout1.setVgap(5);
    jPanel_ParserAuto.setLayout(borderLayout6);
    jPanel2.setLayout(borderLayout7);
    jPanel3.setMinimumSize(new Dimension(250, 250));
    jPanel3.setPreferredSize(new Dimension(1000, 1000));
    jPanel3.setLayout(xYLayout1);
    jPanel4.setLayout(borderLayout8);
    jPanel5.setLayout(borderLayout9);
    jLabel2.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel2.setText("Text Start With:");
    jLabel3.setText("Text Start With:");
    jLabel3.setFont(new java.awt.Font("Dialog", 1, 12));
    btnAdd.setText("Add ->");
    btnAdd.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnAdd_actionPerformed(e);
      }
    });
    btnDel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnDel_actionPerformed(e);
      }
    });
    btnDel.setText("Del < -");
    btnPath.setText("...");
    btnPath.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnPath_actionPerformed(e);
      }
    });
    jLabel4.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel4.setText("Type:");
    jLabel5.setText("Path:");
    jLabel5.setFont(new java.awt.Font("Dialog", 1, 12));
    scrollPane.setMinimumSize(new Dimension(400, 100));
    jPanel5.setMinimumSize(new Dimension(400, 100));
    btnParse.setText("Parse");
    btnParse.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnParse_actionPerformed(e);
      }
    });
    jPanel_Source.setLayout(borderLayout_Source);

    gridLayout_Source_ToolBar.setColumns(2);
    jPanel_Source_ToolBar.setLayout(gridLayout_Source_ToolBar);

    jPanel_Source_ToolBar.add(jPanel_Source_ToolBar_Left);
    jPanel_Source_ToolBar.add(jPanel_Source_ToolBar_Right);

    jPanel_Source_ToolBar_Right.setLayout(borderLayout_Source_ToolBar_Right);
    borderLayout_Source_ToolBar_Right.setHgap(10);
    borderLayout_Source_ToolBar_Right.setVgap(10);

    jPanel_Source_ToolBar_Right.add(jPanel_Source_ToolBar_Right_Button,  BorderLayout.EAST);
    jPanel_Source_ToolBar_Right_Button.add(btnReadSource, null);
    jPanel_Source_ToolBar_Right_Button.add(btnViewSource, null);
    jPanel_Source_ToolBar_Right_Button.add(btnAddBlock, null);

    jPanel_Source_ToolBar_Right_Button.setLayout(gridLayout_Source_ToolBar_Right_Button);
    gridLayout_Source_ToolBar_Right_Button.setHgap(5);
    gridLayout_Source_ToolBar_Right_Button.setVgap(5);

    jPanel_Source_ToolBar_Left.setLayout(borderLayout_Source_ToolBar_Left);
    borderLayout_Source_ToolBar_Left.setHgap(10);
    borderLayout_Source_ToolBar_Left.setVgap(10);

    jPanel_Source_ToolBar_Left.add(jPanel_Source_ToolBar_Left_Button,  BorderLayout.EAST);
    jPanel_Source_ToolBar_Left_Button.setAlignmentX(JPanel.LEFT_ALIGNMENT);
    jPanel_Source_ToolBar_Left_Button.add(btnIndentSource, null);
    jPanel_Source_ToolBar_Left_Button.add(btnCorrectSource, null);

    jPanel_Source_ToolBar_Left_Button.setLayout(gridLayout_Source_ToolBar_Left_Button);
    gridLayout_Source_ToolBar_Left_Button.setHgap(5);
    gridLayout_Source_ToolBar_Left_Button.setVgap(5);

    btnViewSource.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnViewSource_actionPerformed(e);
      }
    });
    btnViewSource.setText("View");

    btnAddBlock.setText("Add Block");
    
    btnAddBlock.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	btnAddBlock_actionPerformed(e);
      }
    });

    jLabel6.setText("Name:");
    jLabel6.setFont(new java.awt.Font("Dialog", 1, 12));
    txtName.setBorder(BorderFactory.createLoweredBevelBorder());
    txtTextStart.setBorder(null);
    jScrollPane3.setBorder(BorderFactory.createLoweredBevelBorder());
    jScrollPane4.setBorder(BorderFactory.createLoweredBevelBorder());
    btnReadSource.setText("Read");
    btnReadSource.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnReadSource_actionPerformed(e);
      }
    });
    btnModify.setText("Modify");
    btnModify.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnModify_actionPerformed(e);
      }
    });

    btnIndentSource.setText("Indent");
    btnIndentSource.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnIndentSource_actionPerformed(e);
      }
    });

    btnCorrectSource.setText("Correct");
    btnCorrectSource.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnCorrectSource_actionPerformed(e);
      }
    });

    jPanel_Exclude.setLayout(borderLayout5);
    jPanel7.setLayout(borderLayout12);
    jPanel8.setLayout(xYLayout2);
    jPanel9.setLayout(borderLayout13);
    jPanel8.setMinimumSize(new Dimension(250, 250));
    jPanel8.setPreferredSize(new Dimension(250, 250));
    //jPanel9.setMinimumSize(new Dimension(250, 250));
    //jPanel9.setPreferredSize(new Dimension(250, 250));
    btnGetLink.setText("Get Link...");
    btnGetLink.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnGetLink_actionPerformed(e);
      }
    });
    btnAddLinkExclude.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnAddLinkExclude_actionPerformed(e);
      }
    });
    btnAddLinkExclude.setActionCommand("Add");
    btnAddLinkExclude.setText("Add");
    btnDelLinkExclude.setText("Del");
    btnDelLinkExclude.setActionCommand("Del");
    btnDelLinkExclude.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnDelLinkExclude_actionPerformed(e);
      }
    });
    btnAddLinkInclude.setText("Add");
    btnAddLinkInclude.setActionCommand("Add");
    btnAddLinkInclude.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnAddLinkInclude_actionPerformed(e);
      }
    });
    btnDelLinkInclude.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnDelLinkInclude_actionPerformed(e);
      }
    });
    btnDelLinkInclude.setActionCommand("Del");
    btnDelLinkInclude.setText("Del");
    jLabel7.setText("From");
    jLabel8.setText("To");
    jLabe20.setText("Url");
    txtBuildLinkInclude.setText("http://www.myurl.com?id=<i>");
    btnBuildLinkInclude.setText("Build");
    btnBuildLinkInclude.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnBuildLinkInclude_actionPerformed(e);
      }
    });
    contentPane.setMinimumSize(new Dimension(662, 428));
    jPanel_Option.setLayout(xYLayout3);
    jLabel9.setText("Time Out Downloading Url :");
    txtTimeOutDwnUrl.setText("8");
    chkUseIncludeList.setHorizontalTextPosition(SwingConstants.LEFT);
    chkUseIncludeList.setSelected(bUseIncludeList);
    chkUseIncludeList.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        chkUseIncludeList_actionPerformed(e);
      }
    });
    jLabel10.setText("Use Include List :");
    chkSubLink.setSelected(bSubLink);
    chkSubLink.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        chkSubLink_actionPerformed(e);
      }
    });
    jLabel11.setText("Parse Sub Link of Url :");
    btnSave.setText("Save");
    btnSave.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnSave_actionPerformed(e);
      }
    });
    btnRead.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnRead_actionPerformed(e);
      }
    });
    btnRead.setText("Read");
    chkClean.setSelected(bClean);
    chkClean.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        chkClean_actionPerformed(e);
      }
    });
    chkIndent.setSelected(bIndent);
    chkIndent.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        chkIndent_actionPerformed(e);
      }
    });
    chkCorrectHtml.setSelected(bCorrectHtml);
    chkCorrectHtml.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	chkCorrectHtml_actionPerformed(e);
      }
    });
    jLabel12.setText("Clean");
    jLabel13.setText("Indent");
    jLabel23.setText("Correct Html");
    chkUseTimeOut.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        chkUseTimeOut_actionPerformed(e);
      }
    });
    chkUseTimeOut.setSelected(bUseTimeOut);
    chkUseTimeOut.setHorizontalTextPosition(SwingConstants.LEFT);
    jLabel14.setText("Use Time Out :");
    chkShowPage.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        chkShowPage_actionPerformed(e);
      }
    });
    chkShowPage.setSelected(bShowPage);
    jLabel15.setText("Show Page on Download");
    btnUp.setText("Up");
    btnUp.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnUp_actionPerformed(e);
      }
    });
    btnDown.setText("Down");
    btnDown.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnDown_actionPerformed(e);
      }
    });
    btnDelAllLinkInclude.setText("Del All");
    btnDelAllLinkInclude.setActionCommand("Del");
    btnDelAllLinkInclude.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnDelAllLinkInclude_actionPerformed(e);
      }
    });

		jLabel17.setText("Host :");
    txtProxyHost.setText("webproxy-rgs.telintrans.fr");

    jLabel18.setText("User :");
    txtProxyUser.setText("droca");

    jLabel19.setText("Password :");
    txtProxyPwd.setText("d230roca");

    jLabel20.setText("Port :");
    txtProxyPort.setText("3128");

    jLabel21.setText("Proxy");
    jLabelDataEncode.setText("Data Encode :");
    txtDataEncode.setText("");

    jSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jLabel16.setText("Destination");
    jToolBar.add(jButton1);
    jToolBar.add(jButton2);
    jToolBar.add(jButton3);
    jMenuFile.add(jMenuFileExit);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuHelp);
    this.setJMenuBar(jMenuBar1);
    contentPane.add(jToolBar,  BorderLayout.NORTH);
    contentPane.add(statusBar, BorderLayout.SOUTH);
    contentPane.add(jTabPane,  BorderLayout.CENTER);
//EXTERNALISE    jTabPane.add(jPanel_View,   "View");
//EXTERNALISE    jPanel_View.add(jScrollPane1,  BorderLayout.CENTER);
//EXTERNALISE    jPanel_View.add(jPanel_Url,  BorderLayout.NORTH);
//EXTERNALISE    jScrollPane1.getViewport().add(editorHtml, null);
    jTabPane.add(jPanel_Source,   "Source");
    jPanel_Source.add(jScrollPane5,  BorderLayout.CENTER);
    jScrollPane5.getViewport().add(editorSource, null);
    jTabPane.add(jPanel_ParserAuto,   "Parse Auto");
    jTabPane.add(jPanel_Exclude,   "Links");
    jPanel_Option.add(jLabel11,  new XYConstraints(12, 71, 127, 16));
    jPanel_Option.add(jLabel12, new XYConstraints(12, 87, 44, -1));
    jPanel_Option.add(jLabel13, new XYConstraints(12, 104, 46, 15));
    jPanel_Option.add(jLabel23, new XYConstraints(212, 87, 76, 15));
    jPanel_Option.add(chkIndent, new XYConstraints(170, 102, 19, 20));
    jPanel_Option.add(chkClean, new XYConstraints(170, 86, 16, 16));
    jPanel_Option.add(chkCorrectHtml, new XYConstraints(280, 86, 16, 16));
    jPanel_Option.add(chkSubLink, new XYConstraints(170, 68, 18, 19));
    jPanel_Option.add(chkUseIncludeList, new XYConstraints(170, 53, 19, 19));
    jPanel_Option.add(jLabel10, new XYConstraints(12, 54, 108, 16));
    jPanel_Option.add(chkUseTimeOut,   new XYConstraints(170, 39, 19, 19));
    jPanel_Option.add(jLabel14, new XYConstraints(12, 38, 108, 16));
    jPanel_Option.add(jLabel9, new XYConstraints(11, 21, 158, 16));
    jPanel_Option.add(jLabel15, new XYConstraints(11, 120, 146, 15));
    jPanel_Option.add(chkShowPage, new XYConstraints(170, 118, 19, 20));
    jPanel_Option.add(cmbDestination, new XYConstraints(173, 139, 139, 20));
    jPanel_Option.add(jLabel16,  new XYConstraints(13, 141, -1, -1));
    jPanel_Option.add(txtTimeOutDwnUrl, new XYConstraints(174, 20, 41, 18));

    jPanel_Option.add(jLabel21, new XYConstraints(13, 164, -1, -1));
    jPanel_Option.add(jLabel17, new XYConstraints(13, 181, -1, -1));
    jPanel_Option.add(txtProxyHost, new XYConstraints(173, 181, 139, 20));
    jPanel_Option.add(jLabel18, new XYConstraints(13, 201, -1, -1));
    jPanel_Option.add(txtProxyUser, new XYConstraints(173, 201, 139, 20));
    jPanel_Option.add(jLabel19, new XYConstraints(13, 221, -1, -1));
    jPanel_Option.add(txtProxyPwd, new XYConstraints(173, 221, 139, 20));
    jPanel_Option.add(jLabel20, new XYConstraints(13, 241, -1, -1));
    jPanel_Option.add(txtProxyPort, new XYConstraints(173, 241, 139, 20));

    jPanel_Option.add(jLabelDataEncode, new XYConstraints(13, 275, -1, -1));
    jPanel_Option.add(txtDataEncode, new XYConstraints(173, 275, 139, 20));

    jPanel_Option.add(jLabelMethode, new XYConstraints(13, 300, -1, -1));
    jPanel_Option.add(cmbMethode, new XYConstraints(173, 300, 139, 20));

    jPanel_Option.add(jLabelBrowser, new XYConstraints(13, 325, -1, -1));
    jPanel_Option.add(cmbBrowser, new XYConstraints(173, 325, 139, 20));

    jPanel_Exclude.add(jPanel7, BorderLayout.CENTER);
    jPanel_ParserAuto.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jSplitPane1, BorderLayout.CENTER);
    jSplitPane1.add(jPanel3, JSplitPane.TOP);
    jPanel3.add(jLabel4, new XYConstraints(5, 300, 236, 25));
    jPanel3.add(jLabel5, new XYConstraints(4, 353, 236, 25));
    jPanel3.add(btnPath, new XYConstraints(205, 380, 35, 20));
    jPanel3.add(cboxType, new XYConstraints(5, 326, 234, 25));
    jPanel3.add(txtPath, new XYConstraints(4, 378, 197, 23));
    jPanel3.add(jScrollPane3,    new XYConstraints(5, 72, 239, 104));
    jPanel3.add(jScrollPane4,           new XYConstraints(5, 198, 239, 104));
    jPanel3.add(jLabel6,  new XYConstraints(7, 4, 236, 25));
    jPanel3.add(txtName,   new XYConstraints(6, 24, 237, 25));
    jPanel3.add(jLabel2, new XYConstraints(6, 48, 236, 25));
    jPanel3.add(jLabel3,  new XYConstraints(5, 175, 236, 25));
    jPanel3.add(btnParse,  new XYConstraints(3, 409, 76, 25));
    jPanel3.add(btnSave, new XYConstraints(4, 435, 75, 24));
    jPanel3.add(btnRead,  new XYConstraints(3, 460, 75, 24));
    jPanel3.add(btnUp,                  new XYConstraints(163, 485, 80, 21));
    jPanel3.add(btnDown,     new XYConstraints(163, 507, 80, 21));
    jPanel3.add(btnAdd,  new XYConstraints(164, 436, 79, 23));
    jPanel3.add(btnDel,  new XYConstraints(164, 461, 79, 23));
    jPanel3.add(btnModify,  new XYConstraints(164, 412, 79, 23));
    jScrollPane4.getViewport().add(txtTextEnd, null);
    jScrollPane3.getViewport().add(txtTextStart, null);
    jSplitPane1.add(jPanel4, JSplitPane.BOTTOM);
    jPanel4.add(scrollPane, BorderLayout.CENTER);
//  EXTERNALISE    jPanel_Url.add(txtUrl,  BorderLayout.CENTER);
//  EXTERNALISE    jPanel_Url.add(jPanel1,  BorderLayout.EAST);
//  EXTERNALISE    jPanel1.add(btnBackUrl, null);
//  EXTERNALISE    jPanel1.add(btnNextUrl, null);
//  EXTERNALISE    jPanel1.add(btnGoUrl, null);
//  EXTERNALISE    jPanel_Url.add(jLabel1, BorderLayout.WEST);
//    editorHtml.setContentType("text/html");
//    editorHtml.setContentType("text/html; charset=ISO-8859-1");
//    editorHtml.setEditable(false);
    cboxType.addItem("Text");
    cboxType.addItem("Image");
    cboxType.addItem("Link");
    cboxType.addItem("Block");
    jPanel_Source.add(jPanel_Source_ToolBar, BorderLayout.NORTH);
    jPanel7.add(jSplitPane2, BorderLayout.CENTER);
    jPanel8.add(jScrollPane6,   new XYConstraints(7, 48, 389, 171));
    jPanel8.add(jScrollPane7,     new XYConstraints(403, 49, 375, 171));
    jPanel8.add(jLabel7, new XYConstraints(407, 2, 38, 16));
    jPanel8.add(jLabe20, new XYConstraints(407, 22, 38, 16));
    jPanel8.add(txtFrom, new XYConstraints(437, 3, 43, 17));
    jPanel8.add(txtTo, new XYConstraints(519, 3, 43, 17));
    jPanel8.add(jLabel8, new XYConstraints(495, 3, 21, 16));
    jPanel8.add(txtBuildLinkInclude,  new XYConstraints(437, 23, 275, 20));
    jPanel8.add(btnBuildLinkInclude,  new XYConstraints(716, 22, 63, 20));
    jPanel8.add(btnAddLinkInclude, new XYConstraints(404, 222, 74, 23));
    jPanel8.add(btnDelLinkInclude, new XYConstraints(480, 222, 73, 23));
    jPanel8.add(btnDelAllLinkInclude, new XYConstraints(555, 222, 73, 23));
    jPanel8.add(btnGetLink, new XYConstraints(229, 224, 117, 20));
    jPanel8.add(btnAddLinkExclude, new XYConstraints(7, 223, 79, 23));
    jPanel8.add(btnDelLinkExclude,  new XYConstraints(88, 223, 79, 23));
    jTabPane.add(jPanel_Option,  "Option");
    jScrollPane7.getViewport().add(jTableLinkInclude, null);
    jScrollPane6.getViewport().add(jTableLinkExclude, null);
    jSplitPane2.add(jPanel9, JSplitPane.BOTTOM);
    jPanel9.add(jScrollPane2, BorderLayout.CENTER);
    jSplitPane2.add(jPanel8, JSplitPane.TOP);
    String[] columnsName = new String[]{" ", "Link"};
    btnUp.setEnabled(false);
    btnDown.setEnabled(false);
    cmbDestination.addItem(AncestorThread.DESTINATION_XML);
    cmbDestination.addItem(AncestorThread.DESTINATION_DATABASE);
    chkClean.setEnabled(!bCorrectHtml);

    // Execution du chargement de la page
    //btnGoUrl.doClick();
  }
  //File | Exit action performed
  public void jMenuFileExit_actionPerformed(ActionEvent e) {
    // Stop les Threads
    if ( this.runnerLink != null ) this.runnerLink.interrupt();
    if ( this.runnerLoadUrl != null ) this.runnerLoadUrl.interrupt();
    System.exit(0);
  }
  //Help | About action performed
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    FrmAboutBox dlg = new FrmAboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuFileExit_actionPerformed(null);
    }
  }
  void btnViewSource_actionPerformed(ActionEvent e) {
    //if ( this.editorSource.getText().length() > 0 )
    if ( getTxtSource().length() > 0 )
    {
//      String buf = this.editorSource.getText();
      	String buf = getTxtSource();

//EXTERNALISE      	try { editorHtml.setText(buf); } catch ( Exception ex ) {  }
//EXTERNALISE        this.editorHtml.setCaretPosition(0);
      	jPanel_View.setTxtHtml(buf);

      	jTabPane.setSelectedIndex(jTabPane.indexOfTab("View"));
    }
  }

  void btnAddBlock_actionPerformed(ActionEvent e) {
  	String text = this.editorSource.getSelectedText();
    if ( text!=null && text.length() > 0 ) {
    	String name = JOptionPane.showInputDialog(this, "Name :");
      if ( name!=null && name.length() > 0 ) {
	    	List listTag = FxHtml.extractHashTag(text);
	    	// Vide le contenu de chaque Element
	    	Iterator iter = listTag.iterator();
	    	while (iter.hasNext()) {
	    		BeanTag element = (BeanTag) iter.next();
					element.setContent("");
				}

	    	text = FxHtml.toHtmlAll(null, listTag.iterator(), false);
	      ItemTableDwnld item = new ItemTableDwnld();
	      item.setTextName(name);
	      item.setTextStart(text);
	      item.setTextEnd("");
	      item.setTextPath("");
	      item.setTextType("Block");
	
	      jTableDownload.add(item);
      }
    }
  	jTableDownload.repaint();
  }
//EXTERNALISE
//EXTERNALISE  void btnGoUrl_actionPerformed(ActionEvent e) {
//EXTERNALISE      runLoadUrlProcess();

//EXTERNALISE      if ( ( indexHeap>=0) && (indexHeap<(heap.size()-1)) )
//EXTERNALISE        heap.setSize(indexHeap+1);
//EXTERNALISE      if ( (heap.size()==0) || (!((String)heap.lastElement()).equals(this.txtUrl.getText())) )
//EXTERNALISE      {
//EXTERNALISE        heap.add(this.txtUrl.getText());
//EXTERNALISE        indexHeap++;
//EXTERNALISE      }

//EXTERNALISE      btnNextUrl.setEnabled((indexHeap+1)<heap.size());
//EXTERNALISE      btnBackUrl.setEnabled(indexHeap>0);
//EXTERNALISE  }
//EXTERNALISE  void btnNextUrl_actionPerformed(ActionEvent e) {
//EXTERNALISE    if ( heap.size() > (indexHeap+1) )
//EXTERNALISE    {
//EXTERNALISE      this.txtUrl.setText((String)heap.elementAt(++indexHeap));
//EXTERNALISE      runLoadUrlProcess();
//EXTERNALISE      btnNextUrl.setEnabled((indexHeap+1)<heap.size());
//EXTERNALISE      btnBackUrl.setEnabled(indexHeap>0);
//EXTERNALISE    }
//EXTERNALISE  }

//EXTERNALISE  void btnBackUrl_actionPerformed(ActionEvent e) {
//EXTERNALISE    if ( ( heap.size() > 0 ) && ( indexHeap>= 0 ) )
//EXTERNALISE    {
//EXTERNALISE      this.txtUrl.setText((String)heap.elementAt(--indexHeap));
//EXTERNALISE      runLoadUrlProcess();
//EXTERNALISE      btnNextUrl.setEnabled((indexHeap+1)<heap.size());
//EXTERNALISE      btnBackUrl.setEnabled(indexHeap>0);
//EXTERNALISE    }
//EXTERNALISE  }

  void btnAdd_actionPerformed(ActionEvent e) {
    String szType = (String)this.cboxType.getSelectedItem();

    ItemTableDwnld item = new ItemTableDwnld();
    item.setTextName(this.txtName.getText());
    item.setTextStart(this.txtTextStart.getText());
    item.setTextEnd(this.txtTextEnd.getText());
    item.setTextPath(this.txtPath.getText());
    item.setTextType(szType);

    if ( szType.equalsIgnoreCase("Text") )
    {
      if ( (item.getTextStart().length()>0) && (item.getTextEnd().length()>0) &&
           (item.getTextName().length()>0) && (item.getTextPath().length()>0) )
        jTableDownload.add(item);
    }
    else
    {
      if ( (item.getTextName().length()>0) && (item.getTextPath().length()>0) )
        jTableDownload.add(item);
    }
  	jTableDownload.repaint();
  }

  void btnDel_actionPerformed(ActionEvent e) {
    if ( JOptionPane.showConfirmDialog(this, "Confirmation ?") == JOptionPane.YES_OPTION )
    {
      int[] rows = this.jTableDownload.getSelectedRows();
      for (int i = 0; i < rows.length; i++) {
        jTableDownload.del(rows[i]);
      }
    	jTableDownload.repaint();
    }
  }

  void btnModify_actionPerformed(ActionEvent e) {
    if ( (this.txtTextStart.getText().length()>0) && //(this.txtTextEnd.getText().length()>0) &&
         (this.txtPath.getText().length()>0) )
    {
      int index = jTableDownload.getSelectedRow();
      String szType = (String)this.cboxType.getSelectedItem();
      ItemTableDwnld item = new ItemTableDwnld();
      item.setTextName(this.txtName.getText());
      item.setTextStart(this.txtTextStart.getText());
      item.setTextEnd(this.txtTextEnd.getText());
      item.setTextPath(this.txtPath.getText());
      item.setTextType((String)this.cboxType.getSelectedItem());
      jTableDownload.modify(index, item);
    	jTableDownload.repaint();
    }
  }

	void btnIndentSource_actionPerformed(ActionEvent e) {
    String line = getTxtSource();
    line = FxHtml.indentString(line);
    setTxtSource(line);
    this.editorSource.setCaretPosition(0);
	}

	void btnCorrectSource_actionPerformed(ActionEvent e) {
    String line = getTxtSource();
    line = FxHtml.correctMalformed(line, true);
    setTxtSource(line);
    this.editorSource.setCaretPosition(0);
	}

	void btnPath_actionPerformed(ActionEvent e) {
    JFileChooser fc = new JFileChooser();
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fc.setSelectedFile(new File(txtPath.getText()));
    int returnVal = fc.showSaveDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION)
    {
      File file = fc.getSelectedFile();
      String strFileName = file.getAbsolutePath();
      txtPath.setText(strFileName);
    }
  }
  void btnParse_actionPerformed(ActionEvent e) {
    this.runParseUrlProcess();
  }
  void btnGetLink_actionPerformed(ActionEvent e) {
    this.runGetLinkProcess();
  }

  protected void runParseUrlProcess()
  {
    if ( this.runnerLink != null )
      this.runnerLink.interrupt();

    this.runnerLink = new ThrdParseUrl(this);
    this.runnerLink.start();
  }

  protected void runGetLinkProcess()
  {
    if ( this.runnerLink != null )
      this.runnerLink.interrupt();

    this.runnerLink = new ThrdGetLink(this);
    this.runnerLink.start();
  }

//EXTERNALISE  protected void runLoadUrlProcess()
//EXTERNALISE  {
//EXTERNALISE    this.setURLContext(this.getTxtUrl());
//EXTERNALISE
//EXTERNALISE    if ( this.runnerLoadUrl != null )
//EXTERNALISE      this.runnerLoadUrl.interrupt();
//EXTERNALISE
//EXTERNALISE    this.runnerLoadUrl = new ThrdLoadUrl(this);
//EXTERNALISE    this.runnerLoadUrl.start();
//EXTERNALISE  }

  /**
   * Retourn true si le lien se trouve dans la liste du
   * tableau des liens à exclure ( jTableLinkExclude )
   * @param szUrl
   * @return
   */
  public boolean isExclude(String szUrl)
  {
    boolean ret = false;
    Vector list = jTableLinkExclude.getMessageList();
    for (int i = 0; (i<list.size()) && !ret; i++) {
      ItemTableLink item = (ItemTableLink)list.elementAt(i);
      if ( item != null )
        ret = item.getUrl().toUpperCase().startsWith(szUrl.toUpperCase());
    }

    return ret;
  }
  public void showLink(Vector data)
  {
    // Efface le tableau de sortie
    if ( data.size()>0 ) jTableLink.getDataModel().clearAll();

    for ( int i=0 ; i<data.size() ; i++ )
    {
      String[] str = {" ", (String)data.elementAt(i)};
      ItemTableLink item = new ItemTableLink();
      item.setUrl((String)data.elementAt(i));
      jTableLink.add(item);
    }
    jTableLink.repaint();
  }

  public void ShowViewPanel()
  {
    jTabPane.setSelectedIndex(jTabPane.indexOfTab("View"));

//EXTERNALISE    editorHtml.update(editorHtml.getGraphics());
    jPanel_View.update();
  }

//EXTERNALISE  void editorHtml_hyperlinkUpdate(HyperlinkEvent e) {
//EXTERNALISE    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
//EXTERNALISE    {
//EXTERNALISE  	  try{ 
//EXTERNALISE  		urlContext = e.getURL(); 
//EXTERNALISE
//EXTERNALISE  		// Ouvre la connection
//EXTERNALISE  		URLConnection conn = urlContext.openConnection();
//EXTERNALISE	    /* Essai avec le paramètrage proxy*/
//EXTERNALISE  		String proxyHost = getTxtProxyHost();//"webproxy-rgs.telintrans.fr";
//EXTERNALISE  		String proxyUser = getTxtProxyUser();//"droca";
//EXTERNALISE  		String proxyPwd = getTxtProxyPwd();//"d230roca";
//EXTERNALISE  		String proxyPort = getTxtProxyPort();//"3128";
//EXTERNALISE	    if (proxyHost!=null && !"".equals(proxyHost)) {
//EXTERNALISE	  		String authentication = proxyUser + ":" + proxyPwd;
//EXTERNALISE	  		String encodedPassword = "Basic " + new sun.misc.BASE64Encoder().encode(authentication.getBytes());
//EXTERNALISE	  		System.getProperties().put("http.proxySet", "true");
//EXTERNALISE	  		System.getProperties().put("http.proxyHost", proxyHost);
//EXTERNALISE	  		System.getProperties().put("http.proxyPort", proxyPort);
//EXTERNALISE	  		conn.setRequestProperty("Proxy-Authorization", encodedPassword);
//EXTERNALISE	    }
//EXTERNALISE
//EXTERNALISE        editorHtml.setPage(urlContext); 
//EXTERNALISE  	  } catch (MalformedURLException murle) { 
//EXTERNALISE          editorHtml.setContentType("text/html"); 
//EXTERNALISE          editorHtml.setText("<html><body>MalformedURLExcepti on: " 
//EXTERNALISE  		  + murle.getMessage() 
//EXTERNALISE  		  + "<br>Malformed URL: Can not load url: " 
//EXTERNALISE  		  + urlContext.toExternalForm() 
//EXTERNALISE  		  + "</body></html>"); 
//EXTERNALISE  		  }catch(IOException ioe){ 
//EXTERNALISE  			editorHtml.setContentType("text/html"); 
//EXTERNALISE  			editorHtml.setText("<html><body>IOException: " 
//EXTERNALISE  		  + ioe.getMessage() + "<br>Could not load url: " 
//EXTERNALISE  		  + urlContext.toExternalForm() + "</body></html>"); 
//EXTERNALISE  	   } 
//EXTERNALISE
//EXTERNALISE  	   this.txtUrl.setText(e.getURL().toString());
//EXTERNALISE  	   btnGoUrl.doClick();
//EXTERNALISE    }
//EXTERNALISE  }

  public String cleanString(String str)
  {
    Vector vctList = new Vector();
    vctList.add("font");
    vctList.add("/font");
    vctList.add("b");
    vctList.add("/b");
    str = FxHtml.deleteTag(str, vctList);
    str = FxString.replaceString(str, "       ", "");
    str = FxString.replaceString(str, "      ", "");
    str = FxString.replaceString(str, "     ", "");
    str = FxString.replaceString(str, "    ", "");
    str = FxString.replaceString(str, "   ", "");
    str = FxString.replaceString(str, "  ", "");
    str = FxString.replaceString(str, "> ", ">");
    str = FxString.replaceString(str, " <", "<");
    str = FxString.replaceString(str, "\n", "");
    str = FxString.replaceString(str, "\r", "");
    str = FxString.replaceString(str, "\t", "");
    str = FxString.replaceString(str, "&nbsp;", " ");
    str = FxString.replaceString(str, "&#160;", " ");

    System.gc();

    return str;
  }
  public String getTxtPath() {
    return txtPath.getText();
  }
  public String getTxtTextEnd() {
    return txtTextEnd.getText();
  }
  public String getTxtTextStart() {
    return txtTextStart.getText();
  }
  public void setTxtPath(String txtPath) {
    this.txtPath.setText(txtPath);
  }
  public void setTxtTextEnd(String txtTextEnd) {
    this.txtTextEnd.setText(txtTextEnd);
  }
  public void setTxtTextStart(String txtTextStart) {
    this.txtTextStart.setText(txtTextStart);
  }
  public String getCboxType() {
    return (String)cboxType.getSelectedItem();
  }
  public void setCboxType(String cboxType) {
    this.cboxType.setSelectedItem(cboxType);
  }
  public String getTxtName() {
    return txtName.getText();
  }
  public void setTxtName(String txtName) {
    this.txtName.setText(txtName);
  }
//EXTERNALISE  public String getTxtUrl() {
//EXTERNALISE    return txtUrl.getText();
//EXTERNALISE  }
//EXTERNALISE  public void setTxtUrl(String txtUrl) {
//EXTERNALISE    this.txtUrl.setText(txtUrl);
//EXTERNALISE  }
  public String getTxtUrl() {
    return jPanel_View.getTxtUrl();
  }
  public void setTxtUrl(String txtUrl) {
    this.jPanel_View.setTxtUrl(txtUrl);
  }
  public String getTxtHtml() {
    return jPanel_View.getTxtHtml();
  }
  public String getHtml() {
    return ( isShowPage() ) ? getTxtHtml() : getTxtSource();
  }
  public void setTxtHtml(String txtHtml) {
    this.jPanel_View.setTxtHtml(txtHtml);
  }
  public void setPage(URL url) throws IOException {
		this.jPanel_View.setPage(url);
  }
  public String getTxtSource() {

  	String ret;
  	try {
  		ret = editorSource.getText();
  	}
  	catch (Exception ex) {
  		ret = this.txtSource;
  	}
  	return ret;

//		return this.txtSource;
  }
  public void setTxtSource(String txtSource) {
		this.txtSource = txtSource;
		try {
			this.editorSource.setText(txtSource);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
  }
  public Vector getUrlIncludeList(){
    return this.jTableLinkInclude.getMessageList();
  }
  public Vector getUrlExcludeList(){
    return this.jTableLinkExclude.getMessageList();
  }
  public Vector getDownloadList(){
    return this.jTableDownload.getMessageList();
  }
  public String getTxtProxyHost() {
  	return this.txtProxyHost.getText();
  }
  public String getTxtProxyUser() {
  	return this.txtProxyUser.getText();
  }
  public String getTxtProxyPwd() {
  	return this.txtProxyPwd.getText();
  }
  public String getTxtProxyPort() {
  	return this.txtProxyPort.getText();
  }
  public String getTxtDataEncode() {
  	return this.txtDataEncode.getText();
  }
//EXTERNALISE  public URL getURLContext(){
//EXTERNALISE    return this.urlContext;
//EXTERNALISE  }
  public URL getURLContext(){
    return jPanel_View.getURLContext();
  }
  public String getTxtMethode() {
  	return this.cmbMethode.getSelectedItem().toString();
  }
//EXTERNALISE  public void setURLContext(String url) {
//EXTERNALISE  	if (!url.equals(DEFAULT_URL))
//EXTERNALISE  		try { this.setURLContext(new URL(url)); } catch ( MalformedURLException ex ) { ex.printStackTrace(); };
//EXTERNALISE  }
//EXTERNALISE  public void setURLContext(URL url){
//EXTERNALISE    this.urlContext = url;
//EXTERNALISE  }
//EXTERNALISE  public void doGoUrl(){
//EXTERNALISE    btnGoUrl.doClick();
//EXTERNALISE  }
  public void setURLContext(String url) {
  	this.jPanel_View.setURLContext(url);
  }
  public void doGoUrl(){
  	this.jPanel_View.doGoUrl();
  }
  public void doParse(){
    btnParse.doClick();
  }
  public boolean isClean() {
    return bClean;
  }
  public boolean isCorrectHtml() {
    return bCorrectHtml;
  }
  public boolean isIndent() {
    return bIndent;
  }
  public boolean isSubLink() {
    return bSubLink;
  }
  public boolean isUseIncludeList() {
    return bUseIncludeList;
  }
  public boolean isEditorHtmlUpd() {
    return bEditorHtmlUpd;
  }
  public boolean isUseTimeOut() {
    return bUseTimeOut;
  }
  public boolean isShowPage() {
    return bShowPage;
  }
  public boolean isParse() {
    return bParse;
  }
  public boolean isCloseAtEnd() {
    return bCloseAtEnd;
  }
//EXTERNALISE	public String getUrl() {
//EXTERNALISE		return url;
//EXTERNALISE	}
  public void setClean(boolean bClean) {
    this.bClean = bClean;
    chkClean.setSelected(bClean);
  }
  public void setCorrectHtml(boolean bCorrectHtml) {
    this.bCorrectHtml = bCorrectHtml;
    chkCorrectHtml.setSelected(bCorrectHtml);
  }
  public void setIndent(boolean bIndent) {
    this.bIndent = bIndent;
    chkIndent.setSelected(bIndent);
  }
  public void setSubLink(boolean bSubLink) {
    this.bSubLink = bSubLink;
    chkSubLink.setSelected(bSubLink);
  }
  public void setUseIncludeList(boolean bUseIncludeList) {
    this.bUseIncludeList = bUseIncludeList;
    chkUseIncludeList.setSelected(bUseIncludeList);
  }
  public void setUseTimeOut(boolean bUseTimeOut) {
    this.bUseTimeOut = bUseTimeOut;
    chkUseTimeOut.setSelected(bUseTimeOut);
  }
  public void setShowPage(boolean bShowPage) {
    this.bShowPage = bShowPage;
    chkShowPage.setSelected(bShowPage);
  }
	public void setCloseAtEnd(boolean closeAtEnd) {
		bCloseAtEnd = closeAtEnd;
	}
	public void setParse(boolean parse) {
		bParse = parse;
	}
	
	public void setBrowserWeb(String browser) {
		cmbBrowser.setSelectedItem(browser);
	}
	public String getBrowserWeb() {
		return cmbBrowser.getSelectedItem().toString();
	}
//EXTERNALISE	public void setUrl(String url) {
//EXTERNALISE		this.url = url;
//EXTERNALISE    txtUrl.setText(url);
//EXTERNALISE	}
	public void setUrl(String url) {
		if (jPanel_View!=null)
			jPanel_View.setUrl(url);
	}
  public void setEditorHtmlUpd(boolean bEditorHtmlUpd) {
    this.bEditorHtmlUpd = bEditorHtmlUpd;
  }
  public ThrdParseUrl getRunnerLink() {
    return this.runnerLink;
  }
  public ThrdLoadUrl getRunnerLoadUrl() {
    return this.runnerLoadUrl;
  }
  public void setRunnerLink(ThrdParseUrl runnerLink) {
    this.runnerLink = runnerLink;
  }
  public void setRunnerLoadUrl(ThrdLoadUrl runnerLoadUrl) {
    this.runnerLoadUrl = runnerLoadUrl;
  }
  public int getTimeOutDwnUrl() {
    return Integer.parseInt(txtTimeOutDwnUrl.getText());
  }
  public void setTimeOutDwnUrl(int iTimeOutDwnUrl ) {
    txtTimeOutDwnUrl.setText(Integer.toString(iTimeOutDwnUrl));
  }
  public String getDestination() {
    return (cmbDestination.getSelectedItem()==null) ? "" : cmbDestination.getSelectedItem().toString();
  }
  void btnReadSource_actionPerformed(ActionEvent e) {
      String line = getHtml();
      if ( bClean ) line = cleanString(line);
      if ( bIndent ) line = FxHtml.indentString(line);
      //this.editorSource.setText(line);
      setTxtSource(line);
      this.editorSource.setCaretPosition(0);
  }

  void chkClean_actionPerformed(ActionEvent e) {
    chkCorrectHtml.setEnabled(bClean);
    bClean = !bClean;
  }

  void chkIndent_actionPerformed(ActionEvent e) {
    bIndent = !bIndent;
  }

  void chkCorrectHtml_actionPerformed(ActionEvent e) {
    chkClean.setEnabled(bCorrectHtml);
    bCorrectHtml = !bCorrectHtml;
  }

  void btnAddLinkExclude_actionPerformed(ActionEvent e) {
    int[] rows = this.jTableLink.getSelectedRows();
    for (int i = 0; i < rows.length; i++) {
      ItemTableLink item = (ItemTableLink)this.jTableLink.getMessageList().elementAt(rows[i]);
      jTableLinkExclude.add(item);
    }
    jTableLinkExclude.Show();
    jTableLinkExclude.repaint();
  }
  void btnDelLinkExclude_actionPerformed(ActionEvent e) {
    int[] rows = this.jTableLinkExclude.getSelectedRows();
    for (int i=(rows.length-1); i >= 0; i--) {
      jTableLinkExclude.del(rows[i]);
    }
    jTableLinkExclude.repaint();
  }

  void chkSubLink_actionPerformed(ActionEvent e) {
    bSubLink = !bSubLink;
  }
  void chkUseTimeOut_actionPerformed(ActionEvent e) {
    bUseTimeOut = !bUseTimeOut;
  }
  void btnAddLinkInclude_actionPerformed(ActionEvent e) {
    int[] rows = this.jTableLink.getSelectedRows();
    for (int i = 0; i < rows.length; i++) {
      ItemTableLink item = (ItemTableLink)this.jTableLink.getMessageList().elementAt(rows[i]);
      jTableLinkInclude.add(item);
    }
    jTableLinkInclude.repaint();
    jTableLinkInclude.Show();
  }
  void btnDelLinkInclude_actionPerformed(ActionEvent e) {
    int[] rows = this.jTableLinkInclude.getSelectedRows();
    for (int i=(rows.length-1); i >= 0; i--) {
      jTableLinkInclude.del(rows[i]);
    }
    jTableLinkInclude.repaint();
  }

  void btnBuildLinkInclude_actionPerformed(ActionEvent e) {
    String commande = txtBuildLinkInclude.getText();
    String szBalise = "<i>";
    if ( commande.length() > 0 )
    {
      String szLink = null;
      String szFrom = txtFrom.getText();
      String szTo = txtTo.getText();
      int iFrom = (szFrom==null || szFrom.equals("") ? 0 : Integer.parseInt(szFrom));
      int iTo = (szTo==null || szTo.equals("") ? 0 : Integer.parseInt(szTo));
      for (int i=iFrom ; i<=iTo ; i++)
      {
        int iFind = commande.indexOf(szBalise);
        if ( iFind >= 0 )
        {
          StringBuffer  sbLink = new StringBuffer(commande.substring(0, iFind));
          sbLink.append(Integer.toString(i));
          sbLink.append(commande.substring(iFind+szBalise.length()));
          szLink = sbLink.toString();
        } else {
          szLink = commande;
        }
        ItemTableLink item = new ItemTableLink();
        item.setUrl(szLink);
        jTableLinkInclude.add(item);
      }
      jTableLinkInclude.repaint();
    }
  }

  void chkUseIncludeList_actionPerformed(ActionEvent e) {
    bUseIncludeList = !bUseIncludeList;
  }

  void btnSave_actionPerformed(ActionEvent e) {
    jTableDownload.saveAskDestination();
  }
  void btnRead_actionPerformed(ActionEvent e) {
    jTableDownload.readAskSource();
  }

  void editorHtml_propertyChange(PropertyChangeEvent e) {
    if ( e.getPropertyName().equals("page") )
      // La page a été chargé
      bEditorHtmlUpd = true;
  }
  void chkShowPage_actionPerformed(ActionEvent e) {
    bShowPage = !bShowPage;
  }

  void btnUp_actionPerformed(ActionEvent e) {
    int[] rows = this.jTableDownload.getSelectedRows();
    if (rows.length == 1)
    {
      jTableDownload.moveRow(rows[0], rows[0]-1);
      jTableDownload.setEditingRow(rows[0]-1);
      //jTableDownload.moveRow(rows[0]-1, rows[0]-2);
      //jTableDownload.setEditingRow(rows[0]-2);
    	jTableDownload.repaint();
    }
  }

  void btnDown_actionPerformed(ActionEvent e) {
    int[] rows = this.jTableDownload.getSelectedRows();
    if (rows.length == 1)
    {
      jTableDownload.moveRow(rows[0], rows[0]+1);
      jTableDownload.setEditingRow(rows[0]+1);
    	jTableDownload.repaint();
    }
  }
  public void enableBtnUpDown( int row )
  {
    btnUp.setEnabled( (row>0) );
    btnDown.setEnabled( (row<(jTableDownload.getMessageList().size()-1)) );
  }
  void btnDelAllLinkInclude_actionPerformed(ActionEvent e) {
    if ( JOptionPane.showConfirmDialog(this, "Confirmation ?") == JOptionPane.YES_OPTION )
    {
      while ( jTableLinkInclude.getRowCount() > 0 )
      {
        jTableLinkInclude.del( jTableLinkInclude.getRowCount()-1 );
      }
      jTableLinkInclude.repaint();
    }
  }
	public void setVisible(boolean b) {
		if (b) {
	    if (cmbBrowser.getSelectedItem().equals(AncestorThread.BROWSER_JAVA))
	    	jPanel_View = new PanelJavaBrowser(this);
	    else
	    	jPanel_View = new PanelJdicBrowser(this);
	    jTabPane.add(jPanel_View,   "View", 0);
		}
		super.setVisible(b);
	}

//EXTERNALISE	  void editorHtml_mouseMoved(MouseEvent e) {
//EXTERNALISE	    Point pointMouse = e.getPoint();
//EXTERNALISE	    Component cpntHtml = editorHtml.getComponentAt(pointMouse);
//EXTERNALISE	    if ( cpntHtml!=null )
//EXTERNALISE	    {
//EXTERNALISE	      while( cpntHtml.contains(pointMouse) )
//EXTERNALISE	      {
//EXTERNALISE	        Component cpnt = cpntHtml.getComponentAt(pointMouse);
//EXTERNALISE	        if ( (cpnt==null)||(cpnt.equals(cpnt)) )
//EXTERNALISE	          break;
//EXTERNALISE	        else
//EXTERNALISE	          cpntHtml = cpnt;
//EXTERNALISE	      }
//EXTERNALISE	/*
//EXTERNALISE	      StringBuffer stbTitle = new StringBuffer();
//EXTERNALISE	      stbTitle.append(" Height:").append(cpntHtml.getHeight());
//EXTERNALISE	      stbTitle.append(" Width:").append(cpntHtml.getWidth());
//EXTERNALISE	      stbTitle.append(" X:").append(cpntHtml.getX());
//EXTERNALISE	      stbTitle.append(" Y:").append(cpntHtml.getY());
//EXTERNALISE	      this.setTitle(stbTitle.toString());
//EXTERNALISE	*/
//EXTERNALISE	    }
//EXTERNALISE	/*
//EXTERNALISE	    int iCount = editorHtml.getComponentCount();
//EXTERNALISE	    for (int i=0 ; i<iCount ; i++)
//EXTERNALISE	    {
//EXTERNALISE	      cpntHtml = editorHtml.getComponent(i);
//EXTERNALISE	      if ( cpntHtml!=null )
//EXTERNALISE	      {
//EXTERNALISE	        StringBuffer stbTitle = new StringBuffer("i:").append(i);
//EXTERNALISE	        stbTitle.append(" Height:").append(cpntHtml.getHeight());
//EXTERNALISE	        stbTitle.append(" Width:").append(cpntHtml.getWidth());
//EXTERNALISE	        stbTitle.append(" X:").append(cpntHtml.getX());
//EXTERNALISE	        stbTitle.append(" Y:").append(cpntHtml.getY());
//EXTERNALISE	        System.out.println(stbTitle.toString());
//EXTERNALISE	      }
//EXTERNALISE	    }
//EXTERNALISE	*/
//EXTERNALISE	  }
}