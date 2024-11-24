在前后端分离应用中设置`allowCredentials`属性，主要是在后端Spring Boot应用的跨域配置中进行操作。

1. **全局配置方式（通过实现WebMvcConfigurer接口）**
   - 创建一个配置类，比如`CorsConfig`，让这个类实现`WebMvcConfigurer`接口。
   - 在`addCorsMappings`方法中设置`allowCredentials`属性。例如：
```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
              .allowedOrigins("http://your - frontend - domain.com")
              .allowedMethods("GET", "POST", "PUT", "DELETE")
              .allowedHeaders("*")
              .allowCredentials(true);
    }
}
```
   - 注意，当`allowCredentials`设置为`true`时，`allowedOrigins`不能为`*`，必须明确指定前端应用的域名，这样才能保证安全地传递Cookie凭证信息。

2. **基于Filter方式（使用CorsFilter）**
   - 可以通过创建一个`CorsFilter`来配置跨域属性。首先需要导入相关的依赖，如`spring - web`。
   - 示例代码如下：
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
@Configuration
public class CorsFilterConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://your - frontend - domain.com");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
```
   - 同样，这里`allowCredentials`为`true`时，`allowedOrigins`要明确指定前端域名。这种方式更灵活，适合对跨域规则有更精细控制的场景。

在Spring Boot全局跨域配置（通过实现`WebMvcConfigurer`接口的`addCorsMappings`方法）时，有以下主要配置项：

- **allowedOrigins**：
    - 用于指定允许的跨域请求来源。可以是一个具体的域名（如`http://example.com`），也可以使用`*`来允许所有来源的请求。不过在生产环境中，使用`*`可能会有安全风险，最好明确指定可信的来源。
- **allowedMethods**：
    - 用来定义允许的HTTP请求方法，如`GET`、`POST`、`PUT`、`DELETE`、`OPTIONS`等。可以根据实际的API需求进行设置，例如，如果你的API只提供数据查询（`GET`方法）和数据更新（`POST`方法）功能，就可以配置为`allowedMethods("GET", "POST")`。
- **allowedHeaders**：
    - 用于指定允许的请求头。和`allowedOrigins`一样，可以使用`*`来允许所有请求头，但为了安全考虑，也可以明确列出允许的请求头，如`Content-Type`、`Authorization`等。
- **exposedHeaders**：
    - 这个配置项用于指定当响应时，哪些头信息可以暴露给浏览器。例如，在一些场景下，可能需要将自定义的响应头信息（如`X - Custom - Header`）暴露给前端，就可以通过这个配置项来设置。
- **allowCredentials**：
    - 该配置项用于指定是否允许浏览器发送Cookie等凭证信息。如果设置为`true`，则必须同时设置`allowedOrigins`为具体的域名，不能是`*`，因为浏览器安全策略要求凭证信息只能在指定的域中传递，以防止安全漏洞。
- **maxAge**：
    - 用于指定浏览器缓存预检请求（`OPTIONS`请求）结果的时间，单位是秒。例如，设置为`3600`表示浏览器可以在1小时内使用缓存的预检请求结果，而不需要每次都发送预检请求，从而提高性能。

1. **生成与管理**
   - **生成方**：
     - Cookie凭证信息通常是由服务器端生成。在Spring Boot应用中，当开启了用户认证（比如基于Spring Security）相关的功能后，服务器会在用户成功登录等操作后生成包含用户身份认证相关信息的Cookie。例如，在使用基于表单的身份认证时，用户提交正确的用户名和密码后，服务器的认证机制（如Spring Security的认证过滤器）会创建一个包含用户身份标识的Cookie，并将其发送回浏览器。
   - **管理方**：
     - 服务器负责管理Cookie凭证信息的有效期、内容更新等。服务器可以设置Cookie的过期时间，通过更新用户状态（如用户权限变更）来修改Cookie中的相关信息。浏览器则主要负责存储和在后续请求中携带这些Cookie凭证信息。当浏览器向同一域名下的服务器发起请求时，会自动带上符合规则的Cookie。

2. **没有allowCredentials的情况**
   - 如果`allowCredentials`设置为`false`，浏览器在跨域请求时不会发送Cookie凭证信息。这可能会导致一些需要用户认证的跨域操作无法正常进行。例如，在一个前后端分离的应用中，前端应用部署在一个域名（如`http://frontend.example.com`），后端API部署在另一个域名（如`http://backend.example.com`），如果`allowCredentials`为`false`，用户登录后，当前端向后端发起需要认证的请求（如获取用户特定数据）时，后端无法识别用户身份，因为浏览器没有发送包含用户认证信息的Cookie，从而可能返回未授权的错误。


在前后端分离应用中设置`allowCredentials`属性，主要是在后端Spring Boot应用的跨域配置中进行操作。

1. **全局配置方式（通过实现WebMvcConfigurer接口）**
   - 创建一个配置类，比如`CorsConfig`，让这个类实现`WebMvcConfigurer`接口。
   - 在`addCorsMappings`方法中设置`allowCredentials`属性。例如：
```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
              .allowedOrigins("http://your - frontend - domain.com")
              .allowedMethods("GET", "POST", "PUT", "DELETE")
              .allowedHeaders("*")
              .allowCredentials(true);
    }
}
```
   - 注意，当`allowCredentials`设置为`true`时，`allowedOrigins`不能为`*`，必须明确指定前端应用的域名，这样才能保证安全地传递Cookie凭证信息。

2. **基于Filter方式（使用CorsFilter）**
   - 可以通过创建一个`CorsFilter`来配置跨域属性。首先需要导入相关的依赖，如`spring - web`。
   - 示例代码如下：
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
@Configuration
public class CorsFilterConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://your - frontend - domain.com");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
```
   - 同样，这里`allowCredentials`为`true`时，`allowedOrigins`要明确指定前端域名。这种方式更灵活，适合对跨域规则有更精细控制的场景。

以下是在Spring Boot中配置全局跨域时，常用配置项的介绍（以通过实现`WebMvcConfigurer`接口的`addCorsMappings`方法为例），使用markdown格式呈现：

### 1. `allowedOrigins`
- **作用**：
用于指定允许的跨域请求来源，即哪些域名下的前端应用可以向当前Spring Boot后端发起跨域请求。
- **取值示例及说明**：
    - 可以是一个具体的域名，如`"http://example.com"`，表示只允许来自该域名的跨域请求。这种方式在明确知晓前端应用域名，且对安全性要求较高时使用，适合生产环境。
    - 也可以使用`"*"`，表示允许所有来源的请求，但在生产环境中出于安全考虑，一般不建议这样设置，因为可能会让任意域名的请求都能访问后端资源，存在安全隐患。

### 2. `allowedMethods`
- **作用**：
定义允许的HTTP请求方法，也就是限制前端能通过哪些方式（如`GET`、`POST`、`PUT`、`DELETE`等）来调用后端的API接口。
- **取值示例及说明**：
    - 例如`"GET", "POST", "PUT", "DELETE"`，表示允许这几种常见的HTTP请求方法进行跨域操作。可以根据后端API实际提供的功能和对外开放的接口情况，按需选择具体的方法进行配置，如只提供数据查询和新增功能的API，可配置为`"GET", "POST"`。

### 3. `allowedHeaders`
- **作用**：
用于指定允许的请求头。请求头中包含了很多请求相关的信息，比如请求的内容类型、认证信息等，通过这个配置项可以明确哪些请求头信息是允许跨域传递的。
- **取值示例及说明**：
    - 可以使用`"*"`，表示允许所有请求头跨域传递，但从安全角度出发，更建议明确列出允许的请求头，如`"Content-Type", "Authorization"`等，只允许这些必要的请求头进行跨域交互，以此增强安全性。

### 4. `exposedHeaders`
- **作用**：
指定当响应时，哪些头信息可以暴露给浏览器。在一些场景下，后端可能会添加一些自定义的响应头信息，若希望这些自定义头信息能被前端获取到，就需要通过该配置项进行设置。
- **取值示例及说明**：
    - 例如，设置为`"X-Custom-Header"`，表示允许将名为`X-Custom-Header`的自定义响应头暴露给浏览器，以便前端可以获取并处理相应的信息。

### 5. `allowCredentials`
- **作用**：
确定是否允许浏览器发送Cookie等凭证信息进行跨域请求。如果后端的某些接口需要依赖用户的认证信息（存在于Cookie中）来进行操作，就需要正确配置该属性。
- **取值示例及说明**：
    - 设置为`true`时，表示允许浏览器发送Cookie等凭证信息跨域，但此时`allowedOrigins`不能为`*`，必须明确指定具体的前端域名，以保障安全地传递认证相关的凭证信息，避免安全漏洞。若设置为`false`，浏览器在跨域请求时则不会发送Cookie等凭证信息。

### 6. `maxAge`
- **作用**：
用于指定浏览器缓存预检请求（`OPTIONS`请求）结果的时间，单位是秒。预检请求在跨域请求中用于提前验证请求是否被允许，通过设置缓存时间可以减少不必要的预检请求发送，提高性能。
- **取值示例及说明**：
    - 例如设置为`3600`，意味着浏览器可以在1小时（3600秒）内使用缓存的预检请求结果，在这个时间段内，如果再次发起相同的跨域请求，浏览器就不需要重新发送预检请求，直接按照缓存结果来处理后续请求，加快了请求处理速度。 

