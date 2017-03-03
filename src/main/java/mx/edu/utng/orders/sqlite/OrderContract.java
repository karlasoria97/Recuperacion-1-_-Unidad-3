package mx.edu.utng.orders.sqlite;

import java.util.UUID;

/**
 * Created by Karla on 7/02/17.
 */

public class OrderContract {

    interface OrderHeaderColumns{
        String ID = "id";
        String DATE = "record_date";
        String ID_CUSTOMER = "id_customer";
        String ID_METHOD_PAYMENT = "id_method_payment";
    }

    interface OrderDetailColumns{
        String ID = "id";
        String SEQUENCE = "sequence";
        String ID_PRODUCT = "id_product";
        String QUANTITY = "quantity";
        String PRICE = "price";
    }

    interface ProductColumns{
        String ID = "id";
        String NAME = "name";
        String PRICE = "price";
        String STOCK = "stock";
    }

    interface  CustomerColumns{
        String ID = "id";
        String FIRSTNAME = "firstname";
        String LASTNAME = "lastname";
        String PHONE = "phone";
    }

    interface MethodPaymentColumns{
        String ID = "id";
        String NAME = "name";
    }

    interface ProductTypeColumns{
        String ID = "id";
        String DESCRIPTION = "description";
        String PICTURE = "picture";
        String PROCATEGORY = "productcategory";
    }

    interface VehiculoColumns{
        String ID = "id";
        String PLACA = "placa";
        String DESCRIPCION = "descripcion";
        String ASIENTOS = "asientos";
        String PESO = "peso";
        String ESTADO = "estado";
        String CARGA = "carga";
        String FABRICACION = "fabricacion";
        String ADQUIRIDO = "aquirido";
    }

    interface FigureColumns{
        String ID = "id";
        String TEO = "teo";
        String CODE = "code";
        String DRAWER = "drawer";
        String FIGURE = "figure";
        String USER = "user";
        String BIBREF = "bibref";
        String DATE = "date";
    }

    public static class OrderHeaders implements OrderHeaderColumns{
        public static String generateIdOrderHeader(){
            return  "OH-"+ UUID.randomUUID().toString();
        }
    }

    public static class OrderDetails implements OrderDetailColumns{

    }

    public static class Products implements ProductColumns{
        public static String generateIdProduct(){
            return  "PR-"+ UUID.randomUUID().toString();
        }
    }

    public static class Customers implements CustomerColumns{
        public static String generateIdCustomer(){
            return  "CU-"+ UUID.randomUUID().toString();
        }
    }

    public static class MethodsPayment implements MethodPaymentColumns{
        public static String generateIdMethodPayment(){
            return  "MP-"+ UUID.randomUUID().toString();
        }
    }

    public static class ProductType implements ProductTypeColumns{
        public static  String generateIdProductType(){
            return "PT-" + UUID.randomUUID().toString();
        }
    }

    public static class Vehiculo implements VehiculoColumns{
        public static  String generateIdVehiculo(){
            return "VH-" + UUID.randomUUID().toString();
        }
    }

    public static class Figure implements FigureColumns{
        public static  String generateIdFigure(){
            return "FG-" + UUID.randomUUID().toString();
        }
    }

    private OrderContract() {

    }
}
