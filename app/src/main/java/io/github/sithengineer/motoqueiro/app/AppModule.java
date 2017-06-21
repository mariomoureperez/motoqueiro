package io.github.sithengineer.motoqueiro.app;

import android.app.Application;
import android.preference.PreferenceManager;
import dagger.Module;
import dagger.Provides;
import io.github.sithengineer.motoqueiro.authentication.AccountManager;
import javax.inject.Singleton;

@Module public class AppModule {
  private final Application application;
  private final android.accounts.AccountManager androidAccountManager;

  public AppModule(Application application, android.accounts.AccountManager accountManager) {
    this.application = application;
    this.androidAccountManager = accountManager;
  }

  @Provides @Singleton Application providesApplication() {
    return application;
  }

  @Provides @Singleton Preferences providesSharedPrefs() {
    return new Preferences(PreferenceManager.getDefaultSharedPreferences(application));
  }

  @Provides @Singleton AccountManager providesAccountManager() {
    return new AccountManager(androidAccountManager);
  }
}
