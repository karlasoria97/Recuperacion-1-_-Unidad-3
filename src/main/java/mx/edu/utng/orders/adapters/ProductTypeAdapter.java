package mx.edu.utng.orders.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.edu.utng.orders.R;
import mx.edu.utng.orders.model.ProductType;

/**
 * Created by Karla on 23/02/2017.
 */

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.ProductViewHolder>
implements View.OnClickListener{

    List<ProductType> productTypes;
    View.OnClickListener listener;
    //Constructor
    public ProductTypeAdapter(List<ProductType> productTypes){
        this.productTypes=productTypes;
    }
    //getter and setter de listener
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ProductTypeAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_product_type,parent,false);
        ProductTypeAdapter.ProductViewHolder holder=new ProductTypeAdapter.ProductViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductTypeAdapter.ProductViewHolder holder, int position) {
        holder.tvProductDescription.setText(productTypes.get(position).getDescription());
        holder.tvProductPicture.setText(productTypes.get(position).getPicture());
        holder.tvProductProductCategory.setText(productTypes.get(position).getProductCategory());
        holder.iv_ProductType.setImageResource(R.mipmap.ic_launcher);
        holder.setListener(this);

    }

    @Override
    public int getItemCount() {
        return productTypes.size();
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public static class ProductViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cvProductType;
        TextView tvProductDescription;
        TextView tvProductPicture;
        TextView tvProductProductCategory;
        ImageView iv_ProductType;
        ImageButton btEditProductType;
        ImageButton btDeleteProductType;
        View.OnClickListener listener;

        public ProductViewHolder(View itemView) {
            super(itemView);
            cvProductType=(CardView)itemView.findViewById(R.id.cv_productType);
            iv_ProductType=(ImageView)itemView.findViewById(R.id.iv_productType);
            tvProductDescription=(TextView)itemView.findViewById(R.id.tv_product_description);
            tvProductPicture=(TextView)itemView.findViewById(R.id.tv_product_picture);
            tvProductProductCategory=(TextView)itemView.findViewById(R.id.tv_product_description);
            btEditProductType=(ImageButton) itemView.findViewById(R.id.bt_edit_product_type);
            btDeleteProductType=(ImageButton) itemView.findViewById(R.id.bt_delete_product_type);
            btEditProductType.setOnClickListener(this);
            btDeleteProductType.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                listener.onClick(v);
            }
        }

        public void setListener(View.OnClickListener listener){
            this.listener=listener;

        }
    }

}
