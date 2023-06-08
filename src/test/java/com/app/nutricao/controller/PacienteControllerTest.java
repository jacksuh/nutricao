package com.app.nutricao.controller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PacienteControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    @DisplayName("Teste erro 400")
    void criarPaciente() throws Exception{
        var response = mvc.perform(post("/paciente"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Teste  201")
    void salvarPaciente() throws Exception{

        String json = "{\"nome\":\"Jackson\",\"pesoAtual\":\"82\"}";

        var response = mvc.perform(
                        post("/paciente")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }


    @Test
    @DisplayName("Test http GET 200")
    void getAllPacientes()  throws Exception{
        var response = mvc
                .perform(get("/paciente")
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }


    @Test
    @DisplayName("Test http update 200")
    void updatePaciente() throws Exception{

        String json = "{\"nome\":\"Jackson\",\"pesoAtual\":\"82\"}";

        var response = mvc.perform(
                        put("/ticket/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }


    @Test
    @DisplayName("Teste Delete")
    void deletarPaciente() throws Exception {

        var response = mvc
                .perform(delete("/login/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
}