package fr.formation.api;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.request.HelloFormRequest;
import fr.formation.response.HelloFormResponse;

@RestController
@RequestMapping("/api/hello")
public class HelloApiController {
    @GetMapping
    public String hello() {
        return "hello";
    }

    @PostMapping
    public HelloFormResponse postHello(@RequestBody HelloFormRequest request) {
        // return HelloFormResponse.builder()
        //     .username("L'AUTRE USER")
        //     .build();

        HelloFormResponse resp = new HelloFormResponse();

        // resp.setUsername(request.getUsername());
        BeanUtils.copyProperties(request, resp);

        return resp;
    }
}
