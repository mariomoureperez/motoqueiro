package io.github.sithengineer.motoqueiro.statistics;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import io.github.sithengineer.motoqueiro.BaseActivity;
import io.github.sithengineer.motoqueiro.MotoqueiroApp;
import io.github.sithengineer.motoqueiro.R;

public class StatisticsActivity extends BaseActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_content_frame);

    StatisticsFragment fragment =
        (StatisticsFragment) getSupportFragmentManager().findFragmentById(
            R.id.content_frame);

    if (fragment == null) {
      fragment = StatisticsFragment.newInstance();

      FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
      transaction.addToBackStack("statistics_activity_fragment")
          .replace(R.id.content_frame, fragment)
          .commit();
    }

    MotoqueiroApp.get(this)
        .getRideComponent()
        .with(new StatisticsModule(fragment))
        .inject(fragment);
  }
}
