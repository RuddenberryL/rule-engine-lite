package facts;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import facts.interf.Fact;
import facts.interf.Global;
import utils.annotations.ModelName;

@JsonAutoDetect
@ModelName("TaxiRide")
public class TaxiRide implements Global, Fact {
    private Boolean isNightSurcharge;
    private Long distanceInMile;

    public TaxiRide() {}

    public TaxiRide( Boolean isNightSurcharge, Long distanceInMile) {
        this.isNightSurcharge = isNightSurcharge;
        this.distanceInMile = distanceInMile;
    }

    public Boolean getNightSurcharge() {
        return isNightSurcharge;
    }

    public void setNightSurcharge(Boolean nightSurcharge) {
        isNightSurcharge = nightSurcharge;
    }

    public Long getDistanceInMile() {
        return distanceInMile;
    }

    public void setDistanceInMile(Long distanceInMile) {
        this.distanceInMile = distanceInMile;
    }
}
