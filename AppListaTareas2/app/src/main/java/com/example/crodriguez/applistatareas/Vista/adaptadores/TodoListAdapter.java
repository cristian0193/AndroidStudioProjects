package com.example.crodriguez.applistatareas.Vista.adaptadores;

import android.graphics.Paint;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.crodriguez.applistatareas.R;
import com.example.crodriguez.applistatareas.Modelo.Tarea;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ItemTodoList>{

    private List<Tarea> dataset;
    private OnListenerItemCheck onListenerItemCheck;

    public TodoListAdapter(List<Tarea> dataset, OnListenerItemCheck onListenerItemCheck){
       super();
       this.dataset = dataset;
       this.onListenerItemCheck = onListenerItemCheck;
   }

    @Override
    public ItemTodoList onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ItemTodoList(view);
    }

    @Override
    public void onBindViewHolder(ItemTodoList holder, int position) {
        Tarea tarea = dataset.get(position);

        if(tarea.isRealizada()){
            holder.tvTarea.setPaintFlags(holder.tvTarea.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvFecha.setPaintFlags(holder.tvFecha.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            holder.tvTarea.setPaintFlags(holder.tvTarea.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvFecha.setPaintFlags(holder.tvFecha.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.tvTarea.setText(tarea.getNombre());
        holder.tvFecha.setText(tarea.getFecha());
        holder.chkTarea.setChecked(tarea.isRealizada());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ItemTodoList extends RecyclerView.ViewHolder{

        @BindView(R.id.chkTarea)
        AppCompatCheckBox chkTarea;

        @BindView(R.id.tvTarea)
        EditText tvTarea;

        @BindView(R.id.tvFecha)
        EditText tvFecha;

        public ItemTodoList(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            if (onListenerItemCheck != null){
                chkTarea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        onListenerItemCheck.itemChangedStatusClick(getAdapterPosition(),chkTarea.isChecked());

                    }
                });
            }



        }
    }

    public interface OnListenerItemCheck{

        void itemChangedStatusClick(int posicion, boolean estado);

    }

}
