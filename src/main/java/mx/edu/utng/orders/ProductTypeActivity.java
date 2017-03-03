package mx.edu.utng.orders;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.orders.adapters.ProductTypeAdapter;
import mx.edu.utng.orders.model.ProductType;
import mx.edu.utng.orders.sqlite.DBOperations;

/**
 * Created by Karla on 23/02/2017.
 */

public class ProductTypeActivity extends AppCompatActivity{
    private EditText etDescription;
    private EditText etPicture;
    private EditText etProductCategory;
    private Button btSaveProductType;
    private DBOperations operations;
    private ProductType productType = new ProductType();
    private RecyclerView rvProductTypes;
    private List<ProductType> productTypes = new ArrayList<ProductType>();
    private ProductTypeAdapter Typeadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_type);

        operations = DBOperations.getDBOperations(getApplicationContext());
        productType.setIdProductType("");

        initComponents();
    }

    private void initComponents(){
        etDescription=(EditText)findViewById(R.id.et_description);
        etPicture=(EditText)findViewById(R.id.et_picture);
        etProductCategory=(EditText)findViewById(R.id.et_productCategory);
        rvProductTypes=(RecyclerView)findViewById(R.id.rv_product_type_list);
        rvProductTypes.setHasFixedSize(true);
        LinearLayoutManager layoutManeger=new LinearLayoutManager(this);
        rvProductTypes.setLayoutManager(layoutManeger);
        //
        getProductTypes();
        Typeadapter=new ProductTypeAdapter(productTypes);

        Typeadapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bt_delete_product_type:
                        operations.deleteProductType(productTypes.get
                                (rvProductTypes.getChildPosition((View)v.getParent().getParent())).getIdProductType());
                        getProductTypes();
                        Typeadapter.notifyDataSetChanged();
                        break;
                    case R.id.bt_edit_product_type:

                        Toast.makeText(getApplicationContext(),
                                productTypes.get(rvProductTypes.getChildPosition((View)v.getParent().getParent())).getIdProductType(), Toast.LENGTH_SHORT).show();
                        Cursor c = operations.getProductsTypeById(productTypes.get(
                                rvProductTypes.getChildPosition(
                                        (View)v.getParent().getParent())).getIdProductType());
                        if (c!=null){
                            if (c.moveToFirst()){
                                productType = new ProductType(c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                                Log.i("productType", productType.toString());
                            }
                            etDescription.setText(productType.getDescription());
                            etPicture.setText(productType.getPicture());
                            etProductCategory.setText(productType.getProductCategory());
                        }else{
                            Toast.makeText(getApplicationContext(),"Registro no encontrado",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        });
        rvProductTypes.setAdapter(Typeadapter);

        etDescription=(EditText)findViewById(R.id.et_description);
        etPicture=(EditText)findViewById(R.id.et_picture);
        etProductCategory=(EditText)findViewById(R.id.et_productCategory);

        btSaveProductType=(Button)findViewById(R.id.bt_save_productType);

        btSaveProductType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!productType.getIdProductType().equals("")){
                    productType.setDescription(etDescription.getText().toString());
                    productType.setPicture(etPicture.getText().toString());
                    productType.setProductCategory(etProductCategory.getText().toString());
                    operations.updateProductType(productType);

                }else {
                    productType = new ProductType("", etDescription.getText().toString(),etPicture.getText().toString(), etProductCategory.getText().toString());
                    operations.insertProductType(productType);
                }
                getProductTypes();
                clearData();
                Typeadapter.notifyDataSetChanged();
            }
        });

    }
    private void getProductTypes(){
        Cursor c =operations.getProductTypes();
        productTypes.clear();
        if(c!=null){
            while (c.moveToNext()){
                productTypes.add(new ProductType(c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
            }
        }

    }

    private void clearData(){
        etDescription.setText("");
        etPicture.setText("");
        etProductCategory.setText("");
        productType=null;
        productType= new ProductType();
        productType.setIdProductType("");
    }
}
