package co.grandcircus.bepositive;

import java.net.URLEncoder;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import co.grandcircus.bepositive.dto.DocumentResponse;
import co.grandcircus.bepositive.dto.QuoteOfDay;

@Component
public class ApiService {

	@Value("${watsonKey}")
	private String watsonKey;

	private RestTemplate restTemplate;

	private RestTemplate restTemplateQuote;

	@PostConstruct
	public void init() {

		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
			request.getHeaders().add(HttpHeaders.USER_AGENT, "Spring");
			return execution.execute(request, body);
		};
		restTemplate = new RestTemplateBuilder().additionalInterceptors(interceptor)
				.basicAuthentication("apiKey", watsonKey).build();
		restTemplateQuote = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
	}

	public DocumentResponse search(String text) {

		String url = "https://gateway.watsonplatform.net/tone-analyzer/api/v3/tone?version=2017-09-21"
				+ (StringUtils.isEmpty(text) ? "" : "&text=" + URLEncoder.encode(text));
		System.out.println(URLEncoder.encode(text));
		System.out.println(url);
		DocumentResponse searchResponse = null;
		try {
			searchResponse = restTemplate.getForObject(url, DocumentResponse.class);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return searchResponse;
	}

	public QuoteOfDay getQuote() {

		String url = UriComponentsBuilder.fromHttpUrl("http://api.forismatic.com/api/1.0/")
				.queryParam("method", "getQuote").queryParam("format", "json").queryParam("lang", "en").toUriString();
		QuoteOfDay response = null;
		try {
			response = restTemplateQuote.getForObject(url, QuoteOfDay.class);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return response;
	}
}
