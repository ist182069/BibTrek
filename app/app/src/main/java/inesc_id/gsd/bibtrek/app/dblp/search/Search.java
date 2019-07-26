package inesc_id.gsd.bibtrek.app.dblp.search;

import java.util.ArrayList;

import inesc_id.gsd.bibtrek.app.dblp.search.condition.WriteConditionFactory;
import inesc_id.gsd.bibtrek.app.dblp.writer.DBLPNoSQLWriter;
import inesc_id.gsd.bibtrek.app.exceptions.DBLPNoSQLWriterException;
import inesc_id.gsd.bibtrek.app.exceptions.HTTPClientException;
import inesc_id.gsd.bibtrek.app.exceptions.SearchException;
import inesc_id.gsd.bibtrek.app.http.HTTPClient;

public abstract class Search {
	
	public Search() {
	}
	
	public String executeQuery(String query) throws SearchException {
		HTTPClient httpClient = new HTTPClient();
		String getRequest;
		try {
			getRequest = httpClient.getRequest(query);
			return getRequest;
		} catch(HTTPClientException httpce) {
			throw new SearchException("executeQuery(): could not execute a get request for"
					+ " the website with url: \"" + query + "\".");
		}
	}
		
	public abstract void search() throws Exception;
	
	public void write(ArrayList<Object[]> tupleArrayList, String condition) throws DBLPNoSQLWriterException {
		DBLPNoSQLWriter dblpNoSQLWriter;
		try {
			dblpNoSQLWriter = WriteConditionFactory.getWriter(tupleArrayList, condition);
			dblpNoSQLWriter.writeToFile();
		} catch (DBLPNoSQLWriterException dblpnosqlwe) {
			throw new DBLPNoSQLWriterException("write(): could not invoke the writer's write function...");
		}
	}
}
