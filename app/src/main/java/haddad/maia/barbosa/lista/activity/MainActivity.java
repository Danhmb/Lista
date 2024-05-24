package haddad.maia.barbosa.lista.activity;

//imports

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import haddad.maia.barbosa.lista.R;
import haddad.maia.barbosa.lista.adapter.MyAdapter;
import haddad.maia.barbosa.lista.model.MainActivityViewModel;
import haddad.maia.barbosa.lista.model.MyItem;
import haddad.maia.barbosa.lista.util.Util;

public class MainActivity extends AppCompatActivity {
    //criação do identificador
    static int NEW_ITEM_REQUEST = 1;

    MyAdapter myAdapter;

    //cria elementos da tela
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)  ;
            return insets;
        });
        //obtém o botão fab pelo seu respectivo id
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);

        //Cria evento quando o botão "fabAddItem" é clicado
        fabAddItem.setOnClickListener(new View.OnClickListener() {

            //quando o botão for clicado -> cria uma intenção para a navegação entre activities
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this,NewItemActivity.class);

                    //assume que o destino vai retornar algum dado em determinado momento para quem a inciou
                    startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

        //pega o RecycleView pelo seu id
        RecyclerView rvItens = findViewById(R.id.rvItens);

        //puxa o view modew referente a MainActivityViewModel
        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);

        //pega a lista de itens dentro do view model
        List<MyItem> itens = vm.getItens();

        //Cria o adapter passando o a tela princiapl e os itens(lista)
        myAdapter = new MyAdapter(this,itens);

        //seta o adapter
        rvItens.setAdapter(myAdapter);

        //indica que não há variação de tamanho entre os itens
        rvItens.setHasFixedSize(true);

        //criação de um layout linear
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        //aqui o layput é setado no RecycleView
        rvItens.setLayoutManager(layoutManager);

        //cria um "separador" de itens por linhas
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);

        //o separador é setadp no RecycleView
        rvItens.addItemDecoration(dividerItemDecoration);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST){
            if (resultCode == Activity.RESULT_OK){


                //cria um item para guardar seus respectivos dados
                MyItem myitem = new MyItem();

                //guarda os dados vindo de NewItemActivity e,logo após, adicionamos no array
                myitem.title = data.getStringExtra("title");
                myitem.desc = data.getStringExtra("description");

                Uri selectedPhotoUri = data.getData();
                try{
                    //carrega a imagem e guarda no Bitmap(assim é criada a cópia dela)
                    Bitmap photo = Util.getBitmap(MainActivity.this,selectedPhotoUri,100,100);

                    //guarda o Bitmap dentro de um objeto MyItem
                    myitem.photo = photo;

                    //caso o arquivo de imagem não seja encontrado ativa a exceção
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }

                //puxa o view modew referente a MainActivityViewModel
                MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);

                //pega os itens da lista do view model
                List<MyItem> itens = vm.getItens();

                itens.add(myitem);

                //Adapter é notificado para atualizar RecycleView e exibir um novo item um novo item.
                myAdapter.notifyItemInserted(itens.size()-1);

            }
        }

    }

}