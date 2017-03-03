package mx.edu.utng.orders.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

/**
 * Created by Karla on 7/02/17.
 */

public class OrderDB extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "order.db";
    private static final int CURRENT_VERSION = 1;
    private final Context context;

    interface Tables{
        String ORDER_HEADER = "order_header";
        String ORDER_DETAIL = "order_detail";
        String PRODUCT = "product";
        String CUSTOMER = "customer";
        String METHOD_PAYMENT = "method_payment";
        String PRODUCT_TYPE = "product_type";
        String VEHICULO = "vehiculo";
        String FIGURE = "figure";
    }

    interface References{
        String ID_ORDER_HEADER =
                String.format("REFERENCES %s(%s)" +
                        " ON DELETE CASCADE",
                        Tables.ORDER_HEADER,
                        OrderContract.OrderHeaders.ID);
        String ID_PRODUCT =
                String.format("REFERENCES %s(%s)" +
                                " ON DELETE CASCADE",
                        Tables.PRODUCT,
                        OrderContract.Products.ID);
        String ID_CUSTOMER =
                String.format("REFERENCES %s(%s)" +
                                " ON DELETE CASCADE",
                        Tables.CUSTOMER,
                        OrderContract.Customers.ID);
        String ID_METHOD_PAYMENT =
                String.format("REFERENCES %s(%s)" +
                                " ON DELETE CASCADE",
                        Tables.METHOD_PAYMENT,
                        OrderContract.MethodsPayment.ID);
        String ID_PRODUCT_TYPE=
                String.format("REFERENCES %s(%s)" +
                        "ON DELETE CASCADE",
                        Tables.PRODUCT_TYPE,
                        OrderContract.ProductType.ID);

    }

    public OrderDB(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_VERSION);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                db.setForeignKeyConstraintsEnabled(true);
            }else{
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format(
          "CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  "%s TEXT UNIQUE NOT NULL, %s DATETIME NOT NULL," +
                  "%s TEXT NOT NULL %s, %s TEXT NOT NULL %s)",
                Tables.ORDER_HEADER, BaseColumns._ID,
                OrderContract.OrderHeaders.ID,
                OrderContract.OrderHeaders.DATE,
                OrderContract.OrderHeaders.ID_CUSTOMER,
                References.ID_CUSTOMER,
                OrderContract.OrderHeaders.ID_METHOD_PAYMENT,
                References.ID_METHOD_PAYMENT));

        db.execSQL(String.format(
                "CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL %s," +
                        " %s INTEGER NOT NULL CHECK (%s>0)," +
                        "%s INTEGER NOT NULL %s, %s INTEGER NOT NULL, " +
                        "%s REAL NOT NULL, UNIQUE(%s, %s))",

                Tables.ORDER_DETAIL,
                BaseColumns._ID,
                OrderContract.OrderDetails.ID,
                References.ID_ORDER_HEADER,
                OrderContract.OrderDetails.SEQUENCE,
                OrderContract.OrderDetails.SEQUENCE,
                OrderContract.OrderDetails.ID_PRODUCT,
                References.ID_PRODUCT,
                OrderContract.OrderDetails.QUANTITY,
                OrderContract.OrderDetails.PRICE,
                OrderContract.OrderDetails.ID,
                OrderContract.OrderDetails.SEQUENCE));



        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY" +
                " AUTOINCREMENT, %s TEXT NOT NULL UNIQUE, %s TEXT NOT NULL," +
                " %s REAL NOT NULL, %s INTEGER NOT NULL CHECK(%s>=0))",
                Tables.PRODUCT, BaseColumns._ID,
                OrderContract.Products.ID,
                OrderContract.Products.NAME,
                OrderContract.Products.PRICE,
                OrderContract.Products.STOCK,
                OrderContract.Products.STOCK));

        db.execSQL(String.format("CREATE TABLE %s( %s INTEGER PRIMARY KEY" +
                        " AUTOINCREMENT, %s TEXT NOT NULL UNIQUE, %s TEXT" +
                        " NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)",
                Tables.CUSTOMER,
                BaseColumns._ID,
                OrderContract.Customers.ID,
                OrderContract.Customers.FIRSTNAME,
                OrderContract.Customers.LASTNAME,
                OrderContract.Customers.PHONE));

        db.execSQL(String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY" +
                " AUTOINCREMENT, %s TEXT NOT NULL UNIQUE, %s TEXT NOT NULL)",
                Tables.METHOD_PAYMENT,
                BaseColumns._ID,
                OrderContract.MethodsPayment.ID,
                OrderContract.MethodsPayment.NAME));

        db.execSQL(String.format("CREATE TABLE %s( %s INTEGER PRIMARY KEY" +
                        " AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT" +
                        " NOT NULL, %s TEXT NOT NULL,%s TEXT NOT NULL)",
                Tables.PRODUCT_TYPE,
                BaseColumns._ID,
                OrderContract.ProductType.ID,
                OrderContract.ProductType.DESCRIPTION,
                OrderContract.ProductType.PICTURE,
                OrderContract.ProductType.PROCATEGORY));

        /**NUEVA*/

        db.execSQL(String.format("CREATE TABLE %s( %s INTEGER PRIMARY KEY" +
                        " AUTOINCREMENT, %s TEXT NOT NULL,%s TEXT NOT NULL, %s TEXT" +
                        " NOT NULL, %s TEXT NOT NULL,%s TEXT NOT NULL," +
                "%s TEXT NOT NULL,%s TEXT NOT NULL,%s INTEGER NOT NULL," +
                "%s INTEGER NOT NULL )",
                Tables.VEHICULO,
                BaseColumns._ID,
                OrderContract.Vehiculo.ID,
                OrderContract.Vehiculo.PLACA,
                OrderContract.Vehiculo.DESCRIPCION,
                OrderContract.Vehiculo.ASIENTOS,
                OrderContract.Vehiculo.PESO,
                OrderContract.Vehiculo.ESTADO,
                OrderContract.Vehiculo.CARGA,
                OrderContract.Vehiculo.FABRICACION,
                OrderContract.Vehiculo.ADQUIRIDO));

        db.execSQL(String.format("CREATE TABLE %s( %s INTEGER PRIMARY KEY" +
                        " AUTOINCREMENT, %s TEXT NOT NULL,%s TEXT NOT NULL, %s TEXT" +
                        " NOT NULL, %s TEXT NOT NULL,%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL)",
                Tables.FIGURE,
                BaseColumns._ID,
                OrderContract.Figure.ID,
                OrderContract.Figure.TEO,
                OrderContract.Figure.CODE,
                OrderContract.Figure.DRAWER,
                OrderContract.Figure.FIGURE,
                OrderContract.Figure.USER,
                OrderContract.Figure.BIBREF,
                OrderContract.Figure.DATE));
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Tables.ORDER_HEADER);
        db.execSQL("DROP TABLE IF EXISTS "+ Tables.ORDER_DETAIL);
        db.execSQL("DROP TABLE IF EXISTS "+ Tables.PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS "+ Tables.METHOD_PAYMENT);
        db.execSQL("DROP TABLE IF EXISTS "+ Tables.CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS "+ Tables.PRODUCT_TYPE);
        db.execSQL("DROP TABLE IF EXISTS "+ Tables.VEHICULO);
        db.execSQL("DROP TABLE IF EXISTS "+ Tables.FIGURE);

        onCreate(db);

    }
}
