package sg.vttp.nus.iss.vttp_mini_project_1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.vttp.nus.iss.vttp_mini_project_1.Model.QuoteRequest;
import sg.vttp.nus.iss.vttp_mini_project_1.Repo.QuoteRequestRepo;

@Service
public class QuotationService {

    @Autowired
    QuoteRequestRepo repo;

    public String createRequest(QuoteRequest quoteRequest) {
        return repo.createRequest(quoteRequest);
    }

    public boolean hasRequest(String trackingNumber) {
        return repo.hasRequest(trackingNumber);
    }

    public QuoteRequest getRequest(String id) {
        return repo.getRequest(id);
    }

    public String getRequestJson(String id) {
        QuoteRequest quoteRequest = repo.getRequest(id);
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
            .build();
        return requestJson.toString();
    }
    
}
