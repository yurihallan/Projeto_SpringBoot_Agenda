package br.com.api.contatos.controller;

import java.util.List;
import java.util.Optional;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.contatos.entity.Contatos;
import br.com.api.contatos.repository.ContatosRepository;

@RestController
public class ContatosController {
    
    @Autowired
    private ContatosRepository _ContatosRepository;
    //metodo GET - listando todo os contatos 
    @RequestMapping(value = "/contatos", method = RequestMethod.GET)
    public List<Contatos>Get(){
        return _ContatosRepository.findAll();
    }
    // metodo GET - listando um contato especifico
    @RequestMapping(value = "/contatos/{id}", method = RequestMethod.GET)
    public ResponseEntity<Contatos> GetById(@PathVariable(value = "id") long id){
        Optional<Contatos> contato = _ContatosRepository.findById(id);
        if(contato.isPresent()){
            return new ResponseEntity<Contatos>(contato.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }    
    }

    //metodo post - inserindo um novo contato
    @RequestMapping(value = "/contatos", method = RequestMethod.POST)
    public Contatos Post(@RequestBody Contatos contatos){
        return _ContatosRepository.save(contatos);
    }
    //metodo put - alterando um contato especifico
    @RequestMapping(value = "/contatos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Contatos> Put(@PathVariable(value = "id") long id, @RequestBody Contatos NewContato){
        Optional<Contatos> oldContato = _ContatosRepository.findById(id);
        if(oldContato.isPresent()){
            Contatos contato = oldContato.get();
            contato.setNome(NewContato.getNome());
            _ContatosRepository.save(contato);
            return new ResponseEntity<Contatos>(contato, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //metodo delete - deleta um contato especifico
    @RequestMapping(value = "/contatos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id){
        Optional<Contatos> contato = _ContatosRepository.findById(id);
        if(contato.isPresent()){
            _ContatosRepository.delete((contato.get()));
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
