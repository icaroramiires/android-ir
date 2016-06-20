package com.example.webmaster.meteoro.fragmentos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.webmaster.meteoro.R;

/**
 * Created by webmaster on 13/05/16.
 */
public class FragmentoInformacao extends Fragment {
    private static FragmentoInformacao instancia = null;

    public static FragmentoInformacao getInstancia() {
        if (instancia == null) {
            instancia = new FragmentoInformacao();
        }
        return instancia;
    }

    private View tela = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgroup, Bundle bundle) {
        tela = inflador.inflate(R.layout.fragmento_informacao, vgroup, false);
        return tela;
    }
}
