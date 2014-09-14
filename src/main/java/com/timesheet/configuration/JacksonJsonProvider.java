package com.timesheet.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.timesheet.constants.ApplicationConstants;

@Provider
@Produces({ MediaType.APPLICATION_JSON, "text/json" })
@Consumes({ MediaType.APPLICATION_JSON, "text/json" })
public class JacksonJsonProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object> {

	private ObjectMapper objectMapper = null;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat(ApplicationConstants.STANDARD_DATE_FORMAT);
	
	private ObjectMapper getObjectMapper() {
		
		if(objectMapper == null) {
			
			objectMapper = new ObjectMapper();
			
			// enables JAXB annotations to be recognized for objects and PropertyNameAnnotationIntrospector is written to support XMLElementWrapper and XMLElement 
			JaxbAnnotationIntrospector jaxbAnnotationIntrospector = new JaxbAnnotationIntrospector(objectMapper.getTypeFactory());
			PropertyNameAnnotationIntrospector annotationIntrospector = new PropertyNameAnnotationIntrospector();
			
			AnnotationIntrospectorPair annotationIntrospectorPair = new AnnotationIntrospectorPair(annotationIntrospector, jaxbAnnotationIntrospector);
			objectMapper.getSerializationConfig().with(annotationIntrospectorPair);
			objectMapper.getDeserializationConfig().with(annotationIntrospectorPair);
			
			// disabling serialization of not null properties
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			
			// disables feature to throw exception when unknown fields are passed in payload
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			// standard date format for object mapper
			objectMapper.setDateFormat(new SimpleDateFormat(ApplicationConstants.STANDARD_DATE_FORMAT));

			// deserializer to parse date fields
			SimpleModule dateDeserializer = new SimpleModule("DateDeserializer", Version.unknownVersion());
			dateDeserializer.addDeserializer(Date.class, new DateDeserializer(Date.class));
			objectMapper.registerModule(dateDeserializer);
			
			// deserializer to parse empty or invalid values for int fields
			SimpleModule emptyJsonStringAsInt = new SimpleModule("EmptyJsonStringAsInt", Version.unknownVersion());
			emptyJsonStringAsInt.addDeserializer(int.class, new EmptyJsonStringAsIntDeserializer(int.class));
			objectMapper.registerModule(emptyJsonStringAsInt);
			
			// deserializer to parse empty or invalid values for Integer fields
			SimpleModule emptyJsonStringAsInteger = new SimpleModule("EmptyJsonStringAsInteger", Version.unknownVersion());
			emptyJsonStringAsInteger.addDeserializer(Integer.class, new EmptyJsonStringAsIntegerDeserializer(Integer.class));
			objectMapper.registerModule(emptyJsonStringAsInteger);
			
			// deserializer to parse empty or invalid values for boolean fields
			SimpleModule emptyJsonStringAsBooleanPrimitive = new SimpleModule("EmptyJsonStringAsBooleanPrimitive", Version.unknownVersion());
			emptyJsonStringAsBooleanPrimitive.addDeserializer(boolean.class, new EmptyJsonStringAsBooleanPrimitiveDeserializer(boolean.class));
			objectMapper.registerModule(emptyJsonStringAsBooleanPrimitive);
			
			// deserializer to parse empty or invalid values for Boolean fields
			SimpleModule emptyJsonStringAsBoolean = new SimpleModule("EmptyJsonStringAsBoolean", Version.unknownVersion());
			emptyJsonStringAsBoolean.addDeserializer(Boolean.class, new EmptyJsonStringAsBooleanDeserializer(Boolean.class));
			objectMapper.registerModule(emptyJsonStringAsBoolean);
		}
		
		return objectMapper;
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException,
			WebApplicationException {
		
		StringWriter writer = new StringWriter();
		IOUtils.copy(entityStream, writer);
		String request = writer.toString();
		
		// To handle empty payloads
		if (StringUtils.isEmpty(request)) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		
		try {
			return getObjectMapper().readValue(request, type);
		} catch (Exception e) {
			throw new WebApplicationException(e,Response.Status.BAD_REQUEST);
		} finally {
			entityStream.close();
		}
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public long getSize(Object o, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(Object o, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException,
			WebApplicationException {

		try {
			getObjectMapper().writeValue(entityStream, o);
		} finally {
			entityStream.close();
		}
	}
	
	
	private class EmptyJsonStringAsIntDeserializer extends StdDeserializer<Integer> {
		  
		private static final long serialVersionUID = 1L;
		
		protected EmptyJsonStringAsIntDeserializer(Class<?> vc) {
			super(vc);
		}
		
		@Override
		public Integer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			try {
				return Integer.parseInt(jp.getValueAsString());
			} catch(Exception e) {
				return 0;
			}
		}
	}
	
	private class EmptyJsonStringAsIntegerDeserializer extends StdDeserializer<Integer> {
		  
		private static final long serialVersionUID = 1L;
		
		protected EmptyJsonStringAsIntegerDeserializer(Class<?> vc) {
			super(vc);
		}
		
		@Override
		public Integer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			try {
				return Integer.parseInt(jp.getValueAsString());
			} catch(Exception e) {
				return null;
			}
		}
	}
	
	private class EmptyJsonStringAsBooleanPrimitiveDeserializer extends StdDeserializer<Boolean> {
		  
		private static final long serialVersionUID = 1L;
		
		protected EmptyJsonStringAsBooleanPrimitiveDeserializer(Class<?> vc) {
			super(vc);
		}
		
		@Override
		public Boolean deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			try {
				return Boolean.parseBoolean(jp.getValueAsString());
			} catch(Exception e) {
				return false;
			}
		}
	}
	
	private class EmptyJsonStringAsBooleanDeserializer extends StdDeserializer<Boolean> {
		
		private static final long serialVersionUID = 1L;

		protected EmptyJsonStringAsBooleanDeserializer(Class<?> vc) {
			super(vc);
		}
		
		@Override
		public Boolean deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			try {
				return Boolean.parseBoolean(jp.getValueAsString());
			} catch(Exception e) {
				return null;
			}
		}
	}
	
	private class PropertyNameAnnotationIntrospector extends AnnotationIntrospector implements Versioned,Serializable {

		private static final long serialVersionUID = 1L;

		@Override
		public Version version() {
			return VersionUtil.versionFor(getClass());
		}

		@Override
		public PropertyName findNameForDeserialization(Annotated annotation) {
			XmlElementWrapper elementWrapper = annotation.getAnnotation(XmlElementWrapper.class);
			XmlElement element = annotation.getAnnotation(XmlElement.class);
			
			if(elementWrapper != null) {
				return new PropertyName(elementWrapper.name());
			} else if(element != null) {
				return new PropertyName(element.name());
			} else {
				return super.findNameForDeserialization(annotation);
			}	
		}
		
		@Override
		public PropertyName findNameForSerialization(Annotated annotation) {
			XmlElementWrapper elementWrapper = annotation.getAnnotation(XmlElementWrapper.class);
			XmlElement element = annotation.getAnnotation(XmlElement.class);
			
			if(elementWrapper != null) {
				return new PropertyName(elementWrapper.name());
			} else if(element != null) {
				return new PropertyName(element.name());
			} else {
				return super.findNameForSerialization(annotation);
			}
		}
	}
	
	private class DateDeserializer extends StdDeserializer<Date> {
		
		private static final long serialVersionUID = 1L;
		protected DateDeserializer(Class<?> vc) {
			super(vc);
		}
		
		@Override
		public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			try {
				return dateFormat.parse(jp.getValueAsString());
			} catch(Exception e) {
				return null;
			}
		}
	}
}