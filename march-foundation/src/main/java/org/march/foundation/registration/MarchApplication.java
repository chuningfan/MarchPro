package org.march.foundation.registration;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

public class MarchApplication extends Application {
	
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		
        // register features
        classes.add(JacksonFeature.class);
        classes.add(MultiPartFeature.class);
		return classes;
	}
	
}
