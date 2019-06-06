package dali;

import org.mapdb.DB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * = Atlas_BaconApplication
 *
 * TODO Auto-generated class documentation
 *
 */
@SpringBootApplication
public class Atlas_BaconApplication { 
	
	@Bean
	public RestTemplate restClient() {
		return new RestTemplate();
	}
	
	public static DB db;
	
	/**
	 * TODO Auto-generated method documentation
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		
		SpringApplication.run(Atlas_BaconApplication.class, args);
	}
	
	
}