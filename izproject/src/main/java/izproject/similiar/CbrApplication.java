package izproject.similiar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import es.ucm.fdi.gaia.jcolibri.casebase.LinealCaseBase;
import es.ucm.fdi.gaia.jcolibri.cbraplications.StandardCBRApplication;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCaseBase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRQuery;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.RetrievalResult;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.selection.SelectCases;
import izproject.similiar.model.GPUCaseDescription;
import izproject.similiar.model.MotherboardCaseDescription;
import izproject.similiar.model.ProcessorCaseDescription;
import izproject.similiar.model.RAMCaseDescription;
import izproject.similiar.model.StorageCaseDescription;

public class CbrApplication implements StandardCBRApplication {
	
	Connector _connector;  /** Connector object */
	CBRCaseBase _caseBase;  /** CaseBase object */

	NNConfig simConfig;  /** KNN configuration */
	
	Collection<RetrievalResult> eval = new ArrayList<RetrievalResult>();
	
	public void configure() throws ExecutionException {
		_connector =  new OntologyConnector();
		
		_caseBase = new LinealCaseBase();  // Create a Lineal case base for in-memory organization
		
		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
		
		simConfig.addMapping(new Attribute("manufacturer", ProcessorCaseDescription.class), new Equal());
		simConfig.addMapping(new Attribute("numOfCores", ProcessorCaseDescription.class), new Interval(4));
		simConfig.addMapping(new Attribute("clockSpeed", ProcessorCaseDescription.class), new Interval(0.8));
		
		TableSimilarity motherboardManufacturerSimilarity = new TableSimilarity(Arrays.asList(new String[] {"AMD","Intel"}));
		motherboardManufacturerSimilarity.setSimilarity("AMD", "Intel", 0.5);
		simConfig.addMapping(new Attribute("manufacturer", MotherboardCaseDescription.class), motherboardManufacturerSimilarity);
		
		simConfig.addMapping(new Attribute("memory", GPUCaseDescription.class), new Interval(4));
		simConfig.addMapping(new Attribute("hashRate", GPUCaseDescription.class), new Interval(1000));
		
		simConfig.addMapping(new Attribute("type", RAMCaseDescription.class), new Equal());
		simConfig.addMapping(new Attribute("frequency", RAMCaseDescription.class), new Interval(500));
		simConfig.addMapping(new Attribute("capacity", RAMCaseDescription.class), new Interval(5));
		
		simConfig.addMapping(new Attribute("type", StorageCaseDescription.class), new Equal());
		simConfig.addMapping(new Attribute("capacity", StorageCaseDescription.class), new Interval(400));

		// Equal - returns 1 if both individuals are equal, otherwise returns 0
		// Interval - returns the similarity of two number inside an interval: sim(x,y) = 1-(|x-y|/interval)
		// Threshold - returns 1 if the difference between two numbers is less than a threshold, 0 in the other case
		// EqualsStringIgnoreCase - returns 1 if both String are the same despite case letters, 0 in the other case
		// MaxString - returns a similarity value depending of the biggest substring that belong to both strings
		// EnumDistance - returns the similarity of two enum values as the their distance: sim(x,y) = |ord(x) - ord(y)|
		// EnumCyclicDistance - computes the similarity between two enum values as their cyclic distance
		// Table - uses a table to obtain the similarity between two values. Allowed values are Strings or Enums. The table is read from a text file.
		// TableSimilarity(List<String> values).setSimilarity(value1,value2,similarity) 
	}

	public void cycle(CBRQuery query) throws ExecutionException {
		eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		eval = SelectCases.selectTopKRR(eval, 5);
		System.out.println("Retrieved cases:");
		for (RetrievalResult nse : eval)
			System.out.println(nse.get_case().getDescription());
	}

	public void postCycle() throws ExecutionException {
		
	}

	public CBRCaseBase preCycle() throws ExecutionException {
		_caseBase.init(_connector);
		Collection<CBRCase> cases = _caseBase.getCases();
		for (CBRCase c: cases)
			System.out.println(c.getDescription());
		return _caseBase;
	}

	public Collection<RetrievalResult> getEval() {
		return eval;
	}

	public void setEval(Collection<RetrievalResult> eval) {
		this.eval = eval;
	}

}