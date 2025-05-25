package UsefulFunctions;

import Model.Contact;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class QrCode {

    public static JLabel generateQrCode(Contact contact) {
        int width = 300;
        int height = 300;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(contact.shareContact(), BarcodeFormat.QR_CODE, width, height);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            ImageIcon icon = new ImageIcon(qrImage);
            JLabel label = new JLabel(icon);
            return label;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
