package com.minkj1992.springrestapi.events;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Controller
@RequestMapping(value="/api/events",produces = MediaTypes.HAL_JSON_VALUE) //@TODO: HAL_FORMS_JSON_VALUE vs HAL_JSON 차이점
public class EventController {

    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event) {
        URI createdUri = linkTo(EventController.class).slash("{id}").toUri();
        event.setId(10);    //temp
        return ResponseEntity.created(createdUri).body(event);
    }
}
