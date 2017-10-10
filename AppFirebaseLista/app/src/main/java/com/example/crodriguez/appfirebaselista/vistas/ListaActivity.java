package com.example.crodriguez.appfirebaselista.vistas;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crodriguez.appfirebaselista.R;
import com.example.crodriguez.appfirebaselista.modelos.Tarea;
import com.example.crodriguez.appfirebaselista.vistas.adaptadores.TodoListAdapter;
import com.example.crodriguez.appfirebaselista.vistas.presenters.IListPresenter;
import com.example.crodriguez.appfirebaselista.vistas.presenters.ListPresenter;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListaActivity extends AppCompatActivity implements IListView,TodoListAdapter.OnListenerItemCheck{

    private IListPresenter listPresenter;

    @BindView(R.id.rvListTODO)
    RecyclerView rvListTODO;

    Calendar mCurrentDate;
    int day, month, year;

    public ListaActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        ButterKnife.bind(this);

        String user = getIntent().getExtras().getString("StringUser");
        getSupportActionBar().setTitle("user : " + user);

        listPresenter = new ListPresenter(this);
        listPresenter.obtenerTareasFirebase();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvListTODO.setLayoutManager(llm);

        List<Tarea> lsTarea = listPresenter.obtenerTareas();

        rvListTODO.setAdapter(new TodoListAdapter(lsTarea,this));

    }

    @OnClick(R.id.btnEnviarTareaModal)
    @Override
    public void clickAddTareaModal() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ListaActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_sigin, null);
        mBuilder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);
        mBuilder.setView(mView);

        final EditText etTarea = (EditText) mView.findViewById(R.id.etTarea);
        final EditText etFecha = (EditText) mView.findViewById(R.id.etFecha);
        Button btnCalendar = (Button) mView.findViewById(R.id.btnCalendario);

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentDate = Calendar.getInstance();
                day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
                month = mCurrentDate.get(Calendar.MONTH);
                year = mCurrentDate.get(Calendar.YEAR);

                month = month + 1;

                etFecha.setText(day + "/" + month + "/" + year);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ListaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        etFecha.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        mBuilder.setPositiveButton(R.string.Agregar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                String tareaIngresada = "";
                String fechaIngresada = "";

                tareaIngresada = etTarea.getText().toString();
                fechaIngresada = etFecha.getText().toString();

                if (!tareaIngresada.equals("") && !fechaIngresada.equals("")) {
                    listPresenter.addTarea(tareaIngresada, fechaIngresada);
                    Toast.makeText(getApplicationContext(), R.string.TareaRegistrada, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.CamposVaciosTarea, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBuilder.create();
        mBuilder.show();

    }

    @Override
    public void refrescarListaTareas() {
        rvListTODO.getAdapter().notifyDataSetChanged();
        rvListTODO.scrollToPosition(rvListTODO.getAdapter().getItemCount()-1);
    }

    @Override
    public void refrescarTarea(int posicion) {
        rvListTODO.getAdapter().notifyItemChanged(posicion);
    }

    @Override
    public void itemChangedStatusClick(int posicion, boolean estado) {
        listPresenter.itemCambioEstado(posicion,estado);
    }


}
