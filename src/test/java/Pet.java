public class Pet {
    //Definindo uma classe chamada pet para guardar a estrutura de dados sobre os animais (pets)
    public long id;
    public Category category;
    public String name;
    public String[] photoUrls = new String[]{}; //inicializa para evitar erro de null pointer exception
    public Tag[] tags;
    public String status; 
    

    static class Category {
        public int id;
        public String name;
    }

    public static class Tag {
        public int id;
        public String name;
    }  

}
    
