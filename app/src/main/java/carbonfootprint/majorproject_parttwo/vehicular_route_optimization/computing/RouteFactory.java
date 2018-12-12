package carbonfootprint.majorproject_parttwo.vehicular_route_optimization.computing;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import carbonfootprint.majorproject_parttwo.vehicular_route_optimization.computing.annealing.AnnealingRouteCalculator;
import carbonfootprint.majorproject_parttwo.vehicular_route_optimization.computing.climber.HillClimberRouteCalculator;
import carbonfootprint.majorproject_parttwo.vehicular_route_optimization.computing.genetic.GeneticRouteCalculator;
import carbonfootprint.majorproject_parttwo.vehicular_route_optimization.model.RoutePoint;
import carbonfootprint.majorproject_parttwo.vehicular_route_optimization.util.Helper;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class RouteFactory {
    private final String TAG = RouteFactory.class.getSimpleName();

    private final Context mCtx;
    private List<RouteCalculator> mAlgorithms;

    public RouteFactory(final Context ctx) {
        mCtx = ctx;
        mAlgorithms = new ArrayList<>();

        // Adding our available Algorithms
        mAlgorithms.add(new AnnealingRouteCalculator());
        mAlgorithms.add(new HillClimberRouteCalculator());
        mAlgorithms.add(new GeneticRouteCalculator());
    }

    /**
     * This method tries to optimize the given route in terms of length.
     * Therefore it uses the registered algorithms.
     *
     * @param route which should be optimized
     * @return Optimized Route
     */
    public Observable<List<RoutePoint>> optimizeGivenRoute(final List<String> route) {
        return Observable.defer(new Callable<ObservableSource<? extends List<RoutePoint>>>() {
            @Override
            public ObservableSource<? extends List<RoutePoint>> call() throws Exception {
                List<RoutePoint> bestRoute = transform(route);
                List<Observable<List<RoutePoint>>> sources = new ArrayList<>();

                sources.add(Observable.just(bestRoute));
                for (RouteCalculator algorithm : mAlgorithms) {
                    sources.add(algorithm.calculate(new ArrayList<>(bestRoute)));
                }

                return Observable.concat(sources);
            }
        });
    }

    /**
     * Takes the user provided list and transforms it into our model.
     *
     * @param list user input
     * @return user input in model form
     */
    private List<RoutePoint> transform(List<String> list) {
        return Helper.searchBy(mCtx, list);
    }
}
