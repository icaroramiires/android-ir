package com.example.webmaster.meteoro.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FachadaProdutoBD extends SQLiteOpenHelper {

    private static FachadaProdutoBD instancia = null;

    private static String NOME_BANCO = "MeteoroProdutoBD";
    private static int VERSAO_BANCO = 1;

    public static FachadaProdutoBD criarInstancia(Context context) {
        if (instancia == null) {
            instancia = new FachadaProdutoBD(context);
        }
        return instancia;
    }

    public static FachadaProdutoBD getInstance() {
        return instancia;
    }

    public FachadaProdutoBD(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    private static String COMANDO_CRIACAO_TABELA_PRODUTOS =
            "CREATE TABLE PRODUTOS(" +
                    "CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NOME TEXT, QNT INTEGER, PRECO REAL)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMANDO_CRIACAO_TABELA_PRODUTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        // TODO Auto-generated method stub
    }

    // metodos de criacao de um CRUD utilizando o SQLite

    public long inserir(Produto produto) {
        SQLiteDatabase db = this.getWritableDatabase(); // escreve
        ContentValues valores = new ContentValues();

        valores.put("NOME", produto.getNome());
        valores.put("QNT", produto.getQnt());
        valores.put("PRECO", produto.getPreco());

        long codigo = db.insert("PRODUTOS", null, valores);
        return codigo;
    }

    public long atualizar(Produto produto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", produto.getNome());
        valores.put("QNT", produto.getQnt());
        valores.put("PRECO", produto.getPreco());

        long codigo = db.update("PRODUTOS", valores, "CODIGO = " + produto.getCodigo(), null);
        return codigo;
    }

    public int remover(Produto produto) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("PRODUTOS", "CODIGO = " + produto.getCodigo(), null);
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<Produto>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selecao = "SELECT CODIGO, NOME, QNT, PRECO FROM PRODUTOS";

        Cursor cursor = db.rawQuery(selecao, null); // executa o comando sql
        if (cursor != null) {
            boolean temProximo = cursor.moveToFirst(); // mover o cursor para o primeiro registro
            while (temProximo) {

                Produto produto = new Produto();
                produto.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                produto.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                produto.setQnt(cursor.getInt(cursor.getColumnIndex("QNT")));
                produto.setPreco(cursor.getDouble(cursor.getColumnIndex("PRECO")));

                produtos.add(produto);
                temProximo = cursor.moveToNext();
            }
        }
        return produtos;
    }
}