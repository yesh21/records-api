package com.recordsapi.exception;

import com.netflix.graphql.types.errors.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class GraphQLExceptionHandler implements DataFetcherExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GraphQLExceptionHandler.class);

    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters params) {
        Throwable exception = params.getException();

        logger.error("GraphQL error at {}: {}", params.getPath(), exception.getMessage(), exception);

        GraphQLError error;

        if (exception instanceof StudentNotFoundException) {
            error = handleStudentNotFound((StudentNotFoundException) exception, params);
        } else if (exception instanceof InvalidInputException) {
            error = handleValidationError((InvalidInputException) exception, params);
        } else {
            error = handleGenericError(exception, params);
        }

        DataFetcherExceptionHandlerResult result = DataFetcherExceptionHandlerResult.newResult()
                .error(error)
                .build();

        return CompletableFuture.completedFuture(result);
    }

    private GraphQLError handleStudentNotFound(StudentNotFoundException e, DataFetcherExceptionHandlerParameters params) {
        logger.error("Student not found: {}", e.getMessage());
        return GraphqlErrorBuilder.newError()
                .message(e.getMessage())
                .path(params.getPath())
                .errorType(ErrorType.NOT_FOUND)
                .build();
    }

    private GraphQLError handleValidationError(InvalidInputException e, DataFetcherExceptionHandlerParameters params) {
        logger.error("Validation error/s: {}", e.getMessage());
        return GraphqlErrorBuilder.newError()
                .message("Invalid input: " + e.getMessage())
                .path(params.getPath())
                .errorType(ErrorType.BAD_REQUEST)
                .build();
    }

    private GraphQLError handleGenericError(Throwable e, DataFetcherExceptionHandlerParameters params) {
        logger.error("Unexpected error: {}", e.getMessage(), e);
        return GraphqlErrorBuilder.newError()
                .message("Something went wrong.")
                .path(params.getPath())
                .errorType(ErrorType.INTERNAL)
                .build();
    }
}
