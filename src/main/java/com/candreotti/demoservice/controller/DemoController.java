package com.candreotti.demoservice.controller;

import com.candreotti.demoservice.domain.HelloRequest;
import com.candreotti.demoservice.dto.HelloResponseDto;
import com.candreotti.demoservice.service.HelloService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/hello")
public class DemoController {

    @Autowired
    private HelloService helloService;

    @GetMapping
    @ApiOperation("Get HelloResponse")
    public ResponseEntity<HelloResponseDto> helloGet(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String password) {
        HelloResponseDto helloResponse = helloService.greeting(
                HelloRequest.builder()
                        .name(name)
                        .age(age)
                        .password(password)
                        .build());

        return ResponseEntity.ok()
                .body(helloResponse);
    }

    @PostMapping
    @ApiOperation("Post HelloRequest")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "HelloRequest posted"),
    })
    public ResponseEntity<HelloResponseDto> helloPost(@RequestBody HelloRequest request) {

        HelloResponseDto helloResponse = helloService.greeting(request);

        return ResponseEntity.ok()
                .body(helloResponse);
    }

}
