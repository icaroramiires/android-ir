package com.example.webmaster.meteoro.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.webmaster.meteoro.R;
import com.example.webmaster.meteoro.bd.Cliente;
import com.example.webmaster.meteoro.bd.Produto;
import com.example.webmaster.meteoro.tarefas.GravacaoCliente;
import com.example.webmaster.meteoro.tarefas.GravacaoProduto;

/**
 * Created by webmaster on 13/05/16.
 */
public class FragmentoCadastroProduto extends Fragment {

    private static FragmentoCadastroProduto instancia = null;

    public static FragmentoCadastroProduto getInstancia() {
        if (instancia == null) {
            instancia = new FragmentoCadastroProduto();
        }
        return instancia;
    }

    private View tela = null;
    private EditText nomeProduto = null;
    private EditText qntProduto = null;
    private EditText precoProduto = null;
    private Button btnGravar = null;
    private Produto produto = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgroup, Bundle bundle) {
        tela = inflador.inflate(R.layout.fragmento_cadastro_produto, vgroup, false);

        preparar();

        return tela;
    }

    private void preparar() {
        nomeProduto = (EditText) tela.findViewById(R.id.nomeProduto);
        qntProduto = (EditText) tela.findViewById(R.id.qntProduto);
        precoProduto = (EditText) tela.findViewById(R.id.precoProduto);
        btnGravar = (Button) tela.findViewById(R.id.btnGravar);

        btnGravar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GravacaoProduto gravacao = new GravacaoProduto(getContexto(), getProduto());
                        gravacao.execute();
                    }
                }
        );
    }

    private Context getContexto() {
        return this.getContext();
    }

    private Produto getProduto() {
        produto.setNome(nomeProduto.getText().toString());
        produto.setQnt(Integer.valueOf(qntProduto.getText().toString()));
        produto.setPreco(Double.valueOf(precoProduto.getText().toString()));

        return produto;
    }

    public void exibirProdutoSelecionado() {
        produto = FragmentoListaProdutos.getInstancia().getProdutoSelecionado();
        if (produto.getCodigo() == -1) {
            limparCampos();
        } else {
            carregarCampos();
        }
    }

    private void limparCampos() {
        nomeProduto.setText("");
        qntProduto.setText("0");
        precoProduto.setText("0.00");
    }

    private void carregarCampos() {
        nomeProduto.setText(produto.getNome());
        qntProduto.setText(produto.getQnt()+"");
        precoProduto.setText(produto.getPreco()+"");
    }
}