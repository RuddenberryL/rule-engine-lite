package controller;

import com.google.gson.Gson;
import facts.Fare;
import facts.TaxiRide;
import facts.interf.Fact;
import facts.interf.Global;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.RuleServices;
import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;
@RestController
public class ServiceRestController {
    private final RuleServices services;

    @Autowired
    public ServiceRestController(RuleServices services) {
        this.services = services;
    }

    @ModelAttribute("Fact")
    public Fact getFact(HttpServletRequest request) throws IOException {
        String body = IOUtils.toString(request.getReader());
        JSONObject object = new JSONObject(body);
        String type = (String) object.get("type");
        String value = (String) object.get("value");
        Gson g = new Gson();
        switch(type) {
            case "Fare":
                return new Fare();
            case "TaxiRide":
                return g.fromJson(value, TaxiRide.class);
        }
        return null;
    }

    @RequestMapping(value="/engine-run", method= RequestMethod.GET, produces="application/json")
    public Global service(@RequestParam(required=true, name="ruleName")String ruleName,
                          @RequestBody @ModelAttribute("Fact") Fact fact) {
        System.out.println(ruleName);
        System.out.println(new Gson().toJson(fact));
        return null;
//        Service invokeService = this.services.getServiceByName(ruleName);
//        if (invokeService.getName().equals( "NOT_FOUND" )) {
//            return new Global() {
//                @Override
//                public String getGlobalName() {
//                    return "ERROR: Rule name" + ruleName + " not found.";
//                }
//            };
//        } else {
//            return invokeService.impl(fact);
//        }
    }
}
