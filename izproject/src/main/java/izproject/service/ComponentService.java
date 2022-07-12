package izproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import izproject.util.QueryUtil;

@Service
public class ComponentService {

	public List<String> getComponents(String component){
        String queryString = 
        		izproject.util.Prefix.RDF
                + izproject.util.Prefix.OWL
                + izproject.util.Prefix.RDFS
                + izproject.util.Prefix.XML
                + izproject.util.Prefix.CLASSES
                +" "
                +"SELECT ?x "
                +"WHERE "
                +"{"
                +" ?x rdf:type"
                +" classes:" + component
                +" ."
                +"}";

        
        //queryString = String.format(queryString, value);
        System.out.println(queryString);
        //List<List<String>> rows = OwlReaderUtil.executeQueryTwoColumn(getServletContext(), queryString);
        return QueryUtil.executeQueryOneColumn(queryString);

    }

	public String getComponentProperty(String componentName, String propery) {
		 String queryString = 
	        		izproject.util.Prefix.RDF
	                + izproject.util.Prefix.OWL
	                + izproject.util.Prefix.RDFS
	                + izproject.util.Prefix.XML
	                + izproject.util.Prefix.CLASSES
	                +" "
	                +"SELECT ?x "
	                +"WHERE "
	                +"{"
	                +" classes:" + componentName
	                +" classes:" + propery
	                +" ?x ."
	                +"}";
		 String value = QueryUtil.executeQueryOneColumnLiteral(queryString).get(0);
		 return value.substring(0, value.indexOf("^^"));
	}
}
