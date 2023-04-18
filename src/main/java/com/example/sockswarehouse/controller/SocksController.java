package com.example.sockswarehouse.controller;
import io.swagger.v3.oas.annotations.Operation;
import com.example.sockswarehouse.entity.SocksEntity;
import com.example.sockswarehouse.service.SocksService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService socksService;


    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/income")
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {@Content(mediaType = "*/*", schema = @Schema(implementation = SocksEntity.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    public ResponseEntity<SocksEntity> incomeSocks(@RequestParam String color,
                                                   @RequestParam Integer cottonPart,
                                                   @RequestParam Integer quantity) {

        return ResponseEntity.ok(socksService.incomeSocks(color, cottonPart, quantity));
    }

    @GetMapping
    @Operation(
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content =
                            {@Content(mediaType = "*/*", schema = @Schema(implementation = SocksEntity.class))
                            }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
            })
    public ResponseEntity<Collection<SocksEntity>> getSocks(@RequestParam String color,
                                                            @RequestParam com.example.sockswarehouse.operation.Operation operation,
                                                            @RequestParam Integer cottonPart) {
        return ResponseEntity.ok(socksService.getSocks(color, operation, cottonPart));
    }

    @PostMapping("/outcome")
    @io.swagger.v3.oas.annotations.Operation(
            summary = "outcomeSocks",
            operationId = "outcomeSocks",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content =
                            {@Content(mediaType = "*/*", schema = @Schema(implementation = SocksEntity.class))
                            }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
            })
    public ResponseEntity<SocksEntity> outcomeSocks(@RequestParam String color,
                                              @RequestParam Integer cottonPart,
                                              @RequestParam Integer quantity) {
        return ResponseEntity.ok(socksService.outcomeSocks(color, cottonPart, quantity));
    }
}
