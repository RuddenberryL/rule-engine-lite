package facts;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import facts.interf.Fact;
import facts.interf.Global;
import utils.annotations.ModelName;

@JsonAutoDetect
@ModelName("Fare")
public class Fare implements Global, Fact {
    private Long nightSurcharge;
    private Long rideFare;

    public Fare(){}

    public Fare( Long nightSurcharge, Long rideFare) {
        this.nightSurcharge = nightSurcharge;
        this.rideFare = rideFare;
    }

    public Long getNightSurcharge() {
        return nightSurcharge;
    }

    public void setNightSurcharge(Long nightSurcharge) {
        this.nightSurcharge = nightSurcharge;
    }

    public Long getRideFare() {
        return rideFare;
    }

    public void setRideFare(Long rideFare) {
        this.rideFare = rideFare;
    }
}
