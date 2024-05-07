package haddad.maia.barbosa.lista.activity;

//imports

import android.app.Activity;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import haddad.maia.barbosa.lista.R;

public class NewItemActivity extends AppCompatActivity {
    //criação de identificador
    static int PHOTO_PICKER_REQUEST = 1;

    //guarda o endereço da foto selecionada pelo usuário
    Uri photoSelected = null;

    //cria elementos da tela
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //pega o botão da imagem pelo id
        ImageButton imgCI = findViewById(R.id.imbCI);

        //Cria evento quando o botão de imagem "imgCI" é clicado
        imgCI.setOnClickListener(new View.OnClickListener() {

            //quando o botão for clicado -> cria uma intenção e abre a galeria para escolha da foto

            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*"); //seta o tipo para qualquer tipo de imagem

                //executa o intent
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });

        Button btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //verifica se a foto selecionada é vazia
                if (photoSelected == null) {

                    //manda um alerta para o usuário selecionar uma imagem
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();

                    //fecha a função
                    return;
                }

                EditText etTitle = findViewById(R.id.etTitle);

                //pega o texto do editText e converte para string
                String title = etTitle.getText().toString();

                //verifica se o titúlo está vazio
                if (title.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDesc = findViewById(R.id.etDesc);
                String desc = etDesc.getText().toString();
                if (desc.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                //cria um intent para retornar dados
                Intent i = new Intent();

                //seta o endereço(Uri) da imagem no intent
                i.setData(photoSelected);

                //seta o título e a descrição no intent
                i.putExtra("title", title);
                i.putExtra("description", desc);

                //indicia o resultado da activity
                setResult(Activity.RESULT_OK, i);

                //finaliza a activity
                finish();
            };
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        //verifica se o request code(inteiro que mostra qual foi a chamada da startActivityForResult [nesse caso veio do MainActivity])
        if (requestCode == PHOTO_PICKER_REQUEST){

            //Verifica se a activity foi retornada com sucesso
            if (resultCode == Activity.RESULT_OK){

                //armazena a Uri da image ou seja, o endereço da imagem
                photoSelected = data.getData();

                //pega a imageView
                ImageView imvPhotoPreview =  findViewById(R.id.imvPhotoPreview);

                //aqui é setado a uri na imvPhotoPreview
                imvPhotoPreview.setImageURI(photoSelected);
            }
        }
    }
}
