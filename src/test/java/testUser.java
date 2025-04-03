import static org.hamcrest.Matchers.containsString;   
import static org.hamcrest.Matchers.hasLength;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class TestUser {
    static String ct ="application/json"; // content-type
    static String uriUser = "https://petstore.swagger.io/v2/user";
    static String token;

    @Test 
    public static String testLogin(){
        //configura 
        String userName = "robert";
        String password = "abcdef";

        String resultadoEsperado = "logged in user session:";

        Response resposta = (Response) given()
            .contentType(ct) // tipo de conteúdo é
            .log().all() //exibir tudo na ida
            
        //executa
        .when() //quando
            .get(uriUser + "/login?username=" + userName + "&password=" + password)//envie o corpo da requisição

        //valida
        .then()
            .log().all() //exibir tudo na volta
            .statusCode(200)
            .body("code", Is.is(200)) //verifica se o código é 200
            .body("type", Is.is("unknown")) //verifica se o tipo é unknown
            .body("message", containsString(resultadoEsperado)) //verifica se a mensagem é igual ao esperado
            .body("message", hasLength(36)) //tamanho do campo message
        .extract()
        ;        

        //extração
        String token = resposta.jsonPath().getString("message").substring(23); //extrai o token da resposta
        System.out.println("Conteúdo do Token: " + token); //exibe o token no console
        return token; //retorna o token
        
    }   


}
