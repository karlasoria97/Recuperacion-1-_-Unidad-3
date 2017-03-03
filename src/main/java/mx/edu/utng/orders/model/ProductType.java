package mx.edu.utng.orders.model;

/**
 * Created by Karla on 23/02/2017.
 */

public class ProductType {

    private String idProductType;
    private String picture;
    private String description;
    private String productCategory;

    public ProductType(String idProductType, String picture,
                       String description, String productCategory) {
        this.idProductType = idProductType;
        this.picture = picture;
        this.description = description;
        this.productCategory = productCategory;
    }

    public ProductType(){
        this("","","","");
    }

    public String getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(String idProductType) {
        this.idProductType = idProductType;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "idProductType='" + idProductType + '\'' +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                ", productCategory='" + productCategory + '\'' +
                '}';
    }
}
