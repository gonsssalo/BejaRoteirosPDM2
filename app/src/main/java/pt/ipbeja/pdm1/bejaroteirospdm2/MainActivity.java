package pt.ipbeja.pdm1.bejaroteirospdm2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity
        implements FragmentHeadlines.OnHeadlineSelectedListener {

 public int posiçao;
    Context ctx = this;
    public void onArticleSelected(int position) {

        if (findViewById(R.id.Fragment_conteiner) != null){

           FragmentArticle newFragment = new FragmentArticle();
            Bundle args = new Bundle();
            args.putInt("position", position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.Fragment_conteiner, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
            posiçao = position;
        }
        else{
            /*Toast.makeText(getActivity().getBaseContext(), "Clicked Landscape." + NewsData.Headlines[pos],
                    Toast.LENGTH_SHORT).show();*/
            FragmentArticle articleFrag = (FragmentArticle)
                    getSupportFragmentManager().findFragmentById(R.id.article_fragment);
            articleFrag.updateArticleView(position);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       action();


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

    public void action(){
        DataBaseOperations db = new DataBaseOperations(this);
        DataBaseOperations DB = new DataBaseOperations(ctx);

       /* for(int i = 0; i < NewsData.locais.length -1; i++) {
            DB.putInformation( NewsData.locais[i], NewsData.desciçao[i], NewsData.contacto[i], NewsData.link[i]);

        }*/
        NewsData.DBLocais = db.getAllLocations();
        NewsData.DBDescricao = db.getAllDescricions();
        NewsData.DBLink = db.getAllLinks();
        NewsData.DBNumber = db.getAllNumbers();

    }


    public void btnMapa_onClick(View view) {



        Intent SendIntent = new Intent(this, Mapalocais.class);

        SendIntent.putExtra("linkWeb", NewsData.DBLink.get(posiçao));
        startActivity(SendIntent);
    }

    public void btnLigar_onClick(View view) {

        Uri number = Uri.parse("tel:" + NewsData.DBNumber.get(posiçao));
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

        if(NewsData.contacto[posiçao] != "") {

                startActivity(callIntent);
            }

        else {
            Toast.makeText(this,"Não é possivel fazer a chamada por falta de informação", Toast.LENGTH_SHORT).show();
        }
    }
}
