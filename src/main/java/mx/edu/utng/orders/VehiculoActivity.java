package mx.edu.utng.orders;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.orders.adapters.VehiculoAdapter;
import mx.edu.utng.orders.model.Vehiculo;
import mx.edu.utng.orders.sqlite.DBOperations;

/**
 * Created by Karla on 27/02/2017.
 */

public class VehiculoActivity extends AppCompatActivity {
    private EditText etNroPlaca;
    private EditText etDescipcion;
    private EditText etNumAsientos;
    private EditText etPeso;
    private EditText etEstado;
    private EditText etCargaMax;
    private EditText etFabricacion;
    private EditText etAdquirido;
    private Button btSaveVehiculo;
    private DBOperations operations;
    private Vehiculo vehiculo = new Vehiculo();
    private RecyclerView rvVehiculo;
    private List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
    private VehiculoAdapter adapterVehiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculo);

        operations = DBOperations.getDBOperations(getApplicationContext());
        vehiculo.setIdVehiculo("");

        initComponents();
    }

    private void initComponents(){
        rvVehiculo=(RecyclerView)findViewById(R.id.rv_vehiculo_list);
        rvVehiculo.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvVehiculo.setLayoutManager(layoutManager);

        getVehiculos();
        adapterVehiculo = new VehiculoAdapter(vehiculos);

        adapterVehiculo.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bt_delete_vehiculo:
                        operations.deleteVehiculo(vehiculos.get(rvVehiculo.getChildPosition((View)v.getParent().getParent())).getIdVehiculo());
                        getVehiculos();
                        adapterVehiculo.notifyDataSetChanged();
                        break;

                    case  R.id.bt_edit_vehiculo:

                        Toast.makeText(getApplicationContext(),"Editar",Toast.LENGTH_SHORT).show();
                        Cursor c = operations.getVehiculoById(vehiculos.get(
                                rvVehiculo.getChildPosition((View)v.getParent().getParent()
                        )).getIdVehiculo());

                        if(c!=null){
                            if(c.moveToFirst()){
                                vehiculo = new Vehiculo(c.getString(1),c.getString(2),
                                        c.getString(3),c.getString(4),c.getString(5),
                                        c.getString(6),c.getString(7),c.getInt(8),
                                        c.getInt(9));
                            }

                            etNroPlaca.setText(vehiculo.getNroPlaca());
                            etDescipcion.setText(vehiculo.getDescripcion());
                            etNumAsientos.setText(vehiculo.getNumAsientos());
                            etPeso.setText(vehiculo.getPesoVeh());
                            etEstado.setText(vehiculo.getEstadoVeh());
                            etCargaMax.setText(vehiculo.getCargaMax());
                            etFabricacion.setText(String.valueOf(vehiculo.getYearFab()));
                            etAdquirido.setText(String.valueOf(vehiculo.getYearAdq()));
                        }else{
                            Toast.makeText(getApplicationContext(),"Registro no encontrado",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });

        rvVehiculo.setAdapter(adapterVehiculo);

        etNroPlaca=(EditText)findViewById(R.id.et_nro_placa);
        etDescipcion=(EditText)findViewById(R.id.et_descripcion_vehiculo);
        etNumAsientos=(EditText)findViewById(R.id.et_num_asiento);
        etPeso=(EditText)findViewById(R.id.et_peso);
        etEstado=(EditText)findViewById(R.id.et_estado);
        etCargaMax=(EditText)findViewById(R.id.et_carga);
        etFabricacion=(EditText)findViewById(R.id.et_anioFab);
        etAdquirido=(EditText)findViewById(R.id.et_anioAdq);

        btSaveVehiculo = (Button)findViewById(R.id.bt_save_veh);
        btSaveVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!vehiculo.getIdVehiculo().equals("")){
                    vehiculo.setNroPlaca(etNroPlaca.getText().toString());
                    vehiculo.setDescripcion(etDescipcion.getText().toString());
                    vehiculo.setNumAsientos(etNumAsientos.getText().toString());
                    vehiculo.setPesoVeh(etPeso.getText().toString());
                    vehiculo.setEstadoVeh(etEstado.getText().toString());
                    vehiculo.setCargaMax(etCargaMax.getText().toString());
                    vehiculo.setYearFab(Integer.parseInt(etFabricacion.getText().toString()));
                    vehiculo.setYearAdq(Integer.parseInt(etAdquirido.getText().toString()));
                    operations.updateVehiculo(vehiculo);

                }else {
                    vehiculo = new Vehiculo("", etNroPlaca.getText().toString(),etDescipcion.getText().toString(),etNumAsientos.getText().toString(), etPeso.getText().toString(),etEstado.getText().toString(), etCargaMax.getText().toString(),
                            Integer.parseInt(etFabricacion.getText().toString()),
                            Integer.parseInt(etAdquirido.getText().toString()));
                    operations.insertVehiculo(vehiculo);
                }
                getVehiculos();
                clearData();
                adapterVehiculo.notifyDataSetChanged();
            }
        });

    }

    private void getVehiculos(){
        Cursor c = operations.getVehiculos();
        vehiculos.clear();
        if(c!=null){
            while (c.moveToNext()){
                vehiculos.add(new Vehiculo(c.getString(1), c.getString(2), c.getString(3),
                        c.getString(4), c.getString(5), c.getString(6),
                        c.getString(7), c.getInt(8), c.getInt(9)));
            }
        }
    }

    private void clearData(){
        etNroPlaca.setText("");
        etDescipcion.setText("");
        etNumAsientos.setText("");
        etPeso.setText("");
        etEstado.setText("");
        etCargaMax.setText("");
        etFabricacion.setText("");
        etAdquirido.setText("");
        vehiculo = null;
        vehiculo = new Vehiculo();
        vehiculo.setIdVehiculo("");
    }

}


















