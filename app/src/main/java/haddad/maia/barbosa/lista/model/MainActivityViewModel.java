package haddad.maia.barbosa.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

//o MainActivityViewModel guarda a lista de itens cadastrados
public class MainActivityViewModel extends ViewModel {
    List<MyItem> itens = new ArrayList<>();

    //obtém a lista de itens
    public List<MyItem> getItens(){
        return itens;
    }
}
