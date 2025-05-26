package com.example.Soporte.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/soportes")
public class SoporteController {
    
    @Autowired
    private SoporteService soporteService;

    

}
