package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.config.SpringDocConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/*
Esta es una anotación compuesta que combina varias anotaciones, incluyendo @Configuration,
@EnableAutoConfiguration y @ComponentScan. Se utiliza para marcar la clase principal de la
aplicación Spring Boot y habilita la autoconfiguración y el escaneo de componentes.
 */
@SpringBootApplication
@EnableConfigurationProperties(SpringDocConfig.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
