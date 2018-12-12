package carbonfootprint.majorproject_parttwo.vehicular_route_optimization.computing.genetic;

import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;

import carbonfootprint.majorproject_parttwo.vehicular_route_optimization.computing.RouteCalculator;
import carbonfootprint.majorproject_parttwo.vehicular_route_optimization.model.RoutePoint;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.schedulers.Schedulers;

public class GeneticRouteCalculator implements RouteCalculator {

    private final Universe mUniverse;
    private final String TAG = GeneticRouteCalculator.class.getCanonicalName();

    public GeneticRouteCalculator() {
        mUniverse = new Universe(1000);
    }

    @Override
    public Observable<List<RoutePoint>> calculate(final List<RoutePoint> initialRoute) {
        return Observable.defer(new Callable<ObservableSource<? extends List<RoutePoint>>>() {
            @Override
            public ObservableSource<? extends List<RoutePoint>> call() throws Exception {
                if(initialRoute.size() <= 0){
                    return Observable.empty();
                }else{
                    List<RoutePoint> result = mUniverse.evolveBetterRoute(initialRoute);
                    Log.i(TAG, "Finished Executing");
                    return Observable.just(result);
                }
            }
        }).subscribeOn(Schedulers.newThread());
    }
}
