package httparser.box;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BoxStatusBar extends Box {

	private static final long serialVersionUID = -8665789713887644111L;

	public JLabel lblStatus, lblDesc;

  public BoxStatusBar() {
      super(BoxLayout.X_AXIS);

      Toolkit kit = Toolkit.getDefaultToolkit();
      Dimension screenSize = kit.getScreenSize();

      // Add the JLabel displaying the selected object numbers.
      lblStatus = new JLabel("Status:", SwingConstants.LEADING);
      lblStatus.setPreferredSize(new Dimension((int) (0.7 * screenSize.width),
              22));
      lblStatus.setBorder(BorderFactory.createLoweredBevelBorder());
      this.add(lblStatus, null);

      // Add the JLabel displaying the selected object size.
      // lblSize = new JLabel("Size:", SwingConstants.LEADING);
      // lblSize.setPreferredSize(new Dimension((int)(0.2*screenSize.width), 22));
      // lblSize.setBorder(BorderFactory.createLoweredBevelBorder());
      // this.add(lblSize, null);

      // Add the JLabel displaying the description.
      lblDesc = new JLabel("Description:", SwingConstants.LEADING);
      lblDesc.setPreferredSize(new Dimension((int) (0.3 * screenSize.width),
              22));
      lblDesc.setBorder(BorderFactory.createLoweredBevelBorder());
      this.add(lblDesc, null);
  }
}
