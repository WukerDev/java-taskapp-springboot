package com.project.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.project.model.Zadanie;


public class ZadanieServiceImpl implements ZadanieService {

	private static final Logger logger = LoggerFactory.getLogger(ZadanieServiceImpl.class);

	private static final String RESOURCE_PATH = "/api/projekty";

	@Value("${rest.server.url}")
	private String serverUrl;

	private RestTemplate restTemplate;

	public ZadanieServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public Optional<Zadanie> getZadanie(Integer zadanieId) {
		URI url = buildUri(zadanieId);
		logger.info("GET request for URL: {}", url);
		return Optional.ofNullable(restTemplate.getForObject(url, Zadanie.class));
	}

	@Override
	public Zadanie setZadanie(Zadanie zadanie) {
		if (zadanie.getZadanieId() != null) {
			URI url = buildUri(zadanie.getZadanieId());
			logger.info("PUT request for URL: {}", url);
			restTemplate.put(url, zadanie);
			return zadanie;
		} else {
			HttpEntity<Zadanie> request = new HttpEntity<>(zadanie);
			URI url = buildUri();
			logger.info("POST request for URL: {}", url);
			URI location = restTemplate.postForLocation(url, request);
			logger.info("GET request for location: {}", location);
			return restTemplate.getForObject(location, Zadanie.class);
		}
	}

	@Override
	public void deleteZadanie(Integer zadanieId) {
		URI url = buildUri(zadanieId);
		logger.info("DELETE request for URL: {}", url);
		restTemplate.delete(url);
	}

	@Override
	public Page<Zadanie> findZadaniaProjektu(Integer projektId, Pageable pageable) {
		URI url = buildUriWithQueryParams(pageable, "projekt", projektId);
		logger.info("GET request for URL: {}", url);
		return getPage(url);
	}

	@Override
	public List<Zadanie> findZadaniaProjektu(Integer projektId) {
		URI url = buildUriWithQueryParams("projekt", projektId);
		logger.info("GET request for URL: {}", url);
		return getPage(url).toList();
	}

	private URI buildUri() {
		return buildUri(null);
	}

	private URI buildUri(Integer id) {
		String uriString = serverUrl + getResourcePath(id);
		return URI.create(uriString);
	}

	private URI buildUriWithQueryParams(String paramName, Integer paramValue) {
		return buildUriWithQueryParams(null, paramName, paramValue);
	}

	private URI buildUriWithQueryParams(Pageable pageable, String paramName, Integer paramValue) {
		URI uri = buildUri();
		URI finalUri = URI.create(uri + "?" + paramName + "=" + paramValue);
		if (pageable != null) {
			finalUri = URI.create(finalUri + "&" + getPageableQueryParams(pageable));
		}
		return finalUri;
	}

	private URI getPageableQueryParams(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	private Page<Zadanie> getPage(URI uri) {
		ParameterizedTypeReference<List<Zadanie>> typeRef = new ParameterizedTypeReference<>() {};
		return (Page<Zadanie>) restTemplate.exchange(uri, HttpMethod.GET, null, typeRef).getBody();
	}

	private String getResourcePath(Integer id) {
		if (id == null) {
			return RESOURCE_PATH;
		} else {
			return RESOURCE_PATH + "/" + id;
		}
	}
}
