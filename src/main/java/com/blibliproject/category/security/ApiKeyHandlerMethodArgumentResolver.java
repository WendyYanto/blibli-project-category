package com.blibliproject.category.security;

import com.blibliproject.category.exception.ApiKeyNotFoundException;
import com.blibliproject.category.exception.ApiKeyNotIdentifiedException;
import org.springframework.core.MethodParameter;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class ApiKeyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return ApiKey.class.equals(parameter.getParameterType());
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {
        return Mono.fromSupplier( () -> {
           String value = exchange.getRequest().getHeaders().getFirst("API-KEY");

           if(value == null){
               throw new ApiKeyNotFoundException("ERROR API KEY NOT FOUND");
           }else if(!value.equals("categories")){
                throw new ApiKeyNotIdentifiedException("ERROR API KEY NOT IDENTIFIED");
           }else{
               return new ApiKey(value);
           }
        });
    }
}
