/**
 * @(#)JaxbJacksonObjectMapper.java
 * Copyright (c) 2013, Province of British Columbia.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JaxbJacksonObjectMapper extends ObjectMapper {

    /**
	 * Provides customizations to Jackson JSON serialization/deserialization. 
	 * See 'dispatcher-servlet.xml' 
	 */
	private static final long serialVersionUID = -9169995141937641645L;

	public JaxbJacksonObjectMapper() {
        //super();
		
		this.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        
        // allow root element in JSON requests 
        //this.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		
		// allow root element in JSON reponses.
        this.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        
        // remove nulls from JSON requests when object is null. 
        this.setSerializationInclusion(Include.NON_NULL);

    }

}