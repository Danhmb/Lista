package haddad.maia.barbosa.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import java.net.URI;

public class NewItemActivityViewModel extends ViewModel{

    //guarda somente a URI da foto para que mesmo que a tela seja rotacionada ela não suma
    Uri selectedPhotoLocation = null;

    //obtém itens da lista
    public Uri getSelectedPhotoLocation() {
        return selectedPhotoLocation;
    }

    //seta o URI dentro do ViewModel
    public void setSelectedPhotoLocation(Uri selectedPhotoLocation) {
        this.selectedPhotoLocation = selectedPhotoLocation;
    }
}
