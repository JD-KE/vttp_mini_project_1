package sg.vttp.nus.iss.vttp_mini_project_1.Repo;

import java.io.Reader;
import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.vttp.nus.iss.vttp_mini_project_1.Model.QuoteRequest;

@Repository
public class QuoteRequestRepo {

    @Autowired @Qualifier("redisStringTemplate")
    private RedisTemplate<String,String> template;

    @Resource(name = "redisStringTemplate")
    private HashOperations<String,String,String> hashOps;

    public String createRequest(QuoteRequest quoteRequest) {
        String idString = generateId();
        JsonObject requestJson = Json.createObjectBuilder()
            .add("given_name", quoteRequest.getGivenName())
            .add("family_name", quoteRequest.getFamilyName())
            .add("email", quoteRequest.getEmail())
            .add("phone_number", quoteRequest.getMobileNo())
            .add("contact_address", quoteRequest.getContactAddress())
            .add("site_address", quoteRequest.getSiteAddress())
            .add("site_type", quoteRequest.getSiteType())
            .add("roof_type", quoteRequest.getRoofType())
            .add("monthly_bill", quoteRequest.getMonthlyBill())
            .add("electricity_retailer", quoteRequest.getElectricityRetailer())
            .add("electricity_usage", quoteRequest.getElectricityUsage())
            .add("expected_install_time", quoteRequest.getExpectedInstallTime())
            .add("contact_contractors", Boolean.valueOf(quoteRequest.getContactPermission()).toString())
            .build();

        hashOps.put("quoteRequests",idString,requestJson.toString());
        
        return idString;
    }

    private String generateId() {

        Integer idNumber;

        if(false == template.hasKey("quoteRequests")) {
            idNumber = 1;
        } else {
            idNumber = Integer.valueOf(Long.valueOf(hashOps.size("quoteRequests") + 1L).toString());
        }
        
        return String.format("%08d", idNumber);
    }

    public boolean hasRequest(String trackingNumber) {
        return hashOps.hasKey("quoteRequests",trackingNumber);
    }

    public QuoteRequest getRequest(String id) {
        String requestString = hashOps.get("quoteRequests", id);
        Reader reader = new StringReader(requestString);
        JsonReader jsonReader = Json.createReader(reader);
        JsonObject requestJson = jsonReader.readObject();
        QuoteRequest request = new QuoteRequest(
            requestJson.getString("given_name"),
            requestJson.getString("family_name"),
            requestJson.getString("email"),
            requestJson.getString("phone_number"),
            requestJson.getString("contact_address"),
            requestJson.getString("site_address"),
            requestJson.getString("site_type"),
            requestJson.getString("roof_type"),
            requestJson.getString("monthly_bill"),
            requestJson.getString("electricity_retailer"),
            requestJson.getString("electricity_usage"),
            requestJson.getString("expected_install_time"),
            Boolean.valueOf(requestJson.getString("contact_contractors"))
        );
        return request;
    }
    
}
