package org.iplacex.proyectos.discografia.artistas;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArtistaController.class)
public class ArtistaControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void HandleInsertArtistaRequest() throws Exception {
		Artista artista = new Artista();
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(artista);
		this.mockMvc.perform(post("/api/Artista").content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$._id").value("<value>"))
			.andExpect(jsonPath("$.nombre").value("<value>"))
			.andExpect(jsonPath("$.estilos").value("<value>"))
			.andExpect(jsonPath("$.anioFundation").value("<value>"))
			.andExpect(jsonPath("$.estaActivo").value("<value>"));
	}
}
