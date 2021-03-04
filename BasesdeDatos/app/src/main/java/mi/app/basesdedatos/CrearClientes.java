package mi.app.basesdedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CrearClientes extends SQLiteOpenHelper {
    String sqlCreate = "CREATE TABLE Clientes (DNI INTEGER PRIMARY KEY, Nombre TEXT, Direccion TEXT, tlf TEXT)";

    public CrearClientes (Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version){
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la versión anterior de la tabla
        //Ojo, haciendo esto eliminas también TODOS los registros, habría que pensar en un volcado previo.
        db.execSQL("DROP TABLE IF EXISTS Clientes");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}
