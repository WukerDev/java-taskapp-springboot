package com.project.service;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.model.Projekt;
import com.project.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final RestTemplate restTemplate;
    private final String serverUrl;
    private final static String RESOURCE_PATH = "/api/projekty";

    public StudentServiceImpl(RestTemplate restTemplate, @Value("${rest.server.url}") String serverUrl) {
        this.restTemplate = restTemplate;
        this.serverUrl = serverUrl;
    }

    private String getResourcePath(Integer id) {
        return RESOURCE_PATH + "/" + id;
    }

    private String getResourcePath() {
        return RESOURCE_PATH;
    }

    private String getUriStringComponent() {
        return serverUrl + getResourcePath();
    }

    private String getUriStringComponent(Integer id) {
        return serverUrl + getResourcePath(id);
    }

    private URI buildUri(String path, Pageable pageable, String... queryParams) {
        URI uri = ServiceUtil.getUriComponent(serverUrl, path, pageable).build().toUri();
        if (queryParams.length > 0) {
            uri = URI.create(uri.toString() + "?" + String.join("&", queryParams));
        }
        return uri;
    }

    @Override
    public Optional<Student> getStudent(Integer studentId) {
        URI uri = buildUri(getResourcePath(studentId), null);
        logger.info("GET student: {}", uri);
        return Optional.ofNullable(restTemplate.getForObject(uri, Student.class));
    }

    @Override
    public Student setStudent(Student student) {
        if (student.getStudentId() != null) {
            URI uri = URI.create(getUriStringComponent(student.getStudentId()));
            logger.info("PUT student: {}", uri);
            restTemplate.put(uri, student);
            return student;
        } else {
            HttpEntity<Student> request = new HttpEntity<>(student);
            URI uri = URI.create(getUriStringComponent());
            logger.info("POST student: {}", uri);
            URI location = restTemplate.postForLocation(uri, request);
            logger.info("GET created student: {}", location);
            return restTemplate.getForObject(location, Student.class);
        }
    }

    @Override
    public void deleteStudent(Integer studentId) {
        URI uri = buildUri(getResourcePath(studentId), null);
        logger.info("DELETE student: {}", uri);
        restTemplate.delete(uri);
    }

    @Override
    public Page<Student> getStudenci(Pageable pageable) {
        URI uri = buildUri(getResourcePath(), pageable);
        logger.info("GET students: {}", uri);
        return getPage(uri);
    }

    @Override
    public Optional<Student> findByNrIndeksu(String nrIndeksu, Pageable pageable) {
        URI uri = buildUri(getResourcePath(), pageable, "nrIndeksu=" + nrIndeksu);
        logger.info("GET student by nrIndeksu: {}", uri);
        return Optional.ofNullable(restTemplate.getForObject(uri, Student.class));
    }

    @Override
    public Page<Student> findByNrIndeksuStartsWith(String nrIndeksuPrefix, Pageable pageable) {
        URI uri = buildUri(getResourcePath(), pageable, nrIndeksuPrefix + "*");
        logger.info("GET students by nrIndeksu prefix: {}", uri);
        return getPage(uri);
        }

        @Override
        public Page<Student> findByProjekt(Projekt projekt, Pageable pageable) {
        URI uri = buildUri(getResourcePath(), pageable, "projektId=" + projekt.getProjektId());
        logger.info("GET students by projekt: {}", uri);
        return getPage(uri);
        }

        private Page<Student> getPage(URI uri) {
        ParameterizedTypeReference<RestResponsePage<Student>> responseType =
        new ParameterizedTypeReference<RestResponsePage<Student>>() {};
        return restTemplate.exchange(uri, ServiceUtil.GET, null, responseType).getBody();
        }

		@Override
		public Page<Student> getStudenci(java.awt.print.Pageable pageable) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Page<Student> FindByNrIndeksu(String nr_indeksu, java.awt.print.Pageable pageable) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Page<Student> FindByNrIndeksu(String nr_indeksu, Pageable pageable) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getAllStudents() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Optional<Projekt> getProjekt(Map<String, ?> map) {
			// TODO Auto-generated method stub
			return Optional.empty();
		}

		@Override
		public Page<Projekt> searchByNazwa(String nazwa, Pageable pageable) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Optional<Projekt> getProjekt(Integer projektId2) {
			// TODO Auto-generated method stub
			return Optional.empty();
		}

		@Override
		public Page<Student> findByNazwiskoStartsWithIgnoreCase(String nazwisko, Pageable pageable) {
			// TODO Auto-generated method stub
			return null;
		}
}
