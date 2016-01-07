package httparser.tool;

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
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */

public class OldToolLoadUrl {

	private OldToolLoadUrl() {

	}

	public static String load(String url, boolean correctHtml, boolean clean, boolean indent) {
		String line = "";
		if (url != null && url.length() > 0) {
			InputStream is = null;
			String szUrlUp = url.toUpperCase();
			if (szUrlUp.startsWith("HTTP://")) {
				try {
					// Trace
					System.out.println(url);
					// getParent().setTitle("Loading... URL:"+url);
					// URL url = new URL(url);

					/* Essai avec le paramètrage proxy */

					// Ouvre la connection
					URLConnection conn = new URL(url).openConnection();

					// /* Essai avec le paramètrage proxy*/
					// String proxyHost =
					// getParent().getTxtProxyHost();//"webproxy-rgs.telintrans.fr";
					// String proxyUser =
					// getParent().getTxtProxyUser();//"droca";
					// String proxyPwd =
					// getParent().getTxtProxyPwd();//"d230roca";
					// String proxyPort =
					// getParent().getTxtProxyPort();//"3128";
					// if (proxyHost!=null && !"".equals(proxyHost)) {
					// String authentication = proxyUser + ":" + proxyPwd;
					// String encodedPassword = "Basic " + new
					// sun.misc.BASE64Encoder().encode(authentication.getBytes());
					// System.getProperties().put("http.proxySet", "true");
					// System.getProperties().put("http.proxyHost", proxyHost);
					// System.getProperties().put("http.proxyPort", proxyPort);
					// conn.setRequestProperty("Proxy-Authorization",
					// encodedPassword);
					// }
					// Connection
					conn.connect();
					is = conn.getInputStream();
				} catch (MalformedURLException ex) {
					// TRACE
					System.out.println("ERROR: Malformed Url");
					ex.printStackTrace();
				} catch (IOException ex) {
					// TRACE
					System.out.println("ERROR: Input / Output");
					ex.printStackTrace();
				}
			} else if (szUrlUp.startsWith("FILE://")) {
				try {
					String path = szUrlUp.substring(7).replace('\\', '/');
					is = new FileInputStream(path);
				} catch (FileNotFoundException e) {
					// TRACE
					System.out.println("ERROR: File Not Found");
					e.printStackTrace();
				}
			}

			if (is != null) {
				try {
					// Initialise le buffer
					StringBuffer buf = new StringBuffer();
					// Initialise le flux
					InputStreamReader in = new InputStreamReader(is);
					BufferedReader data = new BufferedReader(in);
					// Lecture du contenu de l'URL
					while ((line = data.readLine()) != null) {
						buf.append(line);
						// getParent().setTitle("Loading... URL:" + url + " [" +
						// Integer.toString(buf.length()) + "]");
					}
					line = buf.toString();
				} catch (IOException ex) {
					// TRACE
					System.out.println("ERROR: Reading");
					ex.printStackTrace();
				}
			}

			if (line != null && !"".equals(line)) {
				if (correctHtml) {
					// getParent().setTitle("Correcting Html... URL:" +
					// getUrl());
					line = FxHtml.correctMalformed(line, indent);
				} else {
					if (clean) {
						// getParent().setTitle("Cleaning... URL:" + getUrl());
						line = org.m6c.ressource.FxHtml.cleanHtml(line);
					}
					if (indent) {
						// getParent().setTitle("Indenting... URL:" + getUrl());
						line = FxHtml.indentString(line);
					}
				}
			}
		}
		return line;
	}
}