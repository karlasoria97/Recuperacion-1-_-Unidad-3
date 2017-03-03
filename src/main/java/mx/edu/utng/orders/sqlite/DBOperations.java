package mx.edu.utng.orders.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import mx.edu.utng.orders.model.Figure;
import mx.edu.utng.orders.model.Product;
import mx.edu.utng.orders.model.MethodPayment;
import mx.edu.utng.orders.model.OrderDetail;
import mx.edu.utng.orders.model.OrderHeader;
import mx.edu.utng.orders.model.Customer;
import mx.edu.utng.orders.model.ProductType;
import mx.edu.utng.orders.model.Vehiculo;


/**
 * Created by Karla on 7/02/17.
 */

public final class DBOperations {
    private static OrderDB db;
    private static DBOperations operations;

    private static final String JOIN_ORDER_CUSTOMER_METHOD =
            "order_header " +
            "INNER JOIN customer " +
            "ON order_header.id_customer = customer.id " +
            "INNER JOIN method_payment " +
            "ON order_header.id_method_payment = method_payment.id";
    private final String[] orderProj = new String[]{
            OrderDB.Tables.ORDER_HEADER + "."
                    + OrderContract.OrderHeaders.ID,
            OrderContract.OrderHeaders.DATE,
            OrderContract.Customers.FIRSTNAME,
            OrderContract.Customers.LASTNAME,
            OrderContract.MethodsPayment.NAME};

    private DBOperations(){

    }

    public static DBOperations getDBOperations(
            Context context){
        if(operations==null) {
            operations = new DBOperations();
        }
        if(db==null){
            db = new OrderDB(context);
        }
        return operations;
    }
    //Operations of  Orders
    public Cursor getOrders(){
        SQLiteDatabase database = db.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(JOIN_ORDER_CUSTOMER_METHOD);

        return  builder.query(database, orderProj,
                null, null, null, null, null);
    }

    public Cursor getOrderById(String id){
        SQLiteDatabase database = db.getWritableDatabase();
        String selection = String.format("%s=?",
                OrderContract.OrderHeaders.ID);
        String[] selectionArgs = {id};
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(JOIN_ORDER_CUSTOMER_METHOD);
        String[] projection = {
                OrderDB.Tables.ORDER_HEADER+"."
                        + OrderContract.OrderHeaders.ID,
                OrderContract.OrderHeaders.DATE,
                OrderContract.Customers.FIRSTNAME,
                OrderContract.Customers.LASTNAME,
                OrderContract.MethodsPayment.NAME
        };
        return builder.query(database, projection, selection,
                selectionArgs, null, null, null);
    }

    public String insertOrderHeader(OrderHeader orderHeader){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        String idOrderHeader =
                OrderContract.OrderHeaders.generateIdOrderHeader();
        values.put(OrderContract.OrderHeaders.ID, idOrderHeader);
        values.put(OrderContract.OrderHeaders.DATE,
                orderHeader.getDate());
        values.put(OrderContract.OrderHeaders.ID_CUSTOMER,
                orderHeader.getIdCustomer());
        values.put(OrderContract.OrderHeaders.ID_METHOD_PAYMENT,
                orderHeader.getIdMethodPayment());

        database.insertOrThrow(OrderDB.Tables.ORDER_HEADER,
                null, values);
        return idOrderHeader;
    }

    public boolean updateOrderHeader(OrderHeader orderHeader){
        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderHeaders.DATE,
                orderHeader.getDate());
        values.put(OrderContract.OrderHeaders.ID_CUSTOMER,
                orderHeader.getIdCustomer());
        values.put(OrderContract.OrderHeaders.ID_METHOD_PAYMENT,
                orderHeader.getIdMethodPayment());

        String whereClause = String.format("%s=?", OrderContract.OrderHeaders.ID);
        String[] whereArgs = {orderHeader.getIdOrderHeader()};

        int result = database.update(OrderDB.Tables.ORDER_HEADER, values, whereClause, whereArgs);
        return result>0;
    }

    public boolean deleteOrderHeader(String idOrderHeader){
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause =
                OrderContract.OrderHeaders.ID + "=?";
        String[] whereArgs = {idOrderHeader};

        int result =  database.delete(
                OrderDB.Tables.ORDER_HEADER, whereClause, whereArgs);
        return result > 0;
    }
    //Operations of  OrderDetails
    public Cursor getOrderDetails(){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s",
                OrderDB.Tables.ORDER_DETAIL);
        return  database.rawQuery(sql, null);
    }

    public Cursor getOrderDetailById(String id){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s" +
                " WHERE %s=?", OrderDB.Tables.ORDER_DETAIL,
                OrderContract.OrderHeaders.ID);
        String[] whereArgs = {id};
        return database.rawQuery(sql, whereArgs);
    }

    public String insertOrderDetail(OrderDetail orderDetail){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderDetails.ID,
                orderDetail.getIdOrderHeader());
        values.put(OrderContract.OrderDetails.SEQUENCE,
                orderDetail.getSequence());
        values.put(OrderContract.OrderDetails.ID_PRODUCT,
                orderDetail.getIdProduct());
        values.put(OrderContract.OrderDetails.QUANTITY,
                orderDetail.getQuantity());
        values.put(OrderContract.OrderDetails.PRICE,
                orderDetail.getPrice());

        database.insertOrThrow(OrderDB.Tables.ORDER_DETAIL,
                null, values);
        return String.format("%s#%d",
                orderDetail.getIdOrderHeader(),
                orderDetail.getSequence());
    }

    public boolean updateOrderDetail(OrderDetail orderDetail){
        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderDetails.SEQUENCE,
                orderDetail.getSequence());
        values.put(OrderContract.OrderDetails.QUANTITY,
                orderDetail.getQuantity());
        values.put(OrderContract.OrderDetails.PRICE,
                orderDetail.getPrice());

        String whereClause = String.format("%s=? AND %s=?",
                OrderContract.OrderDetails.ID,
                OrderContract.OrderDetails.SEQUENCE);
        String[] whereArgs = {orderDetail.getIdOrderHeader(),
        String.valueOf(orderDetail.getSequence())};

        int result = database.update(OrderDB.Tables.ORDER_DETAIL,
                values, whereClause, whereArgs);
        return result>0;
    }

    public boolean deleteOrderDetail(String idOrderDetail){
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause =
                OrderContract.OrderHeaders.ID + "=?";
        String[] whereArgs = {idOrderDetail};

        int result =  database.delete(
                OrderDB.Tables.ORDER_DETAIL, whereClause, whereArgs);
        return result > 0;
    }
    //Operations of  Products
    public Cursor getProducts(){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s",
                OrderDB.Tables.PRODUCT);
        return  database.rawQuery(sql, null);
    }

    public Cursor getProductById(String id){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s" +
                        " WHERE %s=?",
                OrderDB.Tables.PRODUCT,
                OrderContract.Products.ID);
        String[] whereArgs = {id};
        return database.rawQuery(sql, whereArgs);
    }

    public String insertProduct(Product product){
        SQLiteDatabase database = db.getWritableDatabase();
        String idProduct = OrderContract.Products.generateIdProduct();
        ContentValues values = new ContentValues();
        values.put(OrderContract.Products.ID, idProduct);
        values.put(OrderContract.Products.NAME, product.getName());
        values.put(OrderContract.Products.PRICE, product.getPrice());
        values.put(OrderContract.Products.STOCK, product.getStock());
        database.insertOrThrow(OrderDB.Tables.PRODUCT, null, values);
        return idProduct;
    }

    public boolean updateProduct(Product product){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderContract.Products.NAME, product.getName());
        values.put(OrderContract.Products.PRICE, product.getPrice());
        values.put(OrderContract.Products.STOCK, product.getStock());
        String whereClause = String.format("%s=?", OrderContract.Products.ID);
        String[] whereArgs = {product.getIdProduct()};
        int result = database.update(OrderDB.Tables.PRODUCT,
                values, whereClause, whereArgs);
        return result>0;
    }

    public boolean deleteProduct(String idProduct){
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause = OrderContract.Products.ID + "=?";
        String[] whereArgs = {idProduct};
        int result =  database.delete(OrderDB.Tables.PRODUCT,
                whereClause, whereArgs);
        return result > 0;
    }
    // Operations Customers
    public Cursor getCustomers() {
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s",
                OrderDB.Tables.CUSTOMER);
        return database.rawQuery(sql, null);
    }
    public Cursor getCustomersById(String idCustomer) {
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s=?",
                OrderDB.Tables.CUSTOMER,
                OrderContract.Customers.ID);
        String[] whereArgs ={idCustomer};
        return database.rawQuery(sql, whereArgs);
    }

    public String insertCustomer(Customer customer) {
        SQLiteDatabase database = db.getWritableDatabase();
        String idCustomer = OrderContract.Customers.generateIdCustomer();
        ContentValues values = new ContentValues();
        values.put(OrderContract.Customers.ID, idCustomer);
        values.put(OrderContract.Customers.FIRSTNAME, customer.getFirstname());
        values.put(OrderContract.Customers.LASTNAME, customer.getLastname());
        values.put(OrderContract.Customers.PHONE, customer.getPhone());

        return database.insertOrThrow(OrderDB.Tables.CUSTOMER,
                null, values) > 0 ? idCustomer : null;
    }

    public boolean updateCustomer(Customer customer) {
        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderContract.Customers.FIRSTNAME,
                customer.getFirstname());
        values.put(OrderContract.Customers.LASTNAME,
                customer.getLastname());
        values.put(OrderContract.Customers.PHONE,
                customer.getPhone());
        String whereClause = String.format("%s=?",
                OrderContract.Customers.ID);
        final String[] whereArgs = {customer.getIdCustomer()};
        int result = database.update(OrderDB.Tables.CUSTOMER,
                values, whereClause, whereArgs);
        return result > 0;
    }
    public boolean deleteCustomer(String idCustomer) {
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause = String.format("%s=?",
                OrderContract.Customers.ID);
        final String[] whereArgs = {idCustomer};
        int result = database.delete(OrderDB.Tables.CUSTOMER, whereClause, whereArgs);
        return result > 0;
    }

    // Operation Product Type
    public Cursor getProductTypes() {
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s",
                OrderDB.Tables.PRODUCT_TYPE);
        return database.rawQuery(sql, null);
    }

    public Cursor getProductsTypeById(String idProductType) {
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s=?",
                OrderDB.Tables.PRODUCT_TYPE,
                OrderContract.ProductType.ID);
        String[] whereArgs = {idProductType};
        return database.rawQuery(sql, whereArgs);
    }
    public String insertProductType(ProductType productType) {
        SQLiteDatabase database = db.getWritableDatabase();

        String idProductType = OrderContract.ProductType.generateIdProductType();

        ContentValues values = new ContentValues();
        values.put(OrderContract.ProductType.ID, idProductType);
        values.put(OrderContract.ProductType.DESCRIPTION, productType.getDescription());
        values.put(OrderContract.ProductType.PICTURE, productType.getPicture());
        values.put(OrderContract.ProductType.PROCATEGORY, productType.getProductCategory());


        return database.insertOrThrow(
                OrderDB.Tables.PRODUCT_TYPE, null,
                values) > 0 ? idProductType : null;
    }

    public boolean updateProductType(ProductType productType) {
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderContract.ProductType.DESCRIPTION,
                productType.getDescription());
        values.put(OrderContract.ProductType.PICTURE,
                productType.getPicture());
        values.put(OrderContract.ProductType.PROCATEGORY,
                productType.getProductCategory());
        String whereClause = String.format("%s=?",
                OrderContract.ProductType.ID);
        String[] whereArgs = {productType.getIdProductType()};
        int result = database.update(
                OrderDB.Tables.PRODUCT_TYPE, values,
                whereClause, whereArgs);
        return result > 0;
    }

    public boolean deleteProductType(String idProductType) {
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause = String.format("%s=?", OrderContract.ProductType.ID);
        String[] whereArgs = {idProductType};
        int result = database.delete(OrderDB.Tables.PRODUCT_TYPE, whereClause, whereArgs);
        return result > 0;
    }

    // Operation Method of payment
    public Cursor getMethodsPayment() {
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s",
                OrderDB.Tables.METHOD_PAYMENT);
        return database.rawQuery(sql, null);
    }

    public Cursor getMethodsPaymentById(String idMethodPayment) {
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s=?",
                OrderDB.Tables.METHOD_PAYMENT,
                OrderContract.MethodsPayment.ID);
        String[] whereArgs = {idMethodPayment};
        return database.rawQuery(sql, whereArgs);
    }
    public String insertMethodPayment(MethodPayment methodPayment) {
        SQLiteDatabase database = db.getWritableDatabase();

        String idMethodPayment = OrderContract.MethodsPayment.generateIdMethodPayment();

        ContentValues values = new ContentValues();
        values.put(OrderContract.MethodsPayment.ID, idMethodPayment);
        values.put(OrderContract.MethodsPayment.NAME, methodPayment.getName());

        return database.insertOrThrow(
                OrderDB.Tables.METHOD_PAYMENT, null,
                values) > 0 ? idMethodPayment : null;
    }

    public boolean updateMethodPayment(MethodPayment methodPayment) {
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderContract.MethodsPayment.NAME,
                methodPayment.getName());
        String whereClause = String.format("%s=?",
                OrderContract.MethodsPayment.ID);
        String[] whereArgs = {methodPayment.getIdMethodPayment()};
        int result = database.update(
                OrderDB.Tables.METHOD_PAYMENT, values,
                whereClause, whereArgs);
        return result > 0;
    }

    public boolean deleteMethodPayment(String idMethodPayment) {
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause = String.format("%s=?", OrderContract.MethodsPayment.ID);
        String[] whereArgs = {idMethodPayment};
        int result = database.delete(OrderDB.Tables.METHOD_PAYMENT, whereClause, whereArgs);
        return result > 0;
    }
    // Operation Vehiculo
    public Cursor getVehiculos() {
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s",
                OrderDB.Tables.VEHICULO);
        return database.rawQuery(sql, null);
    }

    public Cursor getVehiculoById(String idVehiculo) {
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s=?",
                OrderDB.Tables.VEHICULO,
                OrderContract.Vehiculo.ID);
        String[] whereArgs = {idVehiculo};
        return database.rawQuery(sql, whereArgs);
    }
    public String insertVehiculo(Vehiculo vehiculo) {
        SQLiteDatabase database = db.getWritableDatabase();

        String idVehiculo = OrderContract.Vehiculo.generateIdVehiculo();

        ContentValues values = new ContentValues();
        values.put(OrderContract.Vehiculo.ID, idVehiculo);
        values.put(OrderContract.Vehiculo.PLACA, vehiculo.getNroPlaca());
        values.put(OrderContract.Vehiculo.DESCRIPCION, vehiculo.getDescripcion());
        values.put(OrderContract.Vehiculo.ASIENTOS, vehiculo.getNumAsientos());
        values.put(OrderContract.Vehiculo.PESO, vehiculo.getPesoVeh());
        values.put(OrderContract.Vehiculo.ESTADO, vehiculo.getEstadoVeh());
        values.put(OrderContract.Vehiculo.CARGA, vehiculo.getCargaMax());
        values.put(OrderContract.Vehiculo.FABRICACION, vehiculo.getYearFab());
        values.put(OrderContract.Vehiculo.ADQUIRIDO, vehiculo.getYearAdq());
        database.insertOrThrow(OrderDB.Tables.VEHICULO,null, values);
        return idVehiculo;

    }

    public boolean updateVehiculo(Vehiculo vehiculo) {
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderContract.Vehiculo.PLACA,
                vehiculo.getNroPlaca());
        values.put(OrderContract.Vehiculo.DESCRIPCION,
                vehiculo.getDescripcion());
        values.put(OrderContract.Vehiculo.ASIENTOS,
                vehiculo.getNumAsientos());
        values.put(OrderContract.Vehiculo.PESO,
                vehiculo.getPesoVeh());
        values.put(OrderContract.Vehiculo.ESTADO,
                vehiculo.getEstadoVeh());
        values.put(OrderContract.Vehiculo.CARGA,
                vehiculo.getCargaMax());
        values.put(OrderContract.Vehiculo.FABRICACION,
                vehiculo.getYearFab());
        values.put(OrderContract.Vehiculo.ADQUIRIDO,
                vehiculo.getYearAdq());
        String whereClause = String.format("%s=?",
                OrderContract.Vehiculo.ID);
        String[] whereArgs = {vehiculo.getIdVehiculo()};
        int result = database.update(
                OrderDB.Tables.VEHICULO, values,
                whereClause, whereArgs);
        return result > 0;
    }

    public boolean deleteVehiculo(String idVehiculo) {
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause = String.format("%s=?", OrderContract.Vehiculo.ID);
        String[] whereArgs = {idVehiculo};
        int result = database.delete(OrderDB.Tables.VEHICULO, whereClause, whereArgs);
        return result > 0;
    }


    // Operation Figure
    public Cursor getFigures() {
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s",
                OrderDB.Tables.FIGURE);
        return database.rawQuery(sql, null);
    }

    public Cursor getFigureById(String idFigure) {
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s=?",
                OrderDB.Tables.FIGURE,
                OrderContract.Figure.ID);
        String[] whereArgs = {idFigure};
        return database.rawQuery(sql, whereArgs);
    }
    public String insertFigure(Figure figure) {
        SQLiteDatabase database = db.getWritableDatabase();

        String idFigure = OrderContract.Figure.generateIdFigure();

        ContentValues values = new ContentValues();
        values.put(OrderContract.Figure.ID, idFigure);
        values.put(OrderContract.Figure.TEO, figure.getTeo());
        values.put(OrderContract.Figure.CODE, figure.getCode());
        values.put(OrderContract.Figure.DRAWER, figure.getDrawer());
        values.put(OrderContract.Figure.FIGURE, figure.getFigure());
        values.put(OrderContract.Figure.USER, figure.getUser());
        values.put(OrderContract.Figure.BIBREF, figure.getBibref());
        values.put(OrderContract.Figure.DATE, figure.getDateSubmission());



        return database.insertOrThrow(
                OrderDB.Tables.FIGURE, null,
                values) > 0 ? idFigure : null;
    }

    public boolean updateFigure(Figure figure) {
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderContract.Figure.TEO,
                figure.getTeo());
        values.put(OrderContract.Figure.CODE,
                figure.getCode());
        values.put(OrderContract.Figure.DRAWER,
                figure.getDrawer());
        values.put(OrderContract.Figure.FIGURE,
                figure.getFigure());
        values.put(OrderContract.Figure.USER,
                figure.getUser());
        values.put(OrderContract.Figure.BIBREF,
                figure.getBibref());
        values.put(OrderContract.Figure.DATE,
                figure.getDateSubmission());
        String whereClause = String.format("%s=?",
                OrderContract.Figure.ID);
        String[] whereArgs = {figure.getIdFigure()};
        int result = database.update(
                OrderDB.Tables.FIGURE, values,
                whereClause, whereArgs);
        return result > 0;
    }

    public boolean deleteFigure(String idFigure) {
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause = String.format("%s=?", OrderContract.Figure.ID);
        String[] whereArgs = {idFigure};
        int result = database.delete(OrderDB.Tables.FIGURE, whereClause, whereArgs);
        return result > 0;
    }



    public SQLiteDatabase getDb() {
        return db.getWritableDatabase();
    }


}
