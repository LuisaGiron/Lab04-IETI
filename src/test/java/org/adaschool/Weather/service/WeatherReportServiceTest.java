package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;


    @Test
    void testGetWeatherReport() {

        double latitude = 37.8267;
        double longitude = -122.4233;

        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(25.0);
        main.setHumidity(60.0);

        WeatherApiResponse mockApiResponse = new WeatherApiResponse();
        mockApiResponse.setMain(main);


        when(restTemplate.getForObject(anyString(), Mockito.eq(WeatherApiResponse.class)))
                .thenReturn(mockApiResponse);


        WeatherReport result = weatherReportService.getWeatherReport(latitude, longitude);


        assertEquals(25.0, result.getTemperature(), "La temperatura no coincide");
        assertEquals(60.0, result.getHumidity(), "La humedad no coincide");
    }

}
