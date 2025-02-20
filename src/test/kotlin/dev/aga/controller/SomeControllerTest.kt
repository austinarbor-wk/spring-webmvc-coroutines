package dev.aga.controller

import org.hamcrest.core.IsEqual
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch

@SpringBootTest
@AutoConfigureMockMvc
class SomeControllerTest(@Autowired private val mockMvc: MockMvc) {

  @Test
  fun `normal - sync`() {
    mockMvc.post("/endpoint") {
      contentType = MediaType.APPLICATION_JSON
      content = """{"a": "b"}"""
    }.andExpect {
      status { isEqualTo(HttpStatus.OK.value()) }
      jsonPath("$.test", IsEqual("test"))
    }
  }

  @Test
  fun `normal - async`() {
    val result =
      mockMvc.post("/endpoint"){
        contentType = MediaType.APPLICATION_JSON
        content = """{"a": "b"}"""
      }
        .andExpect {
        status { isOk() }
        request { asyncStarted() }
      }.andReturn()

    val actions = mockMvc.perform(asyncDispatch(result))
    ResultActionsDsl(actions, mockMvc).andExpect {
      status { isEqualTo(HttpStatus.OK.value())}
      jsonPath("$.test", IsEqual("test"))
    }
  }
}
