package com.example.webmaster.meteoro.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.webmaster.meteoro.bd.Cliente;
import com.example.webmaster.meteoro.bd.FachadaClienteBD;

import java.util.List;

/**
 * Created by webmaster on 27/05/16.
 */
public class ListagemClientes extends AsyncTask<Void, Void, List<Cliente>> {
    private Context contexto = null;
    private ListView listaClientes = null;

    public ListagemClientes(Context contexto, ListView listaClientes) {
        this.contexto = contexto;
        this.listaClientes = listaClientes;
    }

    @Override
    protected List doInBackground(Void... params) {
        List<Cliente> clientes = FachadaClienteBD.getInstance().listarClientes();
        return clientes;
    }

    /*
    em background nao executa recursos graficos
    então so depois da execucacao do AsyncTest que
    pode ser exbido usando o metodo onPostExecute()
     */
    @Override
    protected void onPostExecute(List disciplina) {
        if (disciplina.isEmpty()) {
            Toast.makeText(contexto, "Nenhuma Disciplina Encontrada", Toast.LENGTH_LONG);
        } else {
            ArrayAdapter<Cliente> adaptador = new ArrayAdapter<Cliente>(
                    contexto,
                    android.R.layout.simple_list_item_single_choice,
                    disciplina
            );
            listaClientes.setAdapter(adaptador);
        }
    }
}
