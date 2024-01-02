package sg.vttp.nus.iss.vttp_mini_project_1.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.vttp.nus.iss.vttp_mini_project_1.Service.QuotationService;

@RestController
@RequestMapping(path = "/quote/export")
public class QuoteRequestExportController {

    @Autowired
    QuotationService quoteSvc;

    
    @GetMapping(path = "/{id}.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> exportRequestAsJson(@PathVariable String id) {
        String requestJsonString = quoteSvc.getRequestJson(id);
        
        return ResponseEntity.ok(requestJsonString);
    }
}
