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
import mx.edu.utng.orders.model.Figure;

/**
 * Created by Karla on 02/03/2017.
 */

public class FigureAdapter extends RecyclerView.Adapter<FigureAdapter.FigureViewHolder>
        implements View.OnClickListener{

    List<Figure> figures;
    View.OnClickListener listener;
    //Constructor
    public FigureAdapter(List<Figure> figures){
        this.figures=figures;
    }
    //getter and setter de listener
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public FigureAdapter.FigureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_figure,parent,false);
        FigureAdapter.FigureViewHolder holder=new FigureAdapter.FigureViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(FigureAdapter.FigureViewHolder holder, int position) {
        holder.tvTeo.setText(figures.get(position).getTeo());
        holder.tvCode.setText(figures.get(position).getCode());
        holder.iv_Figure.setImageResource(R.mipmap.ic_launcher);
        holder.setListener(this);

    }

    @Override
    public int getItemCount() {
        return figures.size();
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public static class FigureViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cvFigure;
        TextView tvTeo;
        TextView tvCode;
        TextView tvDrawer;
        TextView tvFigure;
        TextView tvUser;
        TextView tvBibref;
        TextView tvDate;
        ImageView iv_Figure;
        ImageButton btEditFigure;
        ImageButton btDeleteFigure;
        View.OnClickListener listener;

        public FigureViewHolder(View itemView) {
            super(itemView);
            cvFigure=(CardView)itemView.findViewById(R.id.cv_figure);
            iv_Figure=(ImageView)itemView.findViewById(R.id.iv_figure);
            tvTeo=(TextView)itemView.findViewById(R.id.tv_teo);
            tvCode=(TextView)itemView.findViewById(R.id.tv_code);
            btEditFigure=(ImageButton) itemView.findViewById(R.id.bt_edit_figure);
            btDeleteFigure=(ImageButton) itemView.findViewById(R.id.bt_delete_figure);
            btEditFigure.setOnClickListener(this);
            btDeleteFigure.setOnClickListener(this);


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
