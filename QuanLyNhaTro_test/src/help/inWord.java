/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import org.apache.poi.xwpf.usermodel.*;
import java.util.Date;

/**
 *
 * @author quocc
 */
public class inWord {

    Date today = new Date(System.currentTimeMillis());
    SimpleDateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy");
    String tod = timeFormat.format(today.getTime());

    public void CreateContract(String tenkh, Date NgayTao, Date NgayBatDau, Date NgayKT, String TienCoc, String Tratheothang, String traTruoc, String TongTien, String sohd, String dieuk, String ngsinh, String socmnd, String diachi, String sodt) throws Exception {
        //tao file word
        XWPFDocument document = new XWPFDocument();
        //tao doan van ban
        //slogan
        XWPFParagraph slogan = document.createParagraph();
        XWPFRun run = slogan.createRun();
        slogan.setAlignment(ParagraphAlignment.CENTER);
        String title = "CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM";
        run.setFontSize(16);
        //run.setFontFamily("Times New Roman");
        run.setBold(true);
        run.setText(title);
        run.addBreak();
        String title1 = "Độc lập - Tự Do - Hạnh phúc";
        run.setText(title1);
        run.addBreak();
        String title4 = "------------";
        run.setText(title4);
        run.addBreak();
        String title2 = "HỢP ĐỒNG CHO THUÊ TRỌ";
        run.setText(title2);
        run.addBreak();

        XWPFRun run2 = slogan.createRun();
        String title3 = "Số Hợp Đồng: " + sohd;
        run2.setFontSize(14);
        run2.setText(title3);
        //Paragraph 1
        XWPFParagraph para1 = document.createParagraph();
        para1.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun runPara1 = para1.createRun();
        runPara1.setFontSize(14);
        String text1 = "Căn cứ theo Luật Nhà Đất 2019";
        runPara1.setText(text1);
        runPara1.addBreak();
        runPara1.setBold(true);;
        runPara1.setUnderline(UnderlinePatterns.THICK);

        XWPFRun runPara2 = para1.createRun();
        runPara2.setFontSize(14);
        String text2 = "-  Các quy định pháp luật hiện hành.";
        String text3 = "-  Các quyết định phê duyệt dự án.";
        runPara2.setText(text2);
        runPara2.addBreak();
        runPara2.setText(text3);
        runPara2.addBreak();
        runPara2.addBreak();
        String text4 = "Hôm nay, " + tod + ", tại Đà Nẵng, hai bên chúng tôi gồm:";
        runPara2.setText(text4);
        //Para3 Bên bán
        XWPFParagraph BenBan = document.createParagraph();
        XWPFRun runBenBan = BenBan.createRun();
        runBenBan.setFontSize(16);
        runBenBan.setBold(true);
        String text5 = "1. BÊN CHO THUÊ:";
        String text6 = "    TRUNG TÂM NHÀ ĐẤT ABD GROUPS";
        runBenBan.setText(text5);
        runBenBan.addBreak();
        runBenBan.setText(text6);
        runBenBan.addBreak();

        XWPFRun runBenBan1 = BenBan.createRun();
        runBenBan1.setFontSize(14);
        String truSo = "     Trụ sở chính:  81 Nguyễn Văn Thoại, Sơn Trà, Đá Nẵng";
        String sdt = "     Điện thoại: 0443 843 848";
        String fax = "Fax: 0443 822 411";
        String taikhoan = "     Tài khoản số: 0308213567213";
        String masothue = "     Mã số thuế: MSTBCL0701";
        String nguoidaidien = "     Người đại diện: Trần Văn A";
        String chucvu = "Chức vụ: Trưởng phòng nhân sự";
        String text7 = "(Theo Giấy ủy quyền ngày.....tháng.....năm..... của...............................)";
        runBenBan1.setText(truSo);
        runBenBan1.addBreak();
        runBenBan1.setText(sdt);
        runBenBan1.addTab();
        runBenBan1.addTab();
        runBenBan1.setText(fax);
        runBenBan1.addBreak();
        runBenBan1.setText(taikhoan);
        runBenBan1.addBreak();
        runBenBan1.setText(masothue);
        runBenBan1.addBreak();
        runBenBan1.setText(nguoidaidien);
        runBenBan1.addTab();
        runBenBan1.addTab();
        runBenBan1.setText(chucvu);
        runBenBan1.addBreak();
        runBenBan1.setText(text7);
        runBenBan1.addBreak();
        //Para4 Bên mua
        XWPFParagraph BenMua = document.createParagraph();
        XWPFRun runBenMua = BenMua.createRun();
        runBenMua.setFontSize(16);
        runBenMua.setBold(true);
        String text8 = "2. BÊN THUÊ NHÀ TRỌ:";
        String text9 = "    Ông/Bà: "+tenkh;
        runBenMua.setText(text8);
        runBenMua.addBreak();
        runBenMua.setText(text9);
        runBenMua.addBreak();

        XWPFRun runBenMua1 = BenMua.createRun();
        runBenMua1.setFontSize(14);
        String ngaysinh = "     Tuổi: "+ngsinh;
        String socmt = "     Số CMND: "+socmnd ;
        String diachi1 = "     Địa chỉ: "+diachi;
        String sdt1 = "     Số điện thoại: "+sodt;
        runBenMua1.setText(ngaysinh);
        runBenMua1.addBreak();
        runBenMua1.setText(socmt);
        runBenMua1.addBreak();
        runBenMua1.setText(diachi1);
        runBenMua1.addBreak();
        runBenMua1.setText(sdt1);
        runBenMua1.addBreak();

        XWPFParagraph hopdong = document.createParagraph();
        XWPFRun runhd = hopdong.createRun();
        runhd.setFontSize(16);
        runhd.setBold(true);
        String ttt = "THÔNG TIN HỢP ĐỒNG: ";
        runhd.setText(ttt);
        runhd.addBreak();

        //ThongTinTrọ
        XWPFRun tttro = hopdong.createRun();
        tttro.setFontSize(14);
        String ngaytao = "Ngày tạo hợp đồng: " + timeFormat.format(NgayTao);
        String NgayBD = "Ngày bắt đầu: " + timeFormat.format(NgayBatDau);
        String Ketthuc = "Ngày kết thúc hợp đồng:" + timeFormat.format(NgayKT);
        String trathang = "Trả hằng tháng: " + Tratheothang;
        String Tiencoc = "Đã đặt cọc : " + TienCoc;
        String Tratruoc = "Tiền đã trả trước: " + traTruoc;
        String tongtien = "Tổng số Tiền:" + TongTien;


        tttro.setText(ngaytao);
        tttro.addBreak();
        tttro.setText(NgayBD);
        tttro.addBreak();
        tttro.setText(Ketthuc);
        tttro.addBreak();
        tttro.setText(trathang);
        tttro.addBreak();
        tttro.setText(Tiencoc);
        tttro.addBreak();
        tttro.setText(Tratruoc);
        tttro.addBreak();
        tttro.setText(tongtien);
        tttro.addBreak();

//dieukhoan
        XWPFParagraph dieukhoan = document.createParagraph();
        XWPFRun dkk = dieukhoan.createRun();
        dkk.setFontSize(16);
        dkk.setBold(true);
        String dk = "ĐIỀU KHOẢN: ";
        dkk.setText(dk);
        dkk.addBreak();

       XWPFRun dieukhoan1 = dieukhoan.createRun();
        dieukhoan1.setFontSize(14);
        String dieukhoan1s = dieuk;
        
        dieukhoan1.setText(dieukhoan1s);
        dieukhoan1.addBreak();
        
        //Ký tên
        XWPFTable KyTen = document.createTable();
        KyTen.setWidth("8500");
        KyTen.setTableAlignment(TableRowAlign.CENTER);
        KyTen.removeBorders();

        XWPFParagraph para3 = document.createParagraph();
        para3.setAlignment(ParagraphAlignment.CENTER);

        XWPFTableRow rowOne = KyTen.getRow(0);
        rowOne.getCell(0).setParagraph(para3);
        rowOne.getCell(0).setText("BÊN CHO THUÊ");
        rowOne.addNewTableCell().setParagraph(para3);
        rowOne.getCell(1).setText("BÊN THUÊ");

        XWPFTableRow rowTwo = KyTen.createRow();
        rowTwo.getCell(0).setParagraph(para3);
        rowTwo.getCell(0).setText("ký ghi rõ họ tên");
        rowTwo.getCell(1).setParagraph(para3);
        rowTwo.getCell(1).setText("ký ghi rõ họ tên, chức vụ và đóng dấu của doanh nghiệp");

        //ghi file
        try {
            File parentPath = new File("..\\quan_ly_nha_tro\\ThongKe");
            if (!parentPath.exists()) {
                parentPath.mkdirs();
            }
            File dd = new File("..\\quan_ly_nha_tro\\ThongKe\\hopdong" + sohd + ".docx");
            if (dd.exists()) {
                int n = JOptionPane.showConfirmDialog(null, "Hợp đồng đã có bạn muốn tạo lại.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                if (n == JOptionPane.YES_OPTION) {
                    dd.delete();
                    FileOutputStream out = new FileOutputStream(new File("..\\quan_ly_nha_tro\\ThongKe\\hopdong" + sohd + ".docx"));
                    document.write(out);
                    out.close();
                    document.close();
                    JOptionPane.showMessageDialog(null, "Tạo hợp đồng thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    JOptionPane.showMessageDialog(null, "Đang mở hợp đồng " + sohd, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(new File("..\\quan_ly_nha_tro\\ThongKe\\hopdong" + sohd + ".docx"));
                } else {
                    JOptionPane.showMessageDialog(null, "bạn chọn không tạo lại hợp đồng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    JOptionPane.showMessageDialog(null, "Đang mở hợp đồng " + sohd, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(new File("..\\quan_ly_nha_tro\\ThongKe\\hopdong" + sohd + ".docx"));
                }
            } else {
                FileOutputStream out = new FileOutputStream(new File("..\\quan_ly_nha_tro\\ThongKe\\hopdong" + sohd + ".docx"));
                document.write(out);
                out.close();
                document.close();
                JOptionPane.showMessageDialog(null, "Tạo hợp đồng thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "Đang mở hợp đồng " + sohd, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                Desktop.getDesktop().open(new File("..\\quan_ly_nha_tro\\ThongKe\\hopdong" + sohd + ".docx"));
            }
        } catch (Exception e) {
        }
//        try {
//
//            FileOutputStream out = new FileOutputStream(new File("..\\quan_ly_nha_tro\\ThongKe\\hopdong"+sohd+".docx"));
//            document.write(out);
//            out.close();
//            document.close();
//            JOptionPane.showMessageDialog(null, "Tạo hợp đồng thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//            
//        } catch (IOException e) {
//            System.out.println(e);
//        }

    }
}
