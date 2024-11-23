package com.dev.adapter.`in`.web

import com.dev.adapter.`in`.web.request.RegisterBankAccountRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class RegisterBankAccountControllerTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val objectMapper: ObjectMapper,
) : BehaviorSpec ( {

  given("새로운 계좌 생성요청이 들어오면") {
    val request = RegisterBankAccountRequest(
      "1",
     "국민은행",
     "110-123-456789",
      isValid = true
    )

    `when` ("유효한 요청일 경우") {
      val response = mockMvc.post("/bank-accounts") {
        contentType = MediaType.APPLICATION_JSON
        content = objectMapper.writeValueAsString(request)
      }

      then("계좌가 성공적으로 등록 되어야 한다"){
          response.andExpect {
              status { isOk() }
              content { contentType(MediaType.APPLICATION_JSON) }
              jsonPath("$.membershipId") { value("1") }
              jsonPath("$.bankName") { value("국민은행") }
              jsonPath("$.bankAccountNumber") { value("110-123-456789") }
          }
      }
    }
  }
}) {
 companion object {
  private val objectMapper = ObjectMapper().apply {
    registerModule(KotlinModule.Builder().build())
    }
  }
}
