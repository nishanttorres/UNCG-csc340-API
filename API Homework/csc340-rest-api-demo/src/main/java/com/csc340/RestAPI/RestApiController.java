package com.csc340.RestAPI;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import static org.springframework.util.StringUtils.capitalize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Nishant Sharma
 */
@RestController
public class RestApiController {
    /**
     * Type in the capital city to know which country it is in. 
     * @param city
     * @return 
     */
    @RequestMapping(value = "/capital/{city}", method = RequestMethod.GET)
    public List<Object> getCountry(@PathVariable("city") String city) {

        String url = "https://restcountries.com/v3.1/capital/" + city;
        RestTemplate restTemplate = new RestTemplate();
        Object[] capital = restTemplate.getForObject(url, Object[].class);
        
        //Print the whole response to console.
        JSONArray ja = new JSONArray(capital);
        System.out.println(ja.toString());
        
        //Parse out the most important info from the response.
        JSONObject jo = ja.getJSONObject(0);
        JSONObject name = jo.getJSONObject("name");
        String country = name.getString("common");

        city = capitalize(city);
        System.out.printf("%s is the capital city of %s.\n", city, country);

        return Arrays.asList(capital);
    }
}
