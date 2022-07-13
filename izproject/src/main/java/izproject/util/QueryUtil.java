package izproject.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.core.io.ClassPathResource;

public class QueryUtil {
	static Model model = ModelFactory.createDefaultModel();

	public static List<String> executeQueryOneColumn(String queryString) {
		List<String> values = new ArrayList<>();

		InputStream is = TypeReference.class.getResourceAsStream("/classes.owl");
		RDFDataMgr.read(model, is, Lang.TURTLE);

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet results = qexec.execSelect();

			while (results.hasNext()) {
				QuerySolution solution = results.nextSolution();
				
				Resource x = solution.getResource("x");

				values.add(x.getLocalName());
			}
		} finally {
			qexec.close();
		}
		return values;

	}

	public static List<String> executeQueryOneColumnLiteral(String queryString) {
		List<String> values = new ArrayList<>();

		InputStream is = TypeReference.class.getResourceAsStream("/classes.owl");
		RDFDataMgr.read(model, is, Lang.TURTLE);

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet results = qexec.execSelect();

			while (results.hasNext()) {
				QuerySolution solution = results.nextSolution();

				Literal x = solution.getLiteral("x");

				values.add(x.toString());
			}
		} finally {
			qexec.close();
		}
		return values;

	}

	public static List<List<String>> executeQueryTwoColumn(String queryString) {
		List<List<String>> rows = new ArrayList<List<String>>();
		Model model = null;

		InputStream is = TypeReference.class.getResourceAsStream("/classes.owl");
		RDFDataMgr.read(model, is, Lang.TURTLE);

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet results = qexec.execSelect();

			while (results.hasNext()) {
				QuerySolution solution = results.nextSolution();

				Resource x = solution.getResource("x");
				Literal y = solution.getLiteral("y");

				List<String> column1 = Arrays.asList(x.getLocalName(), y.getInt() + "");
				rows.add(column1);
			}
		} finally {
			qexec.close();
		}
		return rows;

	}

	public static OWLReasoner getReasoner() {
		// uvoz ontologije iz fajl sistema
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

		File file = null;
		try {
			file = new ClassPathResource("/classes.owl").getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		OWLOntology o = null;
		try {
			o = manager.loadOntologyFromOntologyDocument(file);
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}

		// koriscenje rezonera
		OWLReasonerFactory rf = new ReasonerFactory();
		OWLReasoner r = rf.createReasoner(o);
		r.precomputeInferences(InferenceType.CLASS_HIERARCHY);
		
		return r;
	}

}
