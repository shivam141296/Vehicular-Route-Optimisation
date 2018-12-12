package carbonfootprint.majorproject_parttwo.vehicular_route_optimization.computing.climber;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import carbonfootprint.majorproject_parttwo.vehicular_route_optimization.computing.RouteCalculator;
import carbonfootprint.majorproject_parttwo.vehicular_route_optimization.model.RoutePoint;
import carbonfootprint.majorproject_parttwo.vehicular_route_optimization.util.Helper;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.schedulers.Schedulers;

public class HillClimberRouteCalculator implements RouteCalculator {

    private final Integer TIMES = 100000;
    private final String TAG = HillClimberRouteCalculator.class.getCanonicalName();

    public HillClimberRouteCalculator() {}

    @Override
    public Observable<List<RoutePoint>> calculate(final List<RoutePoint> initialRoute) {
        return Observable.defer(new Callable<ObservableSource<? extends List<RoutePoint>>>() {
            @Override
            public ObservableSource<? extends List<RoutePoint>> call() throws Exception {
                List<RoutePoint> best = initialRoute;
                Double bestFitness = Helper.calculateInverseDistance(best);
                List<RoutePoint> savedState;

                for (int i = 0; i < TIMES; i++) {
                    savedState = new ArrayList<>(best);
                    Helper.swapRandomPoints(best);

                    Double currentFitness = Helper.calculateInverseDistance(best);

                    if (currentFitness > bestFitness) {
                        // No need for restoring state, update the bestFitness
                        bestFitness = currentFitness;
                    } else {
                        // Restore the old route
                        best = savedState;
                    }
                }
                Log.i(TAG, "Finished Executing");

                return Observable.just(best);
            }
        }).subscribeOn(Schedulers.newThread());
    }
}
