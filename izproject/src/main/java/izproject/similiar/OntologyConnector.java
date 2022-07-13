package izproject.similiar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;

import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseBaseFilter;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import es.ucm.fdi.gaia.jcolibri.exception.InitializingException;
import es.ucm.fdi.gaia.jcolibri.util.FileIO;
import izproject.similiar.model.PCCaseDescription;

public class OntologyConnector implements Connector {

	@Override
	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter filter) {
		LinkedList<CBRCase> cases = new LinkedList<CBRCase>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("data/example.csv")));
			if (br == null)
				throw new Exception("Error opening file");

			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#") || (line.length() == 0))
					continue;
				String[] values = line.split(";");

				CBRCase cbrCase = new CBRCase();

				// CaseDescription caseDescription = new CaseDescription();
				PCCaseDescription pcCaseDescription = new PCCaseDescription();
				// TODO

				cbrCase.setDescription(pcCaseDescription);
				cases.add(cbrCase);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cases;
	}

	@Override
	public void initFromXMLfile(URL file) throws InitializingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeCases(Collection<CBRCase> cases) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCases(Collection<CBRCase> cases) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<CBRCase> retrieveAllCases() {
		// TODO Auto-generated method stub
		return null;
	}

}
