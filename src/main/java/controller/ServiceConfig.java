package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import utils.Utils;

import java.nio.file.Path;
import java.util.List;


@Configuration
@ComponentScan("services")
public class ServiceConfig {
    private static final Logger LOGGER = LogManager.getLogger(ServiceConfig.class.getName());
    private List<String> drlFiles;
    public ServiceConfig() {
        LOGGER.debug("Iniitializing serivce configs");
        //List all drl files under classpath "resources/com/rules"
        this.drlFiles = Utils.getResourceFiles("com/rules");
    }

    @Bean
    public KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        for (String name: this.drlFiles) {
            kieFileSystem.write(ResourceFactory.newClassPathResource("com/rules/" + name));
        }
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        KieModule kieModule = kieBuilder.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }
}
