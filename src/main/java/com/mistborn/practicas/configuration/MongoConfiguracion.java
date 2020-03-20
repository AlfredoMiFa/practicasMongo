package com.mistborn.practicas.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClientURI;

@Configuration
public class MongoConfiguracion {
	
	@Primary
	@Bean
	@Qualifier(value = "primaryMongoTemplate")
	public MongoTemplate mongoTemplate() {
		String url = "mongodb://abahafart:Exceptionabahafart3255@192.168.100.5:27017/DESARROLLO";
		MongoClientURI uri = new MongoClientURI(url);
		return new MongoTemplate(new SimpleMongoDbFactory(uri));
	}
	
}
