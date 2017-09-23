package com.example.crodriguez.applistatareas.Vista;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import com.example.crodriguez.applistatareas.Modelo.Tarea;
import com.example.crodriguez.applistatareas.R;
import com.example.crodriguez.applistatareas.Vista.adaptadores.TodoListAdapter;
import com.example.crodriguez.applistatareas.Vista.presenters.IListPresenter;
import com.example.crodriguez.applistatareas.Vista.presenters.ListPresenter;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListaTareasActivity extends AppCompatActivity implements IListView, TodoListAdapter.OnListenerItemCheck {

    private IListPresenter listPresenter;

    @BindView(R.id.rvListTODO)
    RecyclerView rvListTODO;

   /* @BindView(R.id.etTarea)
    EditText etTarea;

    @BindView(R.id.etFecha)
    EditText etFecha;*/

   /* @BindView(R.id.btnCalendario)
    Button btnCalendario;*/

   /* @BindView(R.id.btnRegistrarTarea)
    Button btnRegistrarTarea;*/

    Calendar mCurrentDate;
    int day, month, year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);
        listPresenter = new ListPresenter(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvListTODO.setLayoutManager(llm);

        List<Tarea> lsTarea = listPresenter.obtenerTareas();
        rvListTODO.setAdapter(new TodoListAdapter(lsTarea, this));

          }

    @OnClick(R.id.btnMostrarModal)
    @Override
    public void clickMostarModal() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ListaTareasActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_sigin, null);
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();

        /*btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentDate = Calendar.getInstance();
                day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
                month = mCurrentDate.get(Calendar.MONTH);
                year = mCurrentDate.get(Calendar.YEAR);

                month = month + 1;

                etFecha.setText(day + "/" + month + "/" + year);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ListaTareasActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        etFecha.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnRegistrarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etTarea.getText().equals("") && !etFecha.getText().equals("")){

                    String tareaIngresada = etTarea.getText().toString();
                    String fechaIngresada = etFecha.getText().toString();

                    listPresenter.addTarea(tareaIngresada,fechaIngresada);

                    Toast.makeText(getApplicationContext(),R.string.TareaRegistrada,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),R.string.CamposVaciosTarea,Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }

   /* @OnClick(R.id.btnRegistrarTarea)
    public void registrarTarea(){
        if(!etTarea.getText().equals("") && !etFecha.getText().equals("")){

            String tareaIngresada = etTarea.getText().toString();
            String fechaIngresada = etFecha.getText().toString();

            listPresenter.addTarea(tareaIngresada,fechaIngresada);

            Toast.makeText(getApplicationContext(),R.string.TareaRegistrada,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),R.string.CamposVaciosTarea,Toast.LENGTH_SHORT).show();
        }
    }*/

    @Override
    public void refrescarListaTareas() {
        rvListTODO.getAdapter().notifyDataSetChanged();
        rvListTODO.scrollToPosition(rvListTODO.getAdapter().getItemCount() - 1);
    }

    @Override
    public void refrescarTarea(int posicion) {
        rvListTODO.getAdapter().notifyItemChanged(posicion);
    }

    @Override
    public void itemChangedStatusClick(int posicion, boolean estado) {
        listPresenter.itemCambioEstado(posicion, estado);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
