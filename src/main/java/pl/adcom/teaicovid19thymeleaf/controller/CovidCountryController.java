package pl.adcom.teaicovid19thymeleaf.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pl.adcom.teaicovid19thymeleaf.model.Country;
import pl.adcom.teaicovid19thymeleaf.model.Covid;
import pl.adcom.teaicovid19thymeleaf.model.Global;

import java.util.List;

@Controller
public class CovidCountryController {

    public String BASE_URL = "https://api.covid19api.com";

    @GetMapping
    public String getGlobalStatisticOfCovid(Model model){
        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Covid> allCovids = restTemplate.getForEntity(BASE_URL + "/summary", Covid.class);
        ResponseEntity<Covid> allCovids = restTemplate.exchange(BASE_URL + "/summary"
                                                                , HttpMethod.GET
                                                                , HttpEntity.EMPTY
                                                                , Covid.class);

        Global global = allCovids.getBody().getGlobal();

        List<Country> countries = allCovids.getBody().getCountries();

        model.addAttribute("global", global);
        model.addAttribute("countries", countries);

        return "covid";
    }

}
