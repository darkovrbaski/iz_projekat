package izproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.springframework.stereotype.Service;

import izproject.util.QueryUtil;

@Service
public class ComponentService {

	public List<String> getComponents(String component){
		OWLReasoner reasoner = QueryUtil.getReasoner();

		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		OWLDataFactory df = man.getOWLDataFactory();
		IRI ontology = IRI.create("https://github.com/darkovrbaski/iz_projekat/master/classes.owl#" + component);

		List<String> rows = new ArrayList<>();
		Set<OWLNamedIndividual> a = reasoner.getInstances(df.getOWLClass(ontology)).getFlattened();
		for (OWLNamedIndividual owlNamedIndividual : a) {
			rows.add(owlNamedIndividual.getIRI().getShortForm());
		}
		return rows;
    }

	public String getComponentDataProperty(String componentName, String propery) {
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
		 
		 String value="";
		 if(QueryUtil.executeQueryOneColumnLiteral(queryString).size()>0) {
			 value = QueryUtil.executeQueryOneColumnLiteral(queryString).get(0);
			 value = value.substring(0, value.indexOf("^^"));
		 }
		 return value;
	}
	
	public String getComponentObjectProperty(String componentName, String propery) {
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
		 
		 String value="";
		 if(QueryUtil.executeQueryOneColumn(queryString).size()>0)
			 value = QueryUtil.executeQueryOneColumn(queryString).get(0);
		 return value;
	}
	
	public String getComponentType(String componentName) {
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
	                +" a ?x" 
	                +"."
	                +"}";
		 
		 String value="";
		 if(QueryUtil.executeQueryOneColumn(queryString).size()>0)
			 value = QueryUtil.executeQueryOneColumn(queryString).get(0);
		 return value;
	}
}
