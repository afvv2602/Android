package mi.app.menusylistas;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class actionbar2 extends AppCompatActivity {

    private static final int Opcion1=1;
    private static final int Opcion2=2;
    private static final int OpcionN=3;
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu2, menu);
        menu.add(Menu.NONE,Opcion1,Menu.NONE,"Menu 1");
        menu.add(Menu.NONE,Opcion2,Menu.NONE,"Submenu");
        menu.add(Menu.NONE,OpcionN,Menu.NONE,"Salir");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.Opcion1:
                Intent comunicacion = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(comunicacion);
                finish();
                return true;
            case R.id.Opcion2:
                Intent com = new Intent(getApplicationContext(),submenus.class);
                startActivity(com);
                finish();
                return true;
            case R.id.OpcionN:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
