package vn.edu.stu.demosqlite.model;

public class SinhVien {
    private int ma;
    private String ten;
    private int namSinh;

    public SinhVien() {
    }

    public SinhVien(String ten, int namSinh) {
        this.ten = ten;
        this.namSinh = namSinh;
    }

    public SinhVien(int ma, String ten, int namSinh) {
        this.ma = ma;
        this.ten = ten;
        this.namSinh = namSinh;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    @Override
    public String toString() {
        return ma + " - " + ten + " - " + namSinh;
    }
}
