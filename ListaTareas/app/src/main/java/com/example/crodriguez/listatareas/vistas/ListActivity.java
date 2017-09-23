package com.example.crodriguez.listatareas.vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crodriguez.listatareas.R;
import com.example.crodriguez.listatareas.modelos.Tarea;
import com.example.crodriguez.listatareas.vistas.adaptadores.TodoListAdapter;
import com.example.crodriguez.listatareas.vistas.presenters.IListPresenter;
import com.example.crodriguez.listatareas.vistas.presenters.ListPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class ListActivity extends AppCompatActivity implements IListView,TodoListAdapter.OnListenerItemCheck{

    private IListPresenter listPresenter;

    @BindView(R.id.rvListTODO)
    RecyclerView rvListTODO;

    @BindView(R.id.txtTarea)
    EditText txtTarea;

   // @BindView(R.id.chkTarea)
    // AppCompatCheckBox chkTarea;

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

        rvListTODO.setAdapter(new TodoListAdapter(lsTarea,this));
    }

    @OnClick(R.id.btnEnviarTarea)
    @Override
    public void clickAddTarea() {
        String descTarea = txtTarea.getText().toString();
        listPresenter.addTarea(descTarea);
    }

    @Override
    public void refrescarListaTareas() {
        rvListTODO.getAdapter().notifyDataSetChanged();
        rvListTODO.scrollToPosition(rvListTODO.getAdapter().getItemCount()-1);
        txtTarea.setText("");
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
