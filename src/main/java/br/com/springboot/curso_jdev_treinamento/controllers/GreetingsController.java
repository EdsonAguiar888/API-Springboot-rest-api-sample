package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired  // IC, CD OU CDI--- INJECAO DE DEPENDENCIA
	private UsuarioRepository usuarioRepository;
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
	
	/*
	##########################################################################
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello curso jdev treinamento " + name + "!";
    }
    
    
    @RequestMapping(value = "/teste/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String teste (@PathVariable String name) {
        
        
        Usuario usuario = new Usuario();
        usuario.setNome(name);
        
        usuarioRepository.save(usuario);
        
        return "Hello  " + name + "!";       
    }
    ##########################################################################
    */
    
   //-----------------------------------------------------------------------------------------------
    //1?? Metodo Atualizar lista
    
    @GetMapping(value = "listatodos")
    @ResponseBody // Retorna os dados para o corpo da resposta 
    public ResponseEntity<List<Usuario>> listaUsuario(){
		
    	List <Usuario> usuarios = usuarioRepository.findAll(); //Executa a consulta no banco de dados
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); // Retorna a lista em JSON
    	
    	//NAO PRECISA NENHUM PARAMETRO
    	
    }
    
    	
    //------------------------------------------------------------------------------------------------
    //2?? Metodo Salvar
    
    @PostMapping(value = "salvar") // Mapeia a URL    
    @ResponseBody // Descri????o da resposta
    public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario){   //Recebe os dados para salvar
    	
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    	//POST
    	//BODY
    	//Raw 
    	//JSON    
    	
    	//	{
    	//	"id": 6,
    	//	"nome": "edson aguiar ",
    	//	"idade": 22
		//	}
    	
    }
    
    
    
  //------------------------------------------------------------------------------------------------
    //3?? Metodo Atualizar
    
    @PutMapping(value = "atualizar") // Mapeia a URL    
    @ResponseBody // Descri????o da resposta
    public ResponseEntity<?> atualizar (@RequestBody Usuario usuario){   //Recebe os dados para atualizar
    	
    	if (usuario.getId() == null) {
    		
    		return new ResponseEntity<String>("Id nao foi infomado para atualizacao...", HttpStatus.OK);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    	//PUT
    	//BODY
    	//Raw 
    	//JSON    
    	
    	//	{
    	//	"id": 6,
    	//	"nome": "edson aguiar ",
    	//	"idade": 22
		//	}
    	  
    	
    	
    }
    
    
    	
  //------------------------------------------------------------------------------------------------
    //4?? Metodo Deletar
    
    @DeleteMapping(value = "delete") // Mapeia a URL    
    @ResponseBody // Descri????o da resposta
    public ResponseEntity<String> delete (@RequestParam Long iduser ){   //Recebe os dados para deletar
    	
    	usuarioRepository.deleteById(iduser);
    	return new ResponseEntity<String>("Usuario deletado com sucesso..." , HttpStatus.OK);
    	
    	//DELETE
    	//Params
    	//key = iduser
    	//value = numero do id
    	
    }
    
    
  //------------------------------------------------------------------------------------------------
    //5?? Metodo Buscar por Id
    
    @GetMapping(value = "buscaruserid") // Mapeia a URL    
    @ResponseBody // Descri????o da resposta
    public ResponseEntity<Usuario> buscaruserid (@RequestParam (name = "iduser") Long iduser ){   //Recebe os dados para buscar
    	
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	return new ResponseEntity<Usuario>(usuario , HttpStatus.OK);    
    	
    	//GET
    	//Params
    	//key = iduser
    	//value = numero do id
    	    	
    }
    
    
  //------------------------------------------------------------------------------------------------
    //6?? Metodo Buscar por Nome
    
    @GetMapping(value = "buscarPorNome") // Mapeia a URL    
    @ResponseBody // Descri????o da resposta
    public ResponseEntity<List<Usuario>> buscarPorNome (@RequestParam (name = "name") String name ) {   //Recebe os dados para buscar
    	
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());   
    	
    	return new ResponseEntity<List<Usuario>>(usuario , HttpStatus.OK);
    	
    	//GET
    	//BODY
    	//Form-data
    	//key = nome
    	//value = nome salvo no banco
    	
    }
    
    
}
