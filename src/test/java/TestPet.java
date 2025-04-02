// 0 nome do pacote

// 1 biblioteca

// 2 classe

import java.io.IOException; //Classe resposta do REST-assured
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given; //função given 
// 2 classe
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //ativa a ordenaçaõ de execução

public class TestPet { 
    // 2.1 atributos
    static String ct = "application/json"; // content-type
    static String uriPet = "https://petstore.swagger.io/v2/pet";    
    static int petId = 59066561; //código esperado do pet
    String petName = "Snoopy"; //nome do pet
    String categoryName = "cachorro"; //categoria do pet
    String tagName = "vacinado"; //tag do pet  
    String[] status = {"available", "sold"}; //status do pet 

                

    // 2.2 funções e métodos
    // 2.2.1 funções e métodos comuns / úteis

    // Função de leitura de Json
    public static String lerArquivoJson(String arquivoJson) throws IOException{
            return new String(Files.readAllBytes(Paths.get(arquivoJson)));
        
        }
         
    

    //2.2.2 métodos de teste
    @Test @Order(1)
    public void TestPostPet() throws IOException{

         // configura

        //carregar os dados do arquivo json do pet
     String jsonBody = lerArquivoJson("src/test/resources/json/pet1.json");
        

     //começa o teste via REST - assured

     given()  // dado que 
        .contentType(ct)     // tipo de conteúdo é
        .log().all()         //exibir tudo na ida
        .body(jsonBody)      //envie o corpo da requisição

    .when()                  //quando
        .post(uriPet)              //chamamos o endpoint fazendo um post
        
    .then()                  //então 
        .log().all()         //mostre tudo na volta
        .statusCode(200)     //verifique se o status code é 200
        .body("name", is(petName))  //verifica se o nome do pet é Snoopy
        .body("id", is(petId)) //verifique o código do pet
        .body("category.name", is(categoryName)) //se é cachorro
        .body("tags[0].name", is(tagName)) //se está vacinado
        ; //fim do given

    }

    @Test @Order(2)
    public void testGetPet(){
        //configura
       //entradas e saídas definidas no nível da classe

    given()
            .contentType(ct)
            .log().all()
            //quando é get ou delete não tem body
            //executa
    .when()
            .get(uriPet + "/" + petId)   //montar o endpoint da URI/<petId> 
            //valida
    .then()
        .log().all()
        .statusCode(200)
        .body("name", is(petName))  //verifica se o nome do pet é Snoopy
        .body("id", is(petId)) //verifique o código do pet
        .body("category.name", is(categoryName)) //se é cachorro
        .body("tags[0].name", is(tagName)) //se está vacinado
       
         
 
        //verificar a linha 91
        
       ; //fim do given 
    }

    @Test @Order(3) 
    public void testPutPet() throws IOException{
        //configura
        String jsonBody = lerArquivoJson("src/test/resources/json/pet2.json");

    given()
            .contentType(ct) //tipo de conteúdo é
            .log().all() //exibir tudo na ida
            .body(jsonBody)

        //executa

    .when()
            .put(uriPet) //montar o endpoint da URI/<petId>
        //valida
    .then()
            .log().all() //mostre tudo na volta
            .statusCode(200) //verifique se o status code é 200
            .body("name", is(petName))  //verifica se o nome do pet é Snoopy
            .body("id", is(petId)) //verifique o código do pet
            .body("category.name", is(categoryName)) //se é cachorro
            .body("tags[0].name", is(tagName)) //se está vacinado
            .body("status", is(status[1])) //status do pet na loja
            ;
            
    }

    


    @Test @Order(4)    
    public void  testeDeletePet(){

        //configura ->> dados de entrada e saída no começo da classe
    given()
        .contentType(ct)
        .log().all()
        // executa ->> 
    .when()
         .delete(uriPet + "/" + petId)
        //valida
    .then()
         .log().all()
         .statusCode(200) //se comunicou e processou
         .body("code", is(200)) //se apagou
         .body("type", is("unknown"))
         .body("message", is(String.valueOf(petId)))

         ;

    }   
    
}
