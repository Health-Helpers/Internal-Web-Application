package cat.ehh.web.persistence;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import cat.ehh.web.model.UserEHH;

@EnableTransactionManagement
@Configuration
public class EntityManagerConfiguration {

    @Bean
    public EntityManagerFactory entities() throws Exception {
        LocalContainerEntityManagerFactoryBean theEntityManager = new LocalContainerEntityManagerFactoryBean();
        theEntityManager.setPackagesToScan(UserEHH.class.getPackage().getName());
        theEntityManager.setJtaDataSource(dataSource());
        theEntityManager.setPersistenceUnitName("EHHWebPersistenceUnit");
        theEntityManager.setJpaVendorAdapter(jpaVendorAdapter());
        theEntityManager.setJpaProperties(jpaProperties());
        theEntityManager.afterPropertiesSet();
        return theEntityManager.getNativeEntityManagerFactory();
    }

    @Bean
    public Properties jpaProperties() {
        Properties props = new Properties();
        props.setProperty("eclipselink.ddl-generation", "drop-and-create-tables");
        props.setProperty("eclipselink.weaving", "false");
        props.setProperty("eclipselink.logging.level", "FINEST");
        props.setProperty("eclipselink.logging.parameters", "true");
        props.setProperty("eclipselink.target-server", "JBoss");
        props.setProperty("eclipselink.ddl-generation.output-mode","sql-script");
        props.setProperty("eclipselink.create-ddl-jdbc-file-name", "createDDL_ddlGeneration.jdbc");
        props.setProperty("eclipselink.drop-ddl-jdbc-file-name", "dropDDL_ddlGeneration.jdbc");
        return props;
    }

    @Bean
    public DataSource dataSource() throws Exception {
        return (DataSource)  new InitialContext().lookup("java:jboss/EHHDS");
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        EclipseLinkJpaVendorAdapter theVendorAdapter = new EclipseLinkJpaVendorAdapter();
        theVendorAdapter.setDatabase(Database.POSTGRESQL);
        return theVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(){ 
        return new JtaTransactionManager();
    }   
}