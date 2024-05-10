package haddad.maia.barbosa.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import haddad.maia.barbosa.lista.R;
import haddad.maia.barbosa.lista.activity.MainActivity;
import haddad.maia.barbosa.lista.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> itens;

    //construtor
    public MyAdapter(MainActivity mainActivity, List<MyItem> itens){
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //pega o item pela sua posição
        MyItem myItem = itens.get(position);

        //pega o objeto de tipagem View dentro do ViewHolder
        View v = holder.itemView;

        //pega a imagem dentro do objeto view pelo seu respectivo id
        ImageView imvphoto = v.findViewById(R.id.imvPhoto);
        imvphoto.setImageURI(myItem.photo);

        //pega o titulo dentro do objeto view pelo seu respectivo id
        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        //pega a descrição dentro do objeto view pelo seu respectivo id
        TextView tvdesc = v.findViewById(R.id.tvDesc);
        tvdesc.setText(myItem.desc);
    }

    @Override
    public int getItemCount() {
        //pega o tamanho da lista
        return itens.size();
    }
}
