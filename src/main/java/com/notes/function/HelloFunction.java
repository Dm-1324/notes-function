package com.notes.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.time.Instant;
import java.util.Optional;

/**
 * A serverless HTTP endpoint: GET /api/greet?name=Dhruv
 * Runs only when called (HTTP trigger) and is billed per execution.
 */
public class HelloFunction {

    @FunctionName("greet")
    public HttpResponseMessage greet(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        String name = request.getQueryParameters().getOrDefault("name", "there");
        context.getLogger().info("greet called for " + name);

        return request.createResponseBuilder(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body("{\"message\":\"Hello " + name + " from Azure Functions\",\"time\":\"" + Instant.now() + "\"}")
                .build();
    }
}
