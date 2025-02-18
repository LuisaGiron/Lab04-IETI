package org.adaschool.Weather.controller;

import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class WeatherReportControllerTest {
    private MockMvc mockMvc;

    @Mock
    private WeatherReportService weatherReportService;

    @InjectMocks
    private WeatherReportController weatherReportController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(weatherReportController).build();
    }

    @Test
    void testGetWeatherReport() throws Exception {
        double latitude = 37.8267;
        double longitude = -122.4233;
        WeatherReport mockReport = new WeatherReport();
        mockReport.setTemperature(25.0);
        mockReport.setHumidity(60.0);

        when(weatherReportService.getWeatherReport(latitude, longitude)).thenReturn(mockReport);

        mockMvc.perform(get("/v1/api/weather-report")
                        .param("latitude", String.valueOf(latitude))
                        .param("longitude", String.valueOf(longitude))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature").value(25.0))
                .andExpect(jsonPath("$.humidity").value(60.0));
        Mockito.verify(weatherReportService).getWeatherReport(latitude, longitude);
    }
}
