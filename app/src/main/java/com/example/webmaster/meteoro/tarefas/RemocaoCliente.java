package com.example.webmaster.meteoro.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.webmaster.meteoro.bd.Cliente;
import com.example.webmaster.meteoro.bd.FachadaClienteBD;

/**
 * Created by webmaster on 27/05/16.
 */
public class RemocaoCliente extends AsyncTask<Void, Void, String> {
    private Cliente cliente = null;
    private Context context = null;
    public RemocaoCliente(Context contexto, Cliente cliente) {
        this.cliente = cliente;
        this.context = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if(cliente.getCodigo() != -1) {
            if(FachadaClienteBD.getInstance().remover(cliente) == 0) {
                mensagem = "Problemas na remoção!";
            } else {
                mensagem = "Cliente removido";
            }
        } else {
            mensagem = "Selecione uma cliente!";
        }
        return mensagem;
    }

    /*
    em background nao executa recursos graficos
    então so depois da execucacao do AsyncTest que
    pode ser exbido usando o metodo onPostExecute()
     */
    @Override
    protected void onPostExecute(String mensagem) {
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }
}
