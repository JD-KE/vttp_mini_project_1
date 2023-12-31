package sg.vttp.nus.iss.vttp_mini_project_1.Repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import sg.vttp.nus.iss.vttp_mini_project_1.Model.QuoteRequest;

@Repository
public class QuoteRequestRepo {

    @Autowired @Qualifier("redisStringTemplate")
    private RedisTemplate<String,String> template;

    @Resource(name = "redisStringTemplate")
    private HashOperations<String,String,String> hashOps;

    public String createRequest(QuoteRequest quoteRequest) {
        String idString = generateId();
        hashOps.put("quoteRequests",idString,quoteRequest.toString());
        
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
        hashOps.hasKey("quoteRequests",trackingNumber);
        return hashOps.hasKey("quoteRequests",trackingNumber);
    }

    public QuoteRequest getRequest(String id) {
        String requestString = hashOps.get("quoteRequests", id);
        String[] requestComponents = requestString.split(", ");
        QuoteRequest request = new QuoteRequest(
            requestComponents[0].substring(10),
            requestComponents[1].substring(11),
            requestComponents[2].substring(6),
            requestComponents[3].substring(9),
            requestComponents[4].substring(15),
            requestComponents[5].substring(12),
            requestComponents[6].substring(9),
            requestComponents[7].substring(9),
            requestComponents[8].substring(12),
            requestComponents[9].substring(20),
            requestComponents[10].substring(17),
            requestComponents[11].substring(20),
            Boolean.valueOf(requestComponents[12].substring(17))
        );
        return request;
    }
    
}
