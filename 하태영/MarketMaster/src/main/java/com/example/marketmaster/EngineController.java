package com.example.marketmaster;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/engine")
@RequiredArgsConstructor
public class EngineController {
    private final GbmEngineService engineService;

    @PostMapping("/trigger/{type}")
    public ResponseEntity<String> triggerPattern(@PathVariable String type) {
        engineService.injectPattern(type);
        return ResponseEntity.ok(type + " pattern injected");
    }
}