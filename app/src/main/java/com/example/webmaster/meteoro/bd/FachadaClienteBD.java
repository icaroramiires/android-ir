package com.example.webmaster.meteoro.bd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FachadaClienteBD extends SQLiteOpenHelper {

    private static FachadaClienteBD instancia = null;

    private static String NOME_BANCO = "MeteoroClienteBD";
    private static int VERSAO_BANCO = 1;

    public static FachadaClienteBD criarInstancia(Context context) {
        if (instancia == null) {
            instancia = new FachadaClienteBD(context);
        }
        return instancia;
    }

    public static FachadaClienteBD getInstance() {
        return instancia;
    }

    public FachadaClienteBD(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    private static String COMANDO_CRIACAO_TABELA_CLIENTES =
            "CREATE TABLE CLIENTES(" +
                    "CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NOME TEXT, CIDADE TEXT, EMAIL TEXT, TELEFONE TEXT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMANDO_CRIACAO_TABELA_CLIENTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        // TODO Auto-generated method stub
    }

    // metodos de criacao de um CRUD utilizando o SQLite

    public long inserir(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase(); // escreve
        ContentValues valores = new ContentValues();

        valores.put("NOME", cliente.getNome());
        valores.put("CIDADE", cliente.getCidade());
        valores.put("EMAIL", cliente.getEmail());
        valores.put("TELEFONE", cliente.getTelefone());

        long codigo = db.insert("CLIENTES", null, valores);
        return codigo;
    }

    public long atualizar(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", cliente.getNome());
        valores.put("CIDADE", cliente.getCidade());
        valores.put("EMAIL", cliente.getEmail());
        valores.put("TELEFONE", cliente.getTelefone());

        long codigo = db.update("CLIENTES", valores, "CODIGO = " + cliente.getCodigo(), null);
        return codigo;
    }

    public int remover(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("CLIENTES", "CODIGO = " + cliente.getCodigo(), null);
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selecao = "SELECT CODIGO, NOME, CIDADE, EMAIL, TELEFONE FROM CLIENTES";

        Cursor cursor = db.rawQuery(selecao, null); // executa o comando sql
        if (cursor != null) {
            boolean temProximo = cursor.moveToFirst(); // mover o cursor para o primeiro registro
            while (temProximo) {

                Cliente cliente = new Cliente();
                cliente.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                cliente.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                cliente.setCidade(cursor.getString(cursor.getColumnIndex("CIDADE")));
                cliente.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
                cliente.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));

                clientes.add(cliente);
                temProximo = cursor.moveToNext();
            }
        }
        return clientes;
    }
}