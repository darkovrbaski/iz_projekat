package izproject.util;

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
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.asm.TypeReference;

public class QueryUtil {
	static Model model = ModelFactory.createDefaultModel();
	
	public static List<String> executeQueryOneColumn(String queryString) {
        List<String> values = new ArrayList<>();
        
        InputStream is = TypeReference.class.getResourceAsStream("/classes.owl");
        RDFDataMgr.read(model,is,Lang.TURTLE);
        printModel();

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

    public static List<List<String>> executeQueryTwoColumn(String queryString) {
        List<List<String>> rows = new ArrayList<List<String>>();
        Model model = null;
        
        InputStream is = TypeReference.class.getResourceAsStream("/classes.owl");
        RDFDataMgr.read(model,is,Lang.TURTLE);

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
    
    static void printModel() {
        System.out.println("################ MODEL #######################");
        StmtIterator iter = model.listStatements();
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            Resource subject = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            RDFNode object = stmt.getObject();

            System.out.print(subject.toString() + " ");
            System.out.print(predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else { // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }
        System.out.println("\n\n");
    }

}
