package src.configs.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.service.Tag
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun docket() = Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .tags(
            Tag("User", "Endpoints related to this resource"),
        )
        .select()
        .apis(RequestHandlerSelectors.basePackage("src.entities"))
        .build()
        .apiInfo(apiInfo())

    private fun apiInfo() = ApiInfoBuilder()
        .title("HR User")
        .description("API for Human Resource User")
        .version("v1")
        .contact(
            Contact(
                "Matheus Carvalho",
                "https://www.linkedin.com/in/matheus-carvalho69",
                "matheus9126@gmail.com"
            )
        )
        .build()


}