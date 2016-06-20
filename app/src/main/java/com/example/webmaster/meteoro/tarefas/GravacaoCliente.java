package com.example.webmaster.meteoro.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.webmaster.meteoro.bd.Cliente;
import com.example.webmaster.meteoro.bd.FachadaClienteBD;

/**
 * Created by webmaster on 27/05/16.
 */
public class GravacaoCliente extends AsyncTask<Void, Void, String> {
    private Cliente cliente= null;
    private Context context = null;

    public GravacaoCliente(Context contexto, Cliente cliente) {
        this.cliente = cliente;
        this.context = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo = -1;
        if(cliente.getCodigo() == -1) {
            codigo = FachadaClienteBD.getInstance().inserir(cliente);
        } else {
            codigo = FachadaClienteBD.getInstance().atualizar(cliente);
        }

        if (codigo > 0) {
            mensagem = "Cliente gravado com sucesso!";
        } else {
            mensagem = "Erro ao gravar cliente!";
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
