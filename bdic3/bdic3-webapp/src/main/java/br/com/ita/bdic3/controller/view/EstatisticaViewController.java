package br.com.ita.bdic3.controller.view;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.RuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/estatistica")
public class EstatisticaViewController {

	private static final String VIEW_CONTESTACAO = "view.estatistica";

	@RequestMapping(method = RequestMethod.GET)
	public String validacao(Model model) 
	{
	    System.out.println("CHAMA CHAMA CHAMOU");
		
	    int firstArg = 1000 ;
	    String cliID = "1000" ;

	    //if ( args.length > 0 ) 
	    {
	        //try 
	        {
	            //firstArg = Integer.parseInt ( args [ 0 ] ) ;

	            cliID = Integer.toString ( firstArg ) ;
	        } 
	        //catch ( NumberFormatException e ) 
	        {
	            //System.err.println ( "Parâmetro " + args [ 0 ] + " deve ser um número inteiro." ) ;
	            //System.exit ( 1 ) ;

	        } // end try
	    } // end if

	    try 
	    {
	        Runtime rt = Runtime.getRuntime () ;
	        Process pr = rt.exec ( "/usr/bin/Rscript /home/cloudera/Public/R/suspectTransactions.R " + cliID ) ;
	       
	        BufferedReader input = new BufferedReader ( new InputStreamReader ( pr.getInputStream ())) ;

	        String line = null ;
	 
	        while (( line = input.readLine ()) != null ) 
	            System.out.println ( line ) ;
	      
	        int exitVal = pr.waitFor () ;
	        System.out.println ( "Exited with error code " + exitVal ) ;
	    } 
	    catch ( Exception e ) 
	    {
	        System.out.println ( e.toString ()) ;
	        e.printStackTrace () ;

	    } // end try
	    
	    try
	    {
	    	Thread.sleep ( 1000 ) ;
	    }
	    catch ( InterruptedException ex)
	    {
	    	Thread.currentThread ().interrupt () ;
	    
	    } // end try
			
	    return VIEW_CONTESTACAO;
	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// public String products(@PathVariable("id") String id, Model model) {
	// Produto produto = produtoService.findById(Long.parseLong(id));
	// model.addAttribute("pedidoVO", new PedidoVO());
	// model.addAttribute("produto", produto);
	// return VIEW_COMPRA;
	// }

}
