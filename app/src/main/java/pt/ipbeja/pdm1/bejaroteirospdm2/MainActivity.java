package pt.ipbeja.pdm1.bejaroteirospdm2;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.Fragment_conteiner) != null) {


            if (savedInstanceState != null) {
                return;
            }

            FragmentHeadlines firstFragment = new FragmentHeadlines();

            firstFragment.setArguments(getIntent().getExtras());


            getSupportFragmentManager().beginTransaction()
                    .add(R.id.Fragment_conteiner, firstFragment).commit();
        }
    }
}