//package com.algebra.authentication.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//
//import springfox.documentation.swagger.web.SwaggerResource;
//import springfox.documentation.swagger.web.SwaggerResourcesProvider;
//
//@Component
//@Primary
//public class SwaggerProvider implements SwaggerResourcesProvider {
//
//    public static final String API_URI = "/v2/api-docs";
//
//    public static final String EUREKA_SUB_PRIX = "ReactiveCompositeDiscoveryClient_";
//
//    private static final String GATEWAY_STR = "mdg-gateway";
//
//    private final DiscoveryClientRouteDefinitionLocator routeLocator;
//
//    public SwaggerProvider(DiscoveryClientRouteDefinitionLocator routeLocator) {
//
//        this.routeLocator = routeLocator;
//
//    }
//
//    @Override
//
//    public List<SwaggerResource> get() {
//
//        List<SwaggerResource> resources = new ArrayList<>();
//
//        // List<String> routes = new ArrayList<>();
//
//        // 从DiscoveryClientRouteDefinitionLocator 中取出routes，构造成swaggerResource
//
//        routeLocator.getRouteDefinitions().subscribe(routeDefinition -> {
//
//            resources.add(swaggerResource(routeDefinition.getId(),
//                    routeDefinition.getPredicates().get(0).getArgs().get("pattern").replace("/**", API_URI)));
//
//        });
//
//        // 移除前缀,移除网关自己
//        resources.removeIf(x -> x.getName().toLowerCase().indexOf(GATEWAY_STR) >= 0);
//
//        resources.forEach(action -> {
//            action.setName(action.getName().substring(EUREKA_SUB_PRIX.length()));
//        });
//
//        return resources;
//
//    }
//
//    private SwaggerResource swaggerResource(String name, String location) {
//
//        SwaggerResource swaggerResource = new SwaggerResource();
//
//        swaggerResource.setName(name);
//
//        swaggerResource.setLocation(location);
//
//        swaggerResource.setSwaggerVersion("2.0");
//
//        return swaggerResource;
//
//    }
//
//}