package com.mmhtoo.note.controller;

import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:routes.properties")
public class BaseController {
}
