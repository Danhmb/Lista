package haddad.maia.barbosa.lista.activity;

//imports

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import haddad.maia.barbosa.lista.R;

public class MainActivity extends AppCompatActivity {
    //criação do identificador
    static int NEW_ITEM_REQUEST = 1;

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
    }
}