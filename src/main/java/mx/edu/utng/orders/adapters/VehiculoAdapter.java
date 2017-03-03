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
import mx.edu.utng.orders.model.Vehiculo;

/**
 * Created by Karla on 27/02/2017.
 */

public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.VehiculoViewHolder>
        implements View.OnClickListener{

    List<Vehiculo> vehiculos;
    View.OnClickListener listener;
    //Constructor
    public VehiculoAdapter(List<Vehiculo> vehiculos){
        this.vehiculos=vehiculos;
    }
    //getter and setter de listener
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public VehiculoAdapter.VehiculoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_vehiculo,parent,false);
        VehiculoAdapter.VehiculoViewHolder holder=new VehiculoViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(VehiculoAdapter.VehiculoViewHolder holder, int position) {
        holder.tvPlaca.setText(vehiculos.get(position).getNroPlaca());
        holder.tvDescripcion.setText(vehiculos.get(position).getDescripcion());
        holder.tvAsientos.setText(vehiculos.get(position).getNumAsientos());
        holder.tvPeso.setText(vehiculos.get(position).getPesoVeh());
        holder.tvEstado.setText(vehiculos.get(position).getEstadoVeh());
        holder.tvCarga.setText(vehiculos.get(position).getCargaMax());
        holder.tvFabricacion.setText(vehiculos.get(position).getYearFab());
        holder.tvAdquirido.setText(vehiculos.get(position).getYearAdq());
        holder.iv_Vehiculo.setImageResource(R.mipmap.ic_launcher);
        holder.setListener(this);

    }

    @Override
    public int getItemCount() {
        return vehiculos.size();
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public static class VehiculoViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cvVehiculo;
        TextView tvPlaca;
        TextView tvDescripcion;
        TextView tvAsientos;
        TextView tvPeso;
        TextView tvEstado;
        TextView tvCarga;
        TextView tvFabricacion;
        TextView tvAdquirido;
        ImageView iv_Vehiculo;
        ImageButton btEditVehiculo;
        ImageButton btDeleteVehiculo;
        View.OnClickListener listener;

        public VehiculoViewHolder(View itemView) {
            super(itemView);
            cvVehiculo=(CardView)itemView.findViewById(R.id.cv_vehiculo);
            iv_Vehiculo=(ImageView)itemView.findViewById(R.id.iv_vehiculo);
            tvPlaca=(TextView)itemView.findViewById(R.id.tv_placa);
            tvDescripcion=(TextView)itemView.findViewById(R.id.tv_descripcion);
            tvAsientos=(TextView)itemView.findViewById(R.id.tv_asientos);
            tvPeso=(TextView)itemView.findViewById(R.id.tv_peso);
            tvEstado=(TextView)itemView.findViewById(R.id.tv_estado);
            tvCarga=(TextView)itemView.findViewById(R.id.tv_carga);
            tvFabricacion=(TextView)itemView.findViewById(R.id.tv_fabricacion);
            tvAdquirido=(TextView)itemView.findViewById(R.id.tv_aquirido);
            btEditVehiculo=(ImageButton) itemView.findViewById(R.id.bt_edit_vehiculo);
            btDeleteVehiculo=(ImageButton) itemView.findViewById(R.id.bt_delete_vehiculo);
            btEditVehiculo.setOnClickListener(this);
            btDeleteVehiculo.setOnClickListener(this);


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
