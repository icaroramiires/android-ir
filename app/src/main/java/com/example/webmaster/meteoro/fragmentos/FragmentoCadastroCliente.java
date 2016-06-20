package com.example.webmaster.meteoro.fragmentos;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.webmaster.meteoro.R;
import com.example.webmaster.meteoro.bd.Cliente;
import com.example.webmaster.meteoro.tarefas.GravacaoCliente;

/**
 * Created by webmaster on 13/05/16.
 */
public class FragmentoCadastroCliente extends Fragment {

    private static FragmentoCadastroCliente instancia = null;

    public static FragmentoCadastroCliente getInstancia() {
        if (instancia == null) {
            instancia = new FragmentoCadastroCliente();
        }
        return instancia;
    }

    private View tela = null;
    private EditText nomeCliente = null;
    private EditText cidadeCliente = null;
    private EditText emailCliente = null;
    private EditText telefoneCliente = null;
    private Button btnGravar = null;
    private Cliente cliente = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgroup, Bundle bundle) {
        tela = inflador.inflate(R.layout.fragmento_cadastro_cliente, vgroup, false);

        preparar();

        return tela;
    }

    private void preparar() {
        nomeCliente = (EditText) tela.findViewById(R.id.nomeCliente);
        cidadeCliente = (EditText) tela.findViewById(R.id.cidadeCliente);
        emailCliente = (EditText) tela.findViewById(R.id.emailCliente);
        telefoneCliente = (EditText) tela.findViewById(R.id.telefoneCiente);
        btnGravar = (Button) tela.findViewById(R.id.btnGravar);

        btnGravar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GravacaoCliente gravacao = new GravacaoCliente(getContexto(), getCliente());
                        gravacao.execute();
                    }
                }
        );
    }

    private Context getContexto() {
        return this.getContext();
    }

    private Cliente getCliente() {
        cliente.setNome(nomeCliente.getText().toString());
        cliente.setCidade(cidadeCliente.getText().toString());
        cliente.setEmail(emailCliente.getText().toString());
        cliente.setTelefone(telefoneCliente.getText().toString());

        return cliente;
    }

    public void exibirClienteSelecionado() {
        cliente = FragmentoListaClientes.getInstancia().getClienteSelecionado();
        if (cliente.getCodigo() == -1) {
            limparCampos();
        } else {
            carregarCampos();
        }
    }

    private void limparCampos() {
        nomeCliente.setText("");
        cidadeCliente.setText("");
        emailCliente.setText("");
        telefoneCliente.setText("");
    }

    private void carregarCampos() {
        nomeCliente.setText(cliente.getNome());
        cidadeCliente.setText(cliente.getCidade());
        emailCliente.setText(cliente.getEmail());
        telefoneCliente.setText(cliente.getTelefone());
    }
}