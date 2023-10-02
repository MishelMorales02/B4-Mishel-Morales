
package ejemploproyecto4to;


public class usuario {
    String nomusuario, contrausuario;
     
    public usuario(String nomusuario,String contrausuario){
        this.nomusuario=nomusuario;
        this.contrausuario= contrausuario;
        
    }
    
    public String getnombre(){
        return this.nomusuario;
    }
    public String getcontra(){
        return this.contrausuario;
    }
    
    
}
