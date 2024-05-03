package haddad.maia.barbosa.lista.activity;

//imports

import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
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
                startActivityForResult(photoPickerIntent,PHOTO_PICKER_REQUEST);
            }
        });

    }
}