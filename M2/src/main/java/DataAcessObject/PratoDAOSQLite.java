package DataAcessObject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import Entidades.Prato;

public class PratoDAOSQLite extends SQLiteOpenHelper {
    private static final String dbName = "sys";

    public PratoDAOSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static SQLiteDatabase getDBInstance(Context context) {
        return new PratoDAOSQLite(context, dbName, null, 1).getWritableDatabase();
    }

    public static List<Prato> lerTodosOsPratos(Context context) {
        Cursor cursor = PratoDAOSQLite.getDBInstance(context).rawQuery("select * from cardapio", null);
        ArrayList<Prato> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nome = cursor.getString(1);
            String descricao = cursor.getString(2);
            BigDecimal preco = new BigDecimal("0");
            boolean gluten = cursor.getString(4).equals("1");
            BigDecimal calorias = new BigDecimal(cursor.getString(5));
            String imageID = cursor.getString(6);

            lista.add(new Prato(id,nome,descricao,preco,gluten,calorias,imageID));
        }
        return lista;
    }

    public static void salvarPratos(List<Prato> pratos, Context context) {
        SQLiteDatabase dbInstance = getDBInstance(context);
        dbInstance.execSQL(
                "CREATE TABLE IF NOT EXISTS cardapio (id INT NOT NULL PRIMARY KEY,nome VARCHAR(50),descricao VARCHAR(100),preco DECIMAL(7,2),gluten BOOLEAN, calorias INT, image VARCHAR(500))"
        );
        dbInstance.execSQL("delete from cardapio");

        for (Prato prato : pratos) {
            dbInstance.execSQL("insert into cardapio values(?,?,?,?,?,?,?)",
                    new Object[]{prato.getId(),prato.getNome(),prato.getDescricao(),prato.getPreco(), prato.getGluten(),prato.getCalorias(),prato.getImageID()});
        }
        dbInstance.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS cardapio (id INT NOT NULL PRIMARY KEY,nome VARCHAR(50),descricao VARCHAR(100),preco DECIMAL(7,2),gluten BOOLEAN, calorias INT,image VARCHAR(500))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void clearDatabase(SQLiteDatabase db){
        db.execSQL(
                "DROP TABLE pratos"
        );
    }



    /*public static final String DB_NAME="APP_MESSAGES_DEMO";
    public static final int DB_VERSION=3;
    private Context context=null;


    public PratoDAOSQLite(@Nullable Context context, @Nullable  String name, @Nullable  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory    , version);
        this.context=context;

        this.getWritableDatabase().execSQL(
                "create table if not exists cardapio (id INT(11) primary key, nome VARCHAR(50), descricao VARCHAR(200), preco DECIMAL(10,2), gluten BOOLEAN, calorias INT)"
        );

    }

    @Override
    public List<Prato> obterTodosOsPratosJSON() {
        return null;
    }

    @Override
    public List<Prato> obterTodosOsPratosXML() {
        return null;
    }

    @Override
    public boolean salvarPratos(Prato p) {
        this.getWritableDatabase().execSQL("delete from cardapio", null);
        try {
            String sql = "INSERT INTO cardapio (id, nome, descricao, preco, gluten, calorias) VALUES(?,?,?,?,?,?)";
            this.getWritableDatabase().execSQL(sql, new Object[]{p.getId() ,p.getNome(), p.getDescricao(), p.getPreco(), p.getGluten(), p.getCalorias()});
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Prato> lerTodosOsPratos() {
        ArrayList<Prato> list= new ArrayList<>();
        Cursor cursor= this.getReadableDatabase().rawQuery("select * from cardapio", null);
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nome = cursor.getString(1);
            String descricao = cursor.getString(2);
            BigDecimal preco = new BigDecimal("0");
            boolean gluten = cursor.getString(4).equals("1");;
            //gluten = cursor.getInt(4) == 1;
            BigDecimal calorias = new BigDecimal(cursor.getString(5));
            //int imageId=Integer.valueOf(childrenTags.item(2).getTextContent());
            Prato p= new Prato(id, nome, descricao, preco, gluten, calorias);

            list.add(p);
        }
        return list;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }*/
}
