package com.example.webmaster.meteoro.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.webmaster.meteoro.R;
import com.example.webmaster.meteoro.bd.Cliente;
import com.example.webmaster.meteoro.bd.Produto;
import com.example.webmaster.meteoro.tarefas.ListagemClientes;
import com.example.webmaster.meteoro.tarefas.ListagemProdutos;
import com.example.webmaster.meteoro.tarefas.RemocaoCliente;
import com.example.webmaster.meteoro.tarefas.RemocaoProduto;

/**
 * Created by webmaster on 20/05/16.
 */
public class FragmentoListaProdutos extends Fragment {
    private static FragmentoListaProdutos instancia = null;

    public static FragmentoListaProdutos getInstancia() {
        if (instancia == null) {
            instancia = new FragmentoListaProdutos();
        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle) {
        tela = inflador.inflate(R.layout.fragmento_lista_produtos, vgrupo, false);
        preparar();
        return tela;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflador) {
        super.onCreateOptionsMenu(menu, inflador);
        inflador.inflate(R.menu.menu_controle_notas, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        long id = item.getItemId();
        if (id != AdapterView.INVALID_ROW_ID) {
            if (id == R.id.cadastro_remover_produto) {
                RemocaoProduto remocaoProduto = new RemocaoProduto(this.getContext(), this.getProdutoSelecionado());
                remocaoProduto.execute();
                atualizar();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void preparar() {
        lista = (ListView) tela.findViewById(R.id.listaProdutos);
        this.setHasOptionsMenu(true);
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void atualizar() {
        ListagemProdutos listagem = new ListagemProdutos(this.getContext(), lista);
        listagem.execute();
    }

    public Produto getProdutoSelecionado() {
        Produto produto = new Produto();
        int posicao = lista.getCheckedItemPosition();
        if (posicao != ListView.INVALID_POSITION) {
            produto = (Produto) lista.getItemAtPosition(posicao);
        }
        return produto;
    }
}
