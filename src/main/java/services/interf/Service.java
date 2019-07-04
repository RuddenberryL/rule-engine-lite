package services.interf;

import org.kie.api.runtime.KieSession;
import facts.interf.Fact;
import facts.interf.Global;
import java.util.List;

public interface Service{
    Global createNewGlobal();
    void setKieSession(KieSession session);
    KieSession getKieSession();
    default Global impl(Fact fact) {
        Global global = createNewGlobal();
        KieSession session = getKieSession();
        session.setGlobal(global.getGlobalName(), global);
        session.insert(fact);
        session.fireAllRules();
        session.dispose();
        return global;
    }
    String getName();
}
