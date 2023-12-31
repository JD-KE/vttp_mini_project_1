package sg.vttp.nus.iss.vttp_mini_project_1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
}
