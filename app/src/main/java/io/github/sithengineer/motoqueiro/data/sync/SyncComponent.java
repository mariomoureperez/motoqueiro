package io.github.sithengineer.motoqueiro.data.sync;

import android.content.AbstractThreadedSyncAdapter;
import dagger.Subcomponent;
import io.github.sithengineer.motoqueiro.scope.RideScope;

@RideScope @Subcomponent(modules = { SyncModule.class })
public interface SyncComponent {
  void inject(AbstractThreadedSyncAdapter syncAdapter);
}
