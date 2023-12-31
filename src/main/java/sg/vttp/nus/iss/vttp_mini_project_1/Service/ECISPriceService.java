package sg.vttp.nus.iss.vttp_mini_project_1.Service;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import sg.vttp.nus.iss.vttp_mini_project_1.Model.NodalEnergyPrice;

@Service
public class ECISPriceService {
    public List<NodalEnergyPrice> getECISPrice(String date) {
        RestTemplate template = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://www.nems.emcsg.com/api/sitecore/DataSync/DataDownload")
            .queryParam("value","2")
            .queryParam("fromDate", date)
            .queryParam("toDate", date)
            .queryParam("seriesName","IGS_A+:+SPSVCS+:+GENR+1")
            .queryParam("tpcValue", "1");

        String url = builder.build().toUriString();
        // System.out.println(url);
        
        // String csv = template.getForObject(url , String.class);

        List<NodalEnergyPrice> priceList = template.execute(url,HttpMethod.GET, null, clientHttpResponse -> {
            InputStreamReader reader = new InputStreamReader(clientHttpResponse.getBody());
            // System.out.println(clientHttpResponse.getStatusCode());
            CsvToBean<NodalEnergyPrice> csvToBean = new CsvToBeanBuilder<NodalEnergyPrice>(reader)
                .withType(NodalEnergyPrice.class)
                .withSkipLines(1)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
            return csvToBean.stream().collect(Collectors.toList());
        });

        return priceList;
    }

    public boolean validateDate(String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString);
            LocalDate maxDate = LocalDate.now().minusDays(10L).plusDays(1L);
            LocalDate minDate = LocalDate.of(2018, 1, 1).minusDays(1L);

            return (date.isAfter(minDate) && date.isBefore(maxDate));
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
}
