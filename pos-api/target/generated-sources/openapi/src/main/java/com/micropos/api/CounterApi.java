/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.4.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.micropos.api;

import com.micropos.dto.CartDto;
import com.micropos.dto.ErrorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.codec.multipart.Part;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-27T22:41:41.956848-07:00[America/Los_Angeles]")
@Validated
@Tag(name = "counter", description = "the counter API")
public interface CounterApi {

    /**
     * POST /counter/checkout : checkout a cart
     *
     * @param cartDto The cart to checkout. (required)
     * @return Expected response to a valid request (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "checkout",
        summary = "checkout a cart",
        tags = { "counter" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  Double.class))),
            @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDto.class)))
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/counter/checkout",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default Mono<ResponseEntity<Double>> checkout(
        @Parameter(name = "CartDto", description = "The cart to checkout.", required = true, schema = @Schema(description = "")) @Valid @RequestBody Mono<CartDto> cartDto,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

}