package Model;

import java.io.Serializable;
import java.util.Date;

public class DonHang implements Serializable {
    private int ma;
    private String tenKH;
    private Date ngayDat;
    private boolean daThanhToan;


    public DonHang() {
    }

    public DonHang(int ma, String tenKH, Date ngayDat, boolean daThanhToan) {
        this.ma = ma;
        this.tenKH = tenKH;
        this.ngayDat = ngayDat;
        this.daThanhToan = daThanhToan;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public boolean isDaThanhToan() {
        return daThanhToan;
    }

    public void setDaThanhToan(boolean daThanhToan) {
        this.daThanhToan = daThanhToan;
    }
}
