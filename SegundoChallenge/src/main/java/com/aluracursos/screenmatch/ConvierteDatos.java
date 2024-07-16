package com.aluracursos.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos {

	private final ObjectMapper objectMapper = new ObjectMapper();

	public <T> T obtenerDatos(String json, Class<T> tipo) {
		try {
			return objectMapper.readValue(json, tipo);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error al convertir datos JSON", e);
		}
	}
}
