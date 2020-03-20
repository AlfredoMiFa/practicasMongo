package com.mistborn.practicas.dao;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import com.mistborn.practicas.entity.Usuarios;

public class Busqueda implements Callable<Usuarios> {
	
	private static final Logger log = LoggerFactory.getLogger(Busqueda.class);
	
	private static final String USER = "userName";
	private static final String COLLECTION = "Usuarios";
	
	private String user;
	private long limit;
	private long skip;
	private MongoTemplate mongoTemplate;
	private int hilo;
	
	public Busqueda(String user, long limit, long skip, MongoTemplate mongoTemplate, int hilo) {
		super();
		this.user = user;
		this.limit = limit;
		this.skip = skip;
		this.mongoTemplate = mongoTemplate;
		this.hilo=hilo;
	}

	@Override
	public Usuarios call() throws Exception {
		Aggregation agg =Aggregation.newAggregation(
				Aggregation.match(Criteria.where(USER).is(user)),
				limit(limit),
				skip(skip));
		log.info("Busqueda: {}",agg.toString());
		log.info("Inicio: {}, hilo {}",LocalDateTime.now(),hilo);
		AggregationResults<Usuarios> rest = mongoTemplate.aggregate(agg, COLLECTION, Usuarios.class);
		log.info("Termino {}, hilo {}",LocalDateTime.now(), hilo);
		Usuarios usuario = rest.getMappedResults().stream().filter(Objects::nonNull).findAny().get();
		if(usuario!=null) {
			log.info("Hilo que lo encontro: {}",hilo);
		}
		return usuario;
	}
	
}
