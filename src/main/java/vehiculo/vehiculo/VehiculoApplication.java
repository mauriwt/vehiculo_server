package vehiculo.vehiculo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages={"vehiculo.model"})
@EnableJpaRepositories(basePackages = "vehiculo.repository")
@ComponentScan(basePackages={"vehiculo.controller", "vehiculo.utilities","vehiculo.config"})
@EnableTransactionManagement
public class VehiculoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehiculoApplication.class, args);
	}

}
