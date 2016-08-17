package com.naptex.evtsvc.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonFormatter
{
	private final JsonFactory factory = new JsonFactory();

	public <T> String getJson(Iterator<T> iter) throws IOException
	{
		Writer out = new StringWriter();
		JsonGenerator gen = getJsonGenerator(out);
		gen.writeStartArray();
		while (iter.hasNext())
		{
			T obj = iter.next();
			gen.writeObject(obj);
		}
		gen.writeEndArray();
		gen.flush();
		gen.close();
		out.flush();
		out.close();
		String json = out.toString();
		return json;
	}

	public <T> String getJson(T object) throws IOException
	{
		Writer out = new StringWriter();
		JsonGenerator gen = getJsonGenerator(out);
		gen.writeObject(object);
		gen.flush();
		gen.close();
		out.flush();
		out.close();
		String json = out.toString();
		return json;
	}

	private JsonGenerator getJsonGenerator(Writer out) throws IOException
	{
		JsonGenerator gen = factory.createGenerator(out);
		gen.setCodec(new ObjectMapper());
		gen.useDefaultPrettyPrinter();
		return gen;
	}
}
