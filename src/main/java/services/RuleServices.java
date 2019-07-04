package services;

import facts.interf.Global;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import services.interf.Service;

import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Service
public class RuleServices {

    private static final Logger LOGGER = LogManager.getLogger(RuleServices.class.getName());

    private KieContainer kieContainer;

    private Map<String, Service> services;

    @Autowired
    public RuleServices(KieContainer kieContainer) {
        LOGGER.debug("Assigning kie container to service provider...");
        this.kieContainer = kieContainer;
        LOGGER.debug("Initializing services list");
        this.services = new HashMap<String, Service>();
    }

    public void addService(Service... services) {
        for (Service s: services) {
            this.services.put(s.getName(), s);
        }
    }

    public Service getServiceByName(String name) {
        return this.services.getOrDefault(name, new Service() {
            @Override
            public Global createNewGlobal() {
                return null;
            }

            @Override
            public void setKieSession(KieSession session) { }

            @Override
            public KieSession getKieSession() { return null; }

            @Override
            public String getName() { return "NOT_FOUND"; }
        });
    }
}
