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

}
