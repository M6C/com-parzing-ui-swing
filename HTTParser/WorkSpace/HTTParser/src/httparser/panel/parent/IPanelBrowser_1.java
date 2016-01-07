package httparser.panel.parent;

import java.io.IOException;
import java.net.URL;

public interface IPanelBrowser {
	
	public abstract void setURLContext(String url);

	public abstract void setURLContext(URL url);

	public abstract URL getURLContext();

	public abstract String getTxtUrl();

	public abstract void setTxtUrl(String txtUrl);

	public abstract void doGoUrl();

	public abstract void setUrl(String url);

	public abstract String getUrl();

  public abstract String getTxtHtml();

  public abstract void setTxtHtml(String txtHtml);

  public abstract void setPage(URL url) throws IOException;

	public abstract void update();
}