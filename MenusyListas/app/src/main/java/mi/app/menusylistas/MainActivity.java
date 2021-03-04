package mi.app.menusylistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.Opcion1:
                Intent comunicacion = new Intent(getApplicationContext(),actionbar2.class);
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