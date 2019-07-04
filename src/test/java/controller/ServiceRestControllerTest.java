package controller;

import com.google.gson.Gson;
import facts.TaxiRide;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;
import services.RuleServices;
import services.impls.TaxiFareCalculationService;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@RequestMapping("/engine-run")
public class ServiceRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleServices service;

    @Test
    public void givenTaxiRide_whenImpl_thenReturnFare() throws Exception {
        given(service.getServiceByName("TaxiFareCalculationService")).willReturn(new TaxiFareCalculationService());
        JSONObject input = new JSONObject();
        input.put("type", "TaxiRide");
        input.put("value", new Gson().toJson(new TaxiRide(false, 10L)));
        mockMvc.perform(get("/engine-run?ruleName=TaxiFareCalculationService")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(input.toString())
                )).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nightSurcharge", is(0)));
    }
}