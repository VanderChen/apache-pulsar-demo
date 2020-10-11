package org.vander.pulsardemo.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pulsar")
public class PulsarController {
    @PostMapping(path = "/posttest", consumes = "text/plain")
    public void testPostRequest(@RequestBody String input) throws Exception{
        System.out.print("Get: " + input);
    }
}
