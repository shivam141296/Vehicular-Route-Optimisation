package carbonfootprint.majorproject_parttwo.vehicular_route_optimization.computing;

import java.util.List;

import carbonfootprint.majorproject_parttwo.vehicular_route_optimization.model.RoutePoint;
import io.reactivex.Observable;

public interface RouteCalculator {
    Observable<List<RoutePoint>> calculate(List<RoutePoint> initialRoute);
}
