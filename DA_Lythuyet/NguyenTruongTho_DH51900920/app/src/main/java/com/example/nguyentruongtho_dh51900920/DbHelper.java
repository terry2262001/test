package com.example.nguyentruongtho_dh51900920;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nguyentruongtho_dh51900920.Model.Product;
import com.example.nguyentruongtho_dh51900920.Model.ProductType;

import java.util.ArrayList;

public class DbHelper {
    String dbName ="dbProductManagement";
    String tblProduct = "tblProduct";
    String tblProductType = "tblProductType";

    public static final String COL_ID= "id";
    public static final String COL_NAME= "name";
    public static final String COL_TYPEID= "typeid";
    public static final String COL_IMAGE= "image";
    public static final String COL_DESC= "description";
    public static final String COL_PRICE= "price";




    Context mContext;

    public DbHelper(Context mContext) {
        this.mContext = mContext;
    }

    public SQLiteDatabase openDB(){
        return mContext.openOrCreateDatabase(dbName,Context.MODE_PRIVATE,null);
    }
    public void closeDB(SQLiteDatabase db ){db.close();    }
    public  void createTableProduct(){
        SQLiteDatabase db = openDB();
        String sql = "create table if not exists " + tblProduct + "(" + "" +
                "id INTEGER primary key autoincrement,"
                +"name TEXT,"
                +"image BLOG,"
                +"description TEXT,"
                +"price TEXT,"
                +"typeid integer references "+ tblProductType +"(" +"id"+")"+")";
        db.execSQL(sql);
        closeDB(db);
    }
    public  void createTableProduct1(){
        SQLiteDatabase db = openDB();
        String sql = "create table if not exists " + tblProduct + "(" + "" +
                "id INTEGER primary key autoincrement,"
                +"name TEXT,"
                +"image BLOG,"
                +"description TEXT,"
                +"price REAL,"
                +"typeid integer references "+ tblProductType +"(" +"id"+")"+")";
        db.execSQL(sql);
        closeDB(db);
    }
    public void createTableProductType(){
        SQLiteDatabase db = openDB();
        String sql = "create table if not exists "+tblProductType+"(" + "" +
                "id INTEGER primary key autoincrement,"
                +"name TEXT )";
        db.execSQL(sql);
        closeDB(db);
    }


    public long insertProductData(Product product){
        SQLiteDatabase db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,product.getName());
        contentValues.put(COL_IMAGE,product.getImage());
        contentValues.put(COL_DESC,product.getDescription());
        contentValues.put(COL_PRICE,product.getPrice());
        contentValues.put(COL_TYPEID,product.getTypeid());

        long tmp  = db.insert(tblProduct, null,contentValues);
        db.close();
        return tmp;
    }
    public long insertProductData1(Product product){
        SQLiteDatabase db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,product.getName());
        contentValues.put(COL_IMAGE,product.getImage());
        contentValues.put(COL_DESC,product.getDescription());
        contentValues.put(COL_PRICE,product.getPriceD());
        contentValues.put(COL_TYPEID,product.getTypeid());

        long tmp  = db.insert(tblProduct, null,contentValues);
        db.close();
        return tmp;
    }

    public long insertProductType(ProductType productType){
        SQLiteDatabase db = openDB();
        ContentValues contentValues  = new ContentValues();
        contentValues.put(COL_NAME, productType.getName());
        long    tmp = db.insert(tblProductType,null,contentValues);
        db.close();
        return tmp;
    }

    public long updateProduct(Product newProduct){
        SQLiteDatabase db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,newProduct.getName());
        contentValues.put(COL_IMAGE,newProduct.getImage());
        contentValues.put(COL_DESC,newProduct.getDescription());
        contentValues.put(COL_PRICE,newProduct.getPrice());
        contentValues.put(COL_TYPEID,newProduct.getTypeid());

        int tmp = db.update(tblProduct,contentValues, "id=?", new String[]{String.valueOf(newProduct.getId())});
        closeDB(db);
        return  tmp;

    }
    public long updateProduct1(Product newProduct){
        SQLiteDatabase db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,newProduct.getName());
        contentValues.put(COL_IMAGE,newProduct.getImage());
        contentValues.put(COL_DESC,newProduct.getDescription());
        contentValues.put(COL_PRICE,newProduct.getPriceD());
        contentValues.put(COL_TYPEID,newProduct.getTypeid());

        int tmp = db.update(tblProduct,contentValues, "id=?", new String[]{String.valueOf(newProduct.getId())});
        closeDB(db);
        return  tmp;

    }
    public long updateProductType(ProductType newProductType){
        SQLiteDatabase db = openDB();
        ContentValues contentValues  = new ContentValues();
        contentValues.put(COL_NAME, newProductType.getName());
        int tmp = db.update(tblProductType,contentValues, "id=?", new String[]{String.valueOf(newProductType.getId())});
        closeDB(db);
        return  tmp;

    }
    public ArrayList<Product> getAllProductSQL(){

        ArrayList<Product> products = new ArrayList<>();

        SQLiteDatabase db = openDB();
        // tao cau truy vam
        String sql =
                " SELECT "+ tblProduct+".id,"+tblProduct+".name,"+tblProduct+".image,"+tblProduct+".description,"+"tblProduct.price,"+tblProduct+".typeid,"+ tblProductType+".name "
       +" FROM " + tblProduct +
        " INNER JOIN " + tblProductType +
        " ON " + tblProduct +".typeid = " + tblProductType + ".id  ";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            String desc = cursor.getString(3);
            String price = cursor.getString(4);
            int typeid = cursor.getInt(5);
            String type = cursor.getString(6);
            Product product = new Product(id,name,image,desc,price,typeid,type);
            products.add(product);
        }
        closeDB(db);

        return products;

    }
    public ArrayList<Product> getAllProductSQL1(){

        ArrayList<Product> products = new ArrayList<>();

        SQLiteDatabase db = openDB();
        // tao cau truy vam
        String sql =
                " SELECT "+ tblProduct+".id,"+tblProduct+".name,"+tblProduct+".image,"+tblProduct+".description,"+"tblProduct.price,"+tblProduct+".typeid,"+ tblProductType+".name "
                        +" FROM " + tblProduct +
                        " INNER JOIN " + tblProductType +
                        " ON " + tblProduct +".typeid = " + tblProductType + ".id  ";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            String desc = cursor.getString(3);
            double priceD = cursor.getDouble(4);
            int typeid = cursor.getInt(5);
            String type = cursor.getString(6);
            Product product = new Product(id,name,image,desc,priceD,typeid,type);
            products.add(product);
        }
        closeDB(db);

        return products;

    }
    public ArrayList<ProductType> getAllProductTypes(){

        ArrayList<ProductType> mtp = new ArrayList<>();

        SQLiteDatabase db = openDB();
        // tao cau truy vam
        String sql = "SELECT * FROM "+ tblProductType;
        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);

            mtp.add(new ProductType(id,name));

        }
        closeDB(db);

        return mtp;

    }

    public ArrayList<Product> getAllProduct(){
        SQLiteDatabase db = openDB();
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM " + tblProduct;
        Cursor cursor  = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            String desc = cursor.getString(3);
            String price = cursor.getString(4);
            int typeid = cursor.getInt(5);
            Product product = new Product(id,name,image,desc,price,typeid);
            products.add(product);
        }
        closeDB(db);
        return products;


    }
    public  int deleteProduct(int id){
        SQLiteDatabase db = openDB();
        String idDelete = String.valueOf(id);
        int tmp = db.delete(tblProduct,"id=?",new String[]{idDelete});
        closeDB(db);
        return  tmp;

    }
    public  int deleteProductType(int id){
        SQLiteDatabase db = openDB();
        String idDelete = String.valueOf(id);
        int tmp = db.delete(tblProductType,"id=?",new String[]{idDelete});
        closeDB(db);
        return  tmp;

    }


}

