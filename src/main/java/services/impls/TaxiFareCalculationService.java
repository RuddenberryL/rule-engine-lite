package services.impls;

import facts.Fare;
import facts.interf.Global;
import org.kie.api.runtime.KieSession;
import services.interf.Service;

public class TaxiFareCalculationService implements Service {
    private KieSession kieSession;
    @Override
    public Global createNewGlobal() {
        return new Fare();
    }

    @Override
    public void setKieSession(KieSession session) {
        this.kieSession = session;
    }

    @Override
    public KieSession getKieSession() {
        return this.kieSession;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
