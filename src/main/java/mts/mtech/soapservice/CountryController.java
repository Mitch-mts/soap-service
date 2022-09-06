package mts.mtech.soapservice;

import mts.mtech.soapservice.soap.country.CountryRequest;
import mts.mtech.soapservice.soap.country.CountryResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.print.attribute.standard.MediaSize;

/*registers the class with Spring WS as a potential
 candidate for processing incoming SOAP messages*/
@Endpoint
public class CountryController {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /*annotation is then used by Spring WS to pick the handler method, based on
     the message’s namespace and localPart.

     The @RequestPayload annotation indicates that the incoming
     message will be mapped to the method’s request parameter.

    The @ResponsePayload annotation makes Spring WS map the returned value to the response payload.*/
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "countryRequest")
    @ResponsePayload
    public CountryResponse getCountry(@RequestPayload CountryRequest countryRequest){
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setCountry(countryRepository.findCountry(countryRequest.getName()));
        return countryResponse;
    }
}
