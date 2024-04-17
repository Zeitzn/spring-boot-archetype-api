package com.archetype.api.infraestructure.rest;

import com.archetype.api.configuration.PropertiesConfig;
import com.archetype.api.exception.NotFoundException;
import com.archetype.api.infraestructure.rest.response.HcmWorkerDto;
import com.archetype.api.infraestructure.rest.response.ResponseGeneralDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class HcmRestClient {
    @Qualifier("msApiRestTemplate")
    private final RestTemplate restTemplateHCM;
    private final PropertiesConfig properties;
    private final ModelMapper mapper;
    private static final String ERROR_MSG = "Ocurrió un error inesperado al comunicarse con HCM";

    public HcmWorkerDto getWorkerByPersonNumber(String personNumber) {
        try {
            String uri = String.format(properties.getHcmGateway().getFindWorkerByPersonNumber(), personNumber);
            ResponseEntity<ResponseGeneralDto<HcmWorkerDto>> response = performHttpRequest(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
            });
            HcmWorkerDto result = mapper.map(response.getBody().getData(), HcmWorkerDto.class);
            if(Objects.isNull(result.getWorkerPerson())) {
                throw NotFoundException.builder().code("40403").message(String.format("No se encontró un worker para el personNumber %s", personNumber)).build();
            }
            return result;
        } catch (RestClientException e) {
            throw new RestClientException("Error mientras se hacía la petición HTTP", e);
        }
    }

    private <T> ResponseEntity<T> performHttpRequest(String uri, HttpMethod method, HttpEntity<?> entity, ParameterizedTypeReference<T> responseType) {
        try {
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            messageConverters.add(converter);
            restTemplateHCM.setMessageConverters(messageConverters);

            ResponseEntity<T> response = restTemplateHCM.exchange(uri, method, entity, responseType);
            log.info("Response => {} ", response.getBody());
            if (response.getStatusCode() != HttpStatus.OK && response.getStatusCode() != HttpStatus.CREATED) {
                handleErrorResponse(response);
            }
            return response;
        } catch (HttpClientErrorException e) {
            handleHttpClientError(e);
        } catch (HttpServerErrorException e) {
            handleHttpServerError(e);
        } catch (Exception e) {
            handleGenericError(e);
        }
        return null;
    }

    // ERRORS
    private void handleErrorResponse(ResponseEntity<?> response) {
        log.error("Ocurrió un error al obtener datos de HCM: " + response.getStatusCode().value());
        throw new RestClientException(ERROR_MSG);
    }

    private void handleHttpClientError(HttpClientErrorException e) {
        log.error("Ocurrió un error al hacer la solicitud HTTP a HCM: " + e.getMessage());
        throw new RestClientException(ERROR_MSG);
    }

    private void handleHttpServerError(HttpServerErrorException e) {
        log.error("Ocurrió un error en el servidor al hacer la solicitud HTTP a HCM: " + e.getMessage());
        throw new RestClientException(ERROR_MSG);
    }

    private void handleGenericError(Exception e) {
        log.error("Ocurrió un error genérico: " + e.getMessage());
        throw new RestClientException(ERROR_MSG);
    }
}
