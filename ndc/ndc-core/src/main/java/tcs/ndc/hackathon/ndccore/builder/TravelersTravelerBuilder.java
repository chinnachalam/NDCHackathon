package tcs.ndc.hackathon.ndccore.builder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iata.ndc.schema.AnonymousTravelerType;
import org.iata.ndc.schema.ObjectFactory;
import org.iata.ndc.schema.TravelersTraveler;
import org.iata.ndc.schema.TravelerCoreType.PTC;

/**
 *
 */
final class TravelersTravelerBuilder implements Buildable<List<TravelersTraveler>> {

    private static final ObjectFactory factory = new ObjectFactory();

    private Map<Traveler, Integer> anonymousTravelers;

    public TravelersTravelerBuilder() {
        anonymousTravelers = new HashMap<Traveler, Integer>();
    }

    /**
     * Adds multiple anonymous travelers of type {@link Traveler} to traveler
     * list.<br>
     * <strong>Note:</strong> if this type of traveler already exists,
     * increments the count for this type of traveler.
     *
     * @param traveler type of traveler
     * @param count number of travelers
     * @return current builder instance
     */
    TravelersTravelerBuilder addAnonymousTravelers(Traveler traveler, int count) {
        if (!anonymousTravelers.containsKey(traveler)) {
            anonymousTravelers.put(traveler, count);
            return this;
        }
        Integer total = anonymousTravelers.get(traveler) + count;
        anonymousTravelers.put(traveler, total);
        return this;
    }

    void addTravelers() {

    }

    void setDefaults() {
        if (anonymousTravelers.isEmpty()) {
            addAnonymousTravelers(Traveler.ADT, 1);
        }
    }

    @Override
    public List<TravelersTraveler> build() {
        List<TravelersTraveler> travelers = new ArrayList<TravelersTraveler>(anonymousTravelers.size());
        org.iata.ndc.schema.TravelersTraveler traveler = factory.createTravelersTraveler();
        travelers.add(traveler);

        for (Traveler t : anonymousTravelers.keySet()) {
            AnonymousTravelerType anonymousTraveler = factory.createAnonymousTravelerType();

            PTC ptc = factory.createTravelerCoreTypePTC();
            ptc.setValue(t.name());
            ptc.setQuantity(BigInteger.valueOf(anonymousTravelers.get(t)));
            anonymousTraveler.setPTC(ptc);

            traveler.getAnonymousTraveler().add(anonymousTraveler);
        }
        return travelers;
    }

}
