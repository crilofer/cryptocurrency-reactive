package com.crilofer.controller.cryptocurrency;

import com.crilofer.controller.dto.response.MessageDto;
import com.crilofer.controller.dto.response.cryptocurrency.CryptocurrencyTwitterFeedResponseDto;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/cryptocurrency")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Cryptocurrency", description = "Cryptocurrency information operations")
public interface CryptocurrencyController {

    @GET
    @Path("/twitter/feed")
    @Operation(summary = "Cryptocurrency Twitter feed",
            description = "Operation to show Cryptocurrency official Twitter account feed")
    @APIResponse(responseCode = "200",
            description = "Login operation successful",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CryptocurrencyTwitterFeedResponseDto.class)))
    @APIResponse(responseCode = "404",
            description = "Cryptocurrency not found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessageDto.class)))
    @APIResponse(responseCode = "500",
            description = "Server error",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessageDto.class)))
    Uni<Response> getTwitterFeed(@QueryParam("symbol") @NotNull @NotEmpty @Valid String currencySymbol);
}
