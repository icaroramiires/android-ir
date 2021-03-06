package com.example.webmaster.meteoro.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.webmaster.meteoro.bd.Cliente;
import com.example.webmaster.meteoro.bd.FachadaClienteBD;
import com.example.webmaster.meteoro.bd.FachadaProdutoBD;
import com.example.webmaster.meteoro.bd.Produto;

/**
 * Created by webmaster on 27/05/16.
 */
public class GravacaoProduto extends AsyncTask<Void, Void, String> {
    private Produto produto = null;
    private Context context = null;

    public GravacaoProduto(Context contexto, Produto produto) {
        this.produto = produto;
        this.context = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo = -1;
        if(produto.getCodigo() == -1) {
            codigo = FachadaProdutoBD.getInstance().inserir(produto);
        } else {
            codigo = FachadaProdutoBD.getInstance().atualizar(produto);
        }

        if (codigo > 0) {
            mensagem = "Produto gravado com sucesso!";
        } else {
            mensagem = "Erro ao gravar produto!";
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
