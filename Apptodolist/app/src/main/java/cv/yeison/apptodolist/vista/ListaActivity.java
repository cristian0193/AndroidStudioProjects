package cv.yeison.apptodolist.vista;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cv.yeison.apptodolist.R;
import cv.yeison.apptodolist.modelo.Tarea;
import cv.yeison.apptodolist.vista.adaptadores.TodoListAdapter;
import cv.yeison.apptodolist.vista.presenters.IListPresenter;
import cv.yeison.apptodolist.vista.presenters.ListPresenter;

public class ListaActivity extends AppCompatActivity implements IListView, TodoListAdapter.OnListenerItemCheck {

    private IListPresenter listPresenter;

    private TodoListAdapter adapter;

    @BindView(R.id.rvListTODO)
    RecyclerView rvListTODO;

    Calendar date;
    int day, month, year;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);


        ButterKnife.bind(this);
        listPresenter = new ListPresenter(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvListTODO.setLayoutManager(llm);

        List<Tarea> lsTarea = listPresenter.obtenerTareas();

        adapter = new TodoListAdapter(lsTarea, this);

        rvListTODO.setAdapter(adapter);
    }

    @OnClick(R.id.btnEnviarTarea)
    @Override
    public void clickAddTarea() {

        final AlertDialog.Builder Builder = new AlertDialog.Builder(ListaActivity.this);
        View view = getLayoutInflater().inflate(R.layout.popup_tarea, null);
        Builder.setTitle("Agregar Tarea")
                .setMessage("Dale nombre a la tarea y selecciona una fecha para terminarla.");
        Builder.setView(view);

        final EditText tareaPopUp = (EditText) view.findViewById(R.id.tvTareaPopup);
        final EditText fechaPopUp = (EditText) view.findViewById(R.id.tvFechaPopup);
        Button btnCalendario = (Button) view.findViewById(R.id.btnCalendario);


        Builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tarea = tareaPopUp.getText().toString();
                String fecha = fechaPopUp.getText().toString();
                listPresenter.addTarea(tarea,fecha);
            }
        });

        Builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });


        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                date = Calendar.getInstance();
                day = date.get(Calendar.DAY_OF_MONTH);
                month = date.get(Calendar.MONTH);
                year = date.get(Calendar.YEAR);


                DatePickerDialog datePickerDialog = new DatePickerDialog(ListaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        fechaPopUp.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                    }
                }, year, month, day);

                datePickerDialog.show();


            }
        });

        Builder.create();
        Builder.show();


    }

    @Override
    public void refrescarListaTareas(List<Tarea> lstTarea) {

        adapter.setDataset(lstTarea);
        rvListTODO.getAdapter().notifyDataSetChanged();
        rvListTODO.scrollToPosition(rvListTODO.getAdapter().getItemCount()-1);
    }

    @Override
    public void refrescarTarea(Tarea tarea, int posicion) {
        adapter.setItemDataset(tarea,posicion);
        rvListTODO.getAdapter().notifyItemChanged(posicion);
    }


    @Override
    public void itemChangedStatusClick(int posicion, boolean estado) {
        listPresenter.itemCambioEstado(posicion,estado);
    }



}
