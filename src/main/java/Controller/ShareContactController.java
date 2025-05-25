package Controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.ShareContactView;

public class ShareContactController {

    private ShareContactView shareContactView;

    public ShareContactController(ShareContactView shareContactView) {
        this.shareContactView = shareContactView;

        shareContactView.getShareButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(shareContactView.getContact().shareContact());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                JOptionPane.showMessageDialog(null, "Contact copied to clipboard!", "Contact Saved", JOptionPane.INFORMATION_MESSAGE
                );

            }

        });
    }
}
