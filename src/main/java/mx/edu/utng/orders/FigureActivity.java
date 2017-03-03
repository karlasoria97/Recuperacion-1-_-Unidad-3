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

import mx.edu.utng.orders.adapters.FigureAdapter;
import mx.edu.utng.orders.model.Figure;
import mx.edu.utng.orders.sqlite.DBOperations;

/**
 * Created by Karla on 02/03/2017.
 */

public class FigureActivity extends AppCompatActivity {

    private EditText etTeo;
    private EditText etCode;
    private EditText etDrawer;
    private EditText etFigure;
    private EditText etUser;
    private EditText etBibref;
    private EditText etDateSubmission;
    private Button btSaveFigure;
    private DBOperations operations;
    private Figure figure = new Figure();
    private RecyclerView rvFigures;
    private List<Figure> figures = new ArrayList<Figure>();
    private FigureAdapter adapterFigure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figure);

        operations = DBOperations.getDBOperations(getApplicationContext());
        figure.setIdFigure("");

        initComponents();
    }

    private void initComponents(){
        etTeo=(EditText)findViewById(R.id.et_teo);
        etCode=(EditText)findViewById(R.id.et_code);
        etDrawer=(EditText)findViewById(R.id.et_drawer);
        etFigure=(EditText)findViewById(R.id.et_figure);
        etUser=(EditText)findViewById(R.id.et_user);
        etBibref=(EditText)findViewById(R.id.et_bibref);
        etDateSubmission=(EditText)findViewById(R.id.et_date);

        rvFigures=(RecyclerView)findViewById(R.id.rv_figure_list);
        rvFigures.setHasFixedSize(true);
        LinearLayoutManager layoutManeger=new LinearLayoutManager(this);
        rvFigures.setLayoutManager(layoutManeger);
        //
        getFigures();
        adapterFigure=new FigureAdapter(figures);

        adapterFigure.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bt_delete_figure:
                        operations.deleteFigure(figures.get
                                (rvFigures.getChildPosition((View)v.getParent().getParent())).getIdFigure());
                        getFigures();
                        adapterFigure.notifyDataSetChanged();
                        break;
                    case R.id.bt_edit_figure:

                        Toast.makeText(getApplicationContext(),
                                figures.get(rvFigures.getChildPosition((View)v.getParent().getParent())).getIdFigure(), Toast.LENGTH_SHORT).show();
                        Cursor c = operations.getFigureById(figures.get(
                                rvFigures.getChildPosition(
                                        (View)v.getParent().getParent())).getIdFigure());
                        if (c!=null){
                            if (c.moveToFirst()){
                                figure = new Figure(c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8));
                                Log.i("figure", figure.toString());
                            }
                            etTeo.setText(figure.getTeo());
                            etCode.setText(figure.getCode());
                            etDrawer.setText(figure.getDrawer());
                            etFigure.setText(figure.getFigure());
                            etUser.setText(figure.getUser());
                            etBibref.setText(figure.getBibref());
                            etDateSubmission.setText(figure.getDateSubmission());


                        }else{
                            Toast.makeText(getApplicationContext(),"Registro no encontrado",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        });
        rvFigures.setAdapter(adapterFigure);

        etTeo=(EditText)findViewById(R.id.et_teo);
        etCode=(EditText)findViewById(R.id.et_code);
        etDrawer=(EditText)findViewById(R.id.et_drawer);
        etFigure=(EditText)findViewById(R.id.et_figure);
        etUser=(EditText)findViewById(R.id.et_user);
        etBibref=(EditText)findViewById(R.id.et_bibref);
        etDateSubmission=(EditText)findViewById(R.id.et_date);


        btSaveFigure=(Button)findViewById(R.id.bt_save_figure);

        btSaveFigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!figure.getIdFigure().equals("")){
                    figure.setTeo(etTeo.getText().toString());
                    figure.setCode(etCode.getText().toString());
                    figure.setDrawer(etDrawer.getText().toString());
                    figure.setFigure(etFigure.getText().toString());
                    figure.setUser(etUser.getText().toString());
                    figure.setBibref(etBibref.getText().toString());
                    figure.setDateSubmission(etDateSubmission.getText().toString());
                    operations.updateFigure(figure);

                }else {
                    figure = new Figure("", etTeo.getText().toString(),etCode.getText().toString(),
                            etDrawer.getText().toString(), etFigure.getText().toString(),
                            etUser.getText().toString(), etBibref.getText().toString(),
                            etDateSubmission.getText().toString());
                    operations.insertFigure(figure);
                }
                getFigures();
                clearData();
                adapterFigure.notifyDataSetChanged();
            }
        });

    }
    private void getFigures(){
        Cursor c =operations.getFigures();
        figures.clear();
        if(c!=null){
            while (c.moveToNext()){
                figures.add(new Figure(c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8)));
            }
        }

    }

    private void clearData(){
        etTeo.setText("");
        etCode.setText("");
        etDrawer.setText("");
        etFigure.setText("");
        etUser.setText("");
        etBibref.setText("");
        etDateSubmission.setText("");
        figure=null;
        figure= new Figure();
        figure.setIdFigure("");
    }
}
