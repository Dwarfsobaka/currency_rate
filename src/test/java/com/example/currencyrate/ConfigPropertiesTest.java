package com.example.currencyrate;

import com.example.currencyrate.config.ConfigProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = ConfigProperties.class)
@TestPropertySource("classpath:config-test.properties")

class ConfigPropertiesTest {

    @Autowired
    private ConfigProperties configProperties;

    @Test
    void configPropertiesAllFieldsAreSet() {
        assertEquals("https://openexchangerates.org/api", configProperties.getUrlRate());
     //   assertEquals("RUB", configProperties.getCurrency());
        assertEquals("df639a0a24f343dfa7a1f18c014c2c52", configProperties.getAppId());
        assertEquals("https://api.giphy.com/v1/gifs/search", configProperties.getUrlGif());
        assertEquals("d9EUTeOIt4qjftWYzHKkMcTOMUwh9m7G", configProperties.getApiKey());
        assertEquals("https://openexchangerates.org/api", configProperties.getUrlRate());
        assertEquals("rich", configProperties.getRequestWordUp());
        assertEquals("broke", configProperties.getRequestWordDown());
        assertEquals("1", configProperties.getLimitGif());
    }

    @Test()
    void configPropertiesUrlRateError() {
        Assertions.assertNotEquals("https://openexchangerates.org/", configProperties.getUrlRate());
    }

   // @Test()
   // void configPropertiesCurrencyError() {
//        Assertions.assertNotEquals("FG", configProperties.getCurrency());
//    }

    @Test()
    void configPropertiesAppIdError() {
        Assertions.assertNotEquals("7a1f18c014c2c52", configProperties.getAppId());
    }

    @Test()
    void configPropertiesUrlGifError() {
        Assertions.assertNotEquals("https://api.giphy.com/v1/", configProperties.getUrlGif());
    }

    @Test()
    void configPropertiesApiKeyError() {
        Assertions.assertNotEquals("jftWYzHKkMcTOMUwh9m7G", configProperties.getApiKey());
    }

    @Test()
    void configPropertiesRequestWordUpError() {
        Assertions.assertNotEquals("gggg", configProperties.getRequestWordUp());
    }

    @Test()
    void configPropertiesRequestWordDownError() {
        Assertions.assertNotEquals("fff", configProperties.getRequestWordDown());
    }

    @Test()
    void configPropertiesLimitGifError() {
        Assertions.assertNotEquals("2", configProperties.getLimitGif());
    }

    @Test()
    void configPropertiesSetRequestWordUpNull() {
        assertThrows(NullPointerException.class,
                ()-> {configProperties.setRequestWordUp(null);});
    }
    @Test()
    void configPropertiesSetRequestWordDownNull() {
        assertThrows(NullPointerException.class,
                ()-> {configProperties.setRequestWordDown(null);});
    }
    @Test()
    void configPropertiesSetLimitGifNull() {
        assertThrows(NullPointerException.class,
                ()-> {configProperties.setLimitGif(null);});
    }
    @Test()
    void configPropertiesSetUrlGifNull() {
        assertThrows(NullPointerException.class,
                ()-> {configProperties.setUrlGif(null);});
    }
    @Test()
    void configPropertiesSetApiKeyNull() {
        assertThrows(NullPointerException.class,
                ()-> {configProperties.setApiKey(null);});
    }
    @Test()
    void configPropertiesSetUrlRateNull() {
        assertThrows(NullPointerException.class,
                ()-> {configProperties.setUrlRate(null);});
    }
//    @Test()
//    void configPropertiesSetCurrencyNull() {
//        assertThrows(NullPointerException.class,
//                ()-> {configProperties.setCurrency(null);});
//    }
    @Test()
    void configPropertiesSetAppIdNull() {
        assertThrows(NullPointerException.class,
                ()-> {configProperties.setAppId(null);});
    }



}