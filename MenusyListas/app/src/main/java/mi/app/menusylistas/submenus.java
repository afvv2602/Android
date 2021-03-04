package mi.app.menusylistas;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import androidx.appcompat.app.AppCompatActivity;

public class submenus extends AppCompatActivity {

    private static final int Opcion1=1;
    private static final int Opcion2=2;
    private static final int OpcionN=3;
    private static final int Opcion4=4;
    private static final int Opcion5=5;
    private static final int SUBMENU_OPC1=11;
    private static final int SUBMENU_OPC2=12;
    private static final int SUBMENU_OPC3=13;
    private static final int SUBMENU_OPC4=14;
    private static final int SUBMENU_OPC5=15;


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.submenu, menu);
        SubMenu sbmn=menu.addSubMenu(Menu.NONE,Opcion1,Menu.NONE,"1.Melones");
        sbmn.add(Menu.NONE,SUBMENU_OPC1,Menu.NONE,"1.1. Piel de sapo");
        sbmn.add(Menu.NONE,SUBMENU_OPC2,Menu.NONE,"1.2. Galia");
        sbmn.add(Menu.NONE,SUBMENU_OPC3,Menu.NONE,"1.3. Honey dew");
        menu.add(Menu.NONE,Opcion2,Menu.NONE,"2.Platanos");
        SubMenu sbmn2=menu.addSubMenu(Menu.NONE,OpcionN,Menu.NONE,"3.Aguacates");
        sbmn2.add(Menu.NONE,SUBMENU_OPC4,Menu.NONE,"3.1. Hass");
        sbmn2.add(Menu.NONE,SUBMENU_OPC5,Menu.NONE,"3.2. Pinkerton");
        menu.add(Menu.NONE,Opcion4,Menu.NONE,"Menu 1");
        menu.add(Menu.NONE,Opcion5,Menu.NONE,"Menu 2");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {

            case R.id.Opcion1:
            case R.id.Opcion2:
            case R.id.Opcion4:
                Intent comunicacion = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(comunicacion);
                finish();
            case R.id.Opcion5:
                Intent com = new Intent(getApplicationContext(),actionbar2.class);
                startActivity(com);
                finish();
            case R.id.OpcionN:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

