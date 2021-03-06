package carbonfootprint.majorproject_parttwo.vehicular_route_optimization.computing.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import carbonfootprint.majorproject_parttwo.vehicular_route_optimization.model.RoutePoint;
import carbonfootprint.majorproject_parttwo.vehicular_route_optimization.util.Helper;

class Individual {
    private final Double MUTATION_RATE = 0.45;
    private final List<RoutePoint> mDna;
    private Double mFitness;

    Individual(List<RoutePoint> dna) {
        mDna = new ArrayList<>(dna);
    }

    void calculateFitness() {
        mFitness = Helper.calculateInverseDistance(mDna);
    }

    void mutate() {
        Random generator = new Random();
        if (generator.nextDouble() < MUTATION_RATE) {
            Collections.shuffle(mDna.subList(1, mDna.size() - 2), generator);
        }
    }

    Individual orderedCrossOver(Individual otherParent) {
        List<RoutePoint> childDna = new ArrayList<>(); // Important first and last position are immutable

        int start = 1;
        int end = mDna.size() / 2;

        for (int i = start; i < end; i++) {
            childDna.add(new RoutePoint(mDna.get(i)));
        }

        for (int i = 1; i < otherParent.mDna.size() - 1; i++) {
            RoutePoint current = otherParent.mDna.get(i);
            if (!childDna.contains(current)) {
                childDna.add(new RoutePoint(current));
            }
        }

        childDna.add(0, new RoutePoint(mDna.get(0)));
        childDna.add(mDna.size() - 1, new RoutePoint(mDna.get(mDna.size() - 1)));
        return new Individual(childDna);
    }

    List<RoutePoint> getDna() {
        return mDna;
    }

    Double getFitness() {
        return mFitness;
    }

}
