package ar.edu.utn.frc.tup.lciii.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean("mergerMapper")
    /*
    Esta anotación se utiliza para indicar inequivocamente cual es el nombre que
    tendrá un Bean. De esta manera podemos tener mas de Bean del mismo tipo en al ApplicationContext.
     */
    //@Qualifier("mergerMapper")
    public ModelMapper mergerMapper() {
        ModelMapper mapper =  new ModelMapper();
        mapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
