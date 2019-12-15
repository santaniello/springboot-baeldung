package com.baeldung.springboot.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        configureJacksonConverter(converters);
    }


    public void configureJacksonConverter(List<HttpMessageConverter<?>> converters){
        Optional<HttpMessageConverter<?>> jacksonConverterFound = converters.stream()
                                                  .filter(c -> c instanceof AbstractJackson2HttpMessageConverter)
                                                  .findFirst();

        if(jacksonConverterFound.isPresent()){
            final AbstractJackson2HttpMessageConverter converter = (AbstractJackson2HttpMessageConverter) jacksonConverterFound.get();
            // Exibindo o Json mais "bonito"...
            converter.getObjectMapper().enable(INDENT_OUTPUT);
            // Falha se for enviado uma propriedade desconhecida...
            converter.getObjectMapper().enable(FAIL_ON_UNKNOWN_PROPERTIES); // Falha se  for enviado uma propriedade desconhecida...
        }
    }
}
