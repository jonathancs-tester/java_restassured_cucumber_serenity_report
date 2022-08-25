package ecm.standalone.serenity.steps;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ecm.standalone.cucumber.utils.UtilsSteps;
import ecm.standalone.models.Variable;
import ecm.standalone.utils.ConnectionDB;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;

public class BUC7741Steps {

	@Steps
	UtilsSteps utilsSteps;

	private Response response;
	private String node;

	/***
	 * A function that parses the XML provided as input. 
	 * This XML is retrieved from the database and contains the config variables to be extracted.
	 * 
	 * Each variable extracted contains the following information:
	 * - name (mandatory)
	 * - value (mandatory)
	 * - encrypt (optional) - defines if a variable is Private (1) or not (0)
	 * - isPublic (optional) - defines if a variable is Public (1) or not (0)
	 * 
	 * @param xmlInput A String in XML format to be parsed.
	 * @return List of Variables that were extracted from the XML
	 * @throws SAXException Exception
	 * @throws IOException Exception
	 * @throws ParserConfigurationException Exception
	 */
	public List<Variable> parseConfigXML(String xmlInput)
			throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		StringBuilder xmlStringBuilder = new StringBuilder();
		xmlStringBuilder.append(xmlInput);
		ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
		Document doc = builder.parse(input);
		NodeList nList = doc.getElementsByTagName("variable");

		List<Variable> variablesFound = new ArrayList<Variable>();
		variablesFound.clear();

		for (int loopVariables = 0; loopVariables < nList.getLength(); loopVariables++) {
			Node nNode = nList.item(loopVariables);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				Variable currentVariable = new Variable(element.getElementsByTagName("name").item(0).getTextContent(),
						element.getElementsByTagName("value").item(0).getTextContent());

				if (element.getElementsByTagName("encrypt").getLength() > 0) {
					currentVariable.setEncrypt(element.getElementsByTagName("encrypt").item(0).getTextContent());
				}

				if (element.getElementsByTagName("isPublic").getLength() > 0) {
					currentVariable.setIsPublic(element.getElementsByTagName("isPublic").item(0).getTextContent());
				}

				variablesFound.add(currentVariable);

			}
		}
		return variablesFound;
	}

	/***
	 * Runs a Select statement in the database fetching CW_NODE_CONFIG table for the existing config variables
	 * 
	 * @return Returns a list of Variables that exists in the database
	 * @throws SAXException Exception
	 * @throws IOException Exception
	 * @throws ParserConfigurationException Exception
	 */
	public List<Variable> getDBConfigVariable() throws SAXException, IOException, ParserConfigurationException {

		Connection conn = ConnectionDB.getConnection();

		String sql = new String("select config from CW_NODE_CONFIG where name = ?");
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "ecm");
			ResultSet response = stmt.executeQuery();
			if (!response.next()) {
				List<Variable> empty = new ArrayList<Variable>();
				empty.clear();
				return (empty);
			}
			String responseString = response.getString("CONFIG");
			ConnectionDB.closeConnection(conn);
			return parseConfigXML(responseString);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/***
	 * Validates the response variable inside BUC7741Steps.
	 * - Asserts id is present in the response
	 * - Asserts id set to the node name
	 * - Asserts avmVersion is present
	 * - Asserts generalConfigurations.id is set to environmentType
	 * 
	 *            Does not have any return value since assertions are implemented
	 *            inside the function.
	 */
	public void validateResponse() {
		Assert.assertEquals(this.node, response.getBody().jsonPath().getString("id"));
		Assert.assertNotNull(response.getBody().jsonPath().getString("avmVersion"));
		Assert.assertEquals("environmentType", response.getBody().jsonPath().getString("generalConfigurations[0].id"));
		Assert.assertEquals(1, response.getBody().jsonPath().getList("generalConfigurations").size());
	}

	/***
	 * Executes a GET request to systemConfiguration/node?ids=varID
	 * If varID provided is null, the request is sent to systemConfiguration/node
	 * 
	 * @param nodeID The node name provided. Will be used in the request. 
	 * @param varID The filter to be used in the request.
	 * @return Returns a list of Variables retrieved from the API request
	 * @throws Exception Exception
	 */
	public List<Variable> getAPIConfigVariables(String nodeID, String varID) throws Exception {
		this.node = nodeID;
		// create and submit get
		utilsSteps.authorizationRequest("upadmin", "upadmin");
		if (varID != null) {
			utilsSteps.addQueryParam("ids", varID);
		}
		utilsSteps.sendGETRequestTo("systemConfiguration", node);
		response = UtilsSteps.getResponse();

		if (response.getStatusCode() == 404) {
			List<Variable> empty = new ArrayList<Variable>();
			empty.clear();
			return empty;
		}

		return response.getBody().jsonPath().getList("configVariables", Variable.class);
	}

	/***
	 * Compare function that validates the content of the shortList is entirely
	 * present in the completeLists
	 * 
	 * @param completeList The Complete List of Variables that will be searched
	 * @param shortList The Shorter List of variables that will be used as Search Criteria
	 * 
	 *            Does not have any return value since assertions are implemented
	 *            inside the function.
	 */
	public void compare(List<Variable> completeList, List<Variable> shortList) {
		ListIterator<Variable> iterateShort = shortList.listIterator();

		int countVariablesFound = 0;

		while (iterateShort.hasNext()) {
			Variable shortListVariable = iterateShort.next();
			ListIterator<Variable> iterateComplete = completeList.listIterator();
			while (iterateComplete.hasNext()) {
				Variable completeListVariable = iterateComplete.next();
				if (completeListVariable.getId().equals(shortListVariable.getId())) {
					countVariablesFound++;
					Assert.assertEquals(completeListVariable.getValue(), shortListVariable.getValue());
					break;
				}
			}
		}
		Assert.assertEquals(shortList.size(), countVariablesFound);
	}

	/***
	 * Executes a GET request to systemConfiguration/nodeID?fields=filter
	 * 
	 * @param nodeID The node name provided. Will be used in the request.
	 * @param filter The filter to be used in the request.
	 * @throws Exception Exception
	 */
	public void getUsingFilters(String nodeID, String filter) throws Exception {
		this.node = nodeID;
		// create and submit get
		utilsSteps.authorizationRequest("upadmin", "upadmin");
		utilsSteps.addQueryParam("fields", filter);
		utilsSteps.sendGETRequestTo("systemConfiguration", node);
		response = UtilsSteps.getResponse();
	}

	/***
	 * Validates the response variable inside BUC7741Steps:
	 * 
	 *  - If filter input = "id":
	 *  --- Asserts response has ID 
 	 *  --- Asserts response ID value is equal to BUC7741Steps.node (a variable inside this class previously populated)
	 *  --- Asserts response does not have avmVersion
	 *  
	 *  - If filter input = "avmVersion"
	 *  --- Asserts response does not have ID 
 	 *  --- Asserts response has avmVersion
 	 *  
	 *  - If filter input != "id" AND filter input != "avmVersion"
	 *  --- Asserts response does not have ID 
	 *  --- Asserts response does not have avmVersion
	 *
	 *  @param filter The filter that will be sent with the request
	 *  
	 *            Does not have any return value since assertions are implemented
	 *            inside the function.
	 */
	public void validateResponseHasOnly(String filter) {
		if (filter.equals("id")) {
			Assert.assertEquals(this.node, response.getBody().jsonPath().getString("id"));
			Assert.assertNull(response.getBody().jsonPath().getString("avmVersion"));
		} else if (filter.equals("avmVersion")) {
			Assert.assertNull(response.getBody().jsonPath().getString("id"));
			Assert.assertNotNull(response.getBody().jsonPath().getString("avmVersion"));
		} else {
			Assert.assertNull(response.getBody().jsonPath().getString("id"));
			Assert.assertNull(response.getBody().jsonPath().getString("avmVersion"));
		}
	}

	/***
	 * Finds and returns the first variable within the provided list that has "encrypt" field set to "1"
	 * In case there is no variable found with the search criteria, returns null
	 * 
	 * @param variables List of Variable(s) that will be searched
	 * @return the variable found
	 */
	public Variable findFirstPrivate(List<Variable> variables) {
		ListIterator<Variable> iterateVars = variables.listIterator();

		while (iterateVars.hasNext()) {
			Variable var = iterateVars.next();
			if (var.getEncrypt() != null && var.getEncrypt().equals("1")) {
				return var;
			}
		}
		return null;
		
	}
	
	/***
	 * Validates the response variable inside BUC7741Steps has no "configVariables" present
	 *            Does not have any return value since assertions are implemented
	 *            inside the function.
	 */
	public void validateResponseHasNoVariable() {
		Assert.assertNull(response.getBody().jsonPath().getString("configVariables"));
	}

}
