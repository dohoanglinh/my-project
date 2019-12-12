/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JLabel;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import model.NguoiQuanLy;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Admin
 */
public class General {

    public General() {
    }

    public static NguoiQuanLy MANAGER = null;

    public static void logOff() {
        General.MANAGER = null;
    }

    public static java.sql.Date convertFromJAVADateToSQLDate(
            java.util.Date utilDate) {
        java.sql.Date sqlDate = null;
        if (utilDate != null) {
            sqlDate = new Date(utilDate.getTime());
        }
        return sqlDate;
    }

    public static java.util.Date now() {
        return new java.util.Date();
    }

    public static int getTime(java.util.Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return Integer.parseInt(df.format(date));
    }

    public static Image AppIcon() {
        File path = new File("logos", "ico.png");
        ImageIcon icon = new ImageIcon(path.getAbsolutePath());
        return icon.getImage();
    }

    public static String encoding(String text) {
        return Base64.encodeBase64String(text.getBytes());

    }

    public static String decoding(String text) {
        byte[] decode = Base64.decodeBase64(text);
        return new String(decode);
    }

    public static boolean saveLogo(File file) {
        File dir = new File("logos");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static ImageIcon readLogo(String fileName, JLabel lbl) {
        File path = new File("logos", fileName);
        ImageIcon icon = new ImageIcon(path.getAbsolutePath());
        if (icon != null) {
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            lbl.setText(null);
            return icon;
        } else {
            lbl.setText("Image not found");
            return null;

        }

    }

    public static String formatNumber(double number) {

        try {
            NumberFormat formatter = new DecimalFormat("###,###");
            String resp = formatter.format(number);
            resp = resp.replaceAll(",", ".");
            return resp;
        } catch (Exception e) {
            return "";
        }
    }
   
}
