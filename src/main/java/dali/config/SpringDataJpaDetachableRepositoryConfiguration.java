package dali.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import dali.Atlas_BaconApplication;
import io.springlets.data.jpa.repository.support.DetachableJpaRepositoryImpl;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = DetachableJpaRepositoryImpl.class,basePackageClasses = Atlas_BaconApplication.class)
/**
 * = SpringDataJpaDetachableRepositoryConfiguration
 TODO Auto-generated class documentation
 *
 */
public class SpringDataJpaDetachableRepositoryConfiguration {
}
