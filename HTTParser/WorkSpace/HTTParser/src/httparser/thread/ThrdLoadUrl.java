package httparser.thread;

import httparser.frame.FrmMain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import ressource.FxHtml;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ThrdLoadUrl extends AncestorThread {

  public ThrdLoadUrl(FrmMain parent) {
    super(parent);
  }

  public synchronized void run() {
  	String szUrl = this.getUrl();
    if ( szUrl!=null && szUrl.length()>0 )
    {
    	InputStream is = null;
	    String line = "";
    	String szUrlUp = szUrl.toUpperCase();
    	if (szUrlUp.startsWith("HTTP://")) {
	      try
	      {
	      	// Trace
	  	    System.out.println(szUrl);
	  	    getParent().setTitle("Loading... URL:"+szUrl);
	  	    URL url = new URL(szUrl);
	
	        /* Essai avec le paramètrage proxy*/
	
	  	    // Ouvre la connection
	  	    URLConnection conn = url.openConnection();
	
	  	    /* Essai avec le paramètrage proxy*/
	  	    String proxyHost = getParent().getTxtProxyHost();//"webproxy-rgs.telintrans.fr";
	  	    String proxyUser = getParent().getTxtProxyUser();//"droca";
	  	    String proxyPwd = getParent().getTxtProxyPwd();//"d230roca";
	  	    String proxyPort = getParent().getTxtProxyPort();//"3128";
	  	    if (proxyHost!=null && !"".equals(proxyHost)) {
		  	    String authentication = proxyUser + ":" + proxyPwd;
		  	    String encodedPassword = "Basic " + new sun.misc.BASE64Encoder().encode(authentication.getBytes());
		  	    System.getProperties().put("http.proxySet", "true");
		  	    System.getProperties().put("http.proxyHost", proxyHost);
		  	    System.getProperties().put("http.proxyPort", proxyPort);
		  	    conn.setRequestProperty("Proxy-Authorization", encodedPassword);
	  	    }
	
	        if ( this.isShowPage() )
	        {
	          /*
	          System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
	          Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	          */
	
	          // Ce Flag sera mis à true par l'evenement editorHtml_propertyChange
	          // lorsque la page sera totalement mise à jour
	          this.getParent().setEditorHtmlUpd(false);
	          try {
	        	  this.getParent().setPage(url);
		          if ( this.isUseTimeOut() )
		          {
		            // Nombre de Seconde de Time Out defini dans le panneau Option
		            int iNbSeconde = this.getParent().getTimeOutDwnUrl();
		            //Attend N Seconde(s)
		            try { this.sleep(iNbSeconde*1000); } catch ( InterruptedException ex ) { ex.printStackTrace(); }
		          }
		          else
		          {
		            // Boucle tant que la page n'a pas été mise à jour par l'evenement editorHtml_propertyChange
		            while( !this.getParent().isEditorHtmlUpd() )
		            {
		              //Attend 1 Seconde(s)
		              try { this.sleep(1000); } catch ( InterruptedException ex ) { ex.printStackTrace(); }
		            }
		          }
		          line = this.getParent().getTxtHtml();
	          } catch ( Exception ex ) {
	        	  ex.printStackTrace();
	          }
	          finally {
	              this.getParent().setEditorHtmlUpd(true);
	          }
	        }
	        else
	        {
	          // Connection
	          conn.connect();
	          is = conn.getInputStream();
	        }
	      }
	      catch ( MalformedURLException ex )
	      {
	        //TRACE
	        System.out.println("ERROR: Malformed Url");
	        ex.printStackTrace();
	      }
	      catch ( IOException ex )
	      {
	        //TRACE
	        System.out.println("ERROR: Input / Output");
	        ex.printStackTrace();
	      }
    	}
    	else if (szUrlUp.startsWith("FILE://")) {
    		try {
    			String path = szUrlUp.substring(7).replace('\\', '/');
					is = new FileInputStream(path);
				} catch (FileNotFoundException e) {
	        //TRACE
	        System.out.println("ERROR: File Not Found");
					e.printStackTrace();
				}
    	}

    	if(is!=null) {
    		try {
	        // Initialise le buffer
	        StringBuffer buf = new StringBuffer();
	        // Initialise le flux
	        InputStreamReader in = new InputStreamReader(is);
	        BufferedReader data = new BufferedReader(in);
	        // Lecture du contenu de l'URL
	        while( (line = data.readLine()) != null ) {
	          buf.append(line);
	    	    getParent().setTitle("Loading... URL:"+szUrl + " [" + Integer.toString(buf.length()) + "]");
	        }
	        line = buf.toString();
    		}
	      catch ( IOException ex )
	      {
	        //TRACE
	        System.out.println("ERROR: Reading");
	        ex.printStackTrace();
	      }
      }
    	
    	if (line!=null && !"".equals(line)) {
	      if ( this.isCorrectHtml() ) { 
	      	getParent().setTitle("Correcting Html... URL:"+getUrl()); line = FxHtml.correctMalformed(line, this.isIndent());
	      }
	      else {
	        if ( this.isClean() ) { getParent().setTitle("Cleaning... URL:"+getUrl()); line = this.getParent().cleanString(line); }
	        if ( this.isIndent() ) { getParent().setTitle("Indenting... URL:"+getUrl()); line = FxHtml.indentString(line); }
	      }
	      try { this.getParent().setTxtSource(line); } catch ( Exception ex ) { ex.printStackTrace();  }
	      if ( this.getParent().getRunnerLink() != null ) synchronized(this.getParent().getRunnerLink()){this.getParent().getRunnerLink().notifyAll();}
	      getParent().setTitle("Curent URL:"+szUrl);
    	}
    }
    this.getParent().setRunnerLoadUrl(null);
  }
}