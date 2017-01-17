package pt.ipbeja.pdm1.bejaroteirospdm2;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends FragmentActivity
        implements FragmentHeadlines.OnHeadlineSelectedListener {

    public void onArticleSelected(int position) {

        if (findViewById(R.id.Fragment_conteiner) != null){

            /*Toast.makeText(getActivity().getBaseContext(), "Clicked Portrait." + NewsData.Headlines[pos],
                    Toast.LENGTH_SHORT).show();*/

            // Create fragment and give it an argument specifying the article it should show
            FragmentArticle newFragment = new FragmentArticle();
            Bundle args = new Bundle();
            args.putInt("position", position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.Fragment_conteiner, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();

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

        /*Toast.makeText(this, NewsData.Headlines[0], Toast.LENGTH_SHORT).show();
        Toast.makeText(this, NewsData.Articles[0], Toast.LENGTH_SHORT).show();
        Toast.makeText(this, NewsData.Headlines[1], Toast.LENGTH_SHORT).show();
        Toast.makeText(this, NewsData.Articles[1], Toast.LENGTH_SHORT).show();*/

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.Fragment_conteiner) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            FragmentHeadlines firstFragment = new FragmentHeadlines();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.Fragment_conteiner, firstFragment).commit();
        }

    }


}
