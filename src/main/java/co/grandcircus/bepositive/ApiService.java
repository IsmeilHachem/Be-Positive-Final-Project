package co.grandcircus.bepositive;

import java.net.URLEncoder;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.bepositive.pojos.DocumentResponse;

@Component
public class ApiService {

	private RestTemplate restTemplate;
	{
		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
			request.getHeaders().add(HttpHeaders.USER_AGENT, "Spring");
			return execution.execute(request, body);
		};
//		restTemplate = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
		restTemplate = new RestTemplateBuilder().additionalInterceptors(interceptor)
				.basicAuthentication("apiKey", "n1vF_Yuwo7Nm89JfsVQd43mged9lSbqOQO8zes2alnPw").build();
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
}
