package izproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import izproject.util.QueryUtil;

@Service
public class SuggestionService {
	
    public List<String> getComponents(){
        String queryString = 
        		izproject.util.Prefix.RDF
                + izproject.util.Prefix.OWL
                + izproject.util.Prefix.RDFS
                + izproject.util.Prefix.XML
                + izproject.util.Prefix.CLASSES
                +" "
                +"SELECT ?x "
                +"WHERE "
                +"{ "
                +"?x rdfs:subClassOf "
                +"classes:Hardware ."
                +"} ";

        
        //queryString = String.format(queryString, value);
        System.out.println(queryString);
        //List<List<String>> rows = OwlReaderUtil.executeQueryTwoColumn(getServletContext(), queryString);
        return QueryUtil.executeQueryOneColumn(queryString);

    }
    
    public List<String> getComponentCompatibleWithMotherboard(String motherboardName, String componentType){
        String queryString = 
        		izproject.util.Prefix.RDF
                + izproject.util.Prefix.OWL
                + izproject.util.Prefix.RDFS
                + izproject.util.Prefix.XML
                + izproject.util.Prefix.CLASSES
                + " "
                + "SELECT ?x "
                + "WHERE "
                + "{ "
                + "?x a classes:"
                + componentType
                + " ; "
                + "classes:compatibleWithMotherboard "
                + "classes:"
                + motherboardName
                + " ."
                + "} ";

        
        //queryString = String.format(queryString, value);
        System.out.println(queryString);
        //List<List<String>> rows = OwlReaderUtil.executeQueryTwoColumn(getServletContext(), queryString);
        return QueryUtil.executeQueryOneColumn(queryString);

    }
    
    public List<String> getComponentCompatibleMemoryForMotherboard(String motherboardName){
        String motherboardMemorySlotQuery= 
        		izproject.util.Prefix.RDF
                + izproject.util.Prefix.OWL
                + izproject.util.Prefix.RDFS
                + izproject.util.Prefix.XML
                + izproject.util.Prefix.CLASSES
                + " "
                + "SELECT ?x "
                + "WHERE "
                + "{ classes:"
                + motherboardName 
                + " a classes:Motherboard ;"
                + " classes:motherboardSupportedRamSlot ?x"
                + " ."
                + "} ";
        String motherboardMemorySlot ="";
        if(QueryUtil.executeQueryOneColumnLiteral(motherboardMemorySlotQuery).size()>0)
        	motherboardMemorySlot= QueryUtil.executeQueryOneColumnLiteral(motherboardMemorySlotQuery).get(0);
    	String queryString = 
        		izproject.util.Prefix.RDF
                + izproject.util.Prefix.OWL
                + izproject.util.Prefix.RDFS
                + izproject.util.Prefix.XML
                + izproject.util.Prefix.CLASSES
                + " "
                + "SELECT ?x "
                + "WHERE "
                + "{ "
                + "?x a classes:"
                + motherboardMemorySlot
                + " ."
                + "} ";

        
        //queryString = String.format(queryString, value);
        //System.out.println(motherboardMemorySlot);
        //List<List<String>> rows = OwlReaderUtil.executeQueryTwoColumn(getServletContext(), queryString);
        return QueryUtil.executeQueryOneColumn(queryString);

    }

}
