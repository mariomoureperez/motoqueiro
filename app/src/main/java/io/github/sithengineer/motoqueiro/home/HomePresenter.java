package io.github.sithengineer.motoqueiro.home;

import com.trello.rxlifecycle.android.FragmentEvent;
import io.github.sithengineer.motoqueiro.exception.GpsNotActiveException;
import io.github.sithengineer.motoqueiro.util.CompositeSubscriptionManager;
import java.util.concurrent.TimeUnit;
import rx.Completable;
import rx.Observable;
import timber.log.Timber;

public class HomePresenter implements HomeContract.Presenter {

  private final CompositeSubscriptionManager subscriptionManager;
  private final HomeContract.View view;
  private final RideManager rideManager;

  public HomePresenter(HomeContract.View view,
      CompositeSubscriptionManager subscriptionManager, RideManager rideManager) {
    this.view = view;
    this.subscriptionManager = subscriptionManager;
    this.rideManager = rideManager;
  }

  @Override public void start() {
    Observable<Void> handleStartClick = view.handleStartClick()
        .flatMap(__ -> rideManager.start(view.getRideName())
            .doOnSuccess(rideId -> goToCruisingActivity(rideId, view.getMiBandAddress()))
            .toObservable())
        .onErrorResumeNext(err -> handleStartRideError(err).map(__ -> null))
        .map(__ -> null);

    subscriptionManager.add(view.lifecycle()
        .filter(event -> event == FragmentEvent.RESUME)
        .flatMap(__ -> handleStartClick)
        .compose(view.bindUntilEvent(FragmentEvent.PAUSE))
        .subscribe(__ -> {
        }, err -> Timber.e(err)));
  }

  @Override public void stop() {
    subscriptionManager.clearAll();
  }

  private Observable<Void> handleStartRideError(Throwable err) {
    if (err instanceof GpsNotActiveException) {
      return sendToActivateGpsSettings();
    }

    Timber.e(err);
    view.showGenericError();
    return Observable.empty();
  }

  private Observable<Void> sendToActivateGpsSettings() {
    return Completable.fromAction(() -> showActivateGpsViewMessage())
        .andThen(Completable.timer(2, TimeUnit.SECONDS)
            .doOnCompleted(() -> showActivateGpsView()))
        .toObservable();
  }

  @Override public void showActivateGpsViewMessage() {
    view.showActivateGpsViewMessage();
  }

  @Override public void showActivateGpsView() {
    view.sendToGpsSettings();
  }

  private void goToCruisingActivity(String rideId, String miBandAddress) {
    view.goToCruisingActivity(rideId, miBandAddress);
  }
}
