package dev.aga.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping
@RestController
class SomeController {

  @PostMapping("/endpoint")
  suspend fun endpoint(@RequestBody arg: Map<String,String>): ResponseEntity<Map<String,String>> {
    return ResponseEntity.ok(mapOf("test" to "test") + arg)
  }
}
