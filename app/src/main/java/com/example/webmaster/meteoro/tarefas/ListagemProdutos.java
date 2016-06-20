package com.example.webmaster.meteoro.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.webmaster.meteoro.bd.Cliente;
import com.example.webmaster.meteoro.bd.FachadaClienteBD;
import com.example.webmaster.meteoro.bd.FachadaProdutoBD;
import com.example.webmaster.meteoro.bd.Produto;

import java.util.List;

/**
 * Created by webmaster on 27/05/16.
 */
public class ListagemProdutos extends AsyncTask<Void, Void, List<Produto>> {
    private Context contexto = null;
    private ListView listaProdutos = null;

    public ListagemProdutos(Context contexto, ListView listaProdutos) {
        this.contexto = contexto;
        this.listaProdutos = listaProdutos;
    }

    @Override
    protected List doInBackground(Void... params) {
        List<Produto> produtos = FachadaProdutoBD.getInstance().listarProdutos();
        return produtos;
    }

    /*
    em background nao executa recursos graficos
    então so depois da execucacao do AsyncTest que
    pode ser exbido usando o metodo onPostExecute()
     */
    @Override
    protected void onPostExecute(List produtos) {
        if (produtos.isEmpty()) {
            Toast.makeText(contexto, "Nenhum produto Encontrado", Toast.LENGTH_LONG);
        } else {
            ArrayAdapter<Produto> adaptador = new ArrayAdapter<Produto>(
                    contexto,
                    android.R.layout.simple_list_item_single_choice,
                    produtos
            );
            listaProdutos.setAdapter(adaptador);
        }
    }
}
