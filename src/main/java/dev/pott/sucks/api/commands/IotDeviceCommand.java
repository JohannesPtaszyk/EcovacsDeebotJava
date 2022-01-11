package dev.pott.sucks.api.commands;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.gson.Gson;

import dev.pott.sucks.api.internal.dto.request.portal.PortalIotCommandRequest.JsonPayloadHeader;
import dev.pott.sucks.api.internal.dto.response.portal.AbstractPortalIotCommandResponse;

public abstract class IotDeviceCommand<RESPONSETYPE> {
    private final String xmlCommandName;
    private final String jsonCommandName;

    protected IotDeviceCommand(String xmlCommandName, String jsonCommandName) {
        this.xmlCommandName = xmlCommandName;
        this.jsonCommandName = jsonCommandName;
    }

    public String getName(boolean forXml) {
        return forXml ? xmlCommandName : jsonCommandName;
    }

    public boolean forceXmlFormat() {
        return false;
    }

    public final String getXmlPayload() throws Exception {
        Document xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element ctl = xmlDoc.createElement("ctl");
        ctl.setAttribute("td", xmlCommandName);
        applyXmlPayload(xmlDoc, ctl);
        xmlDoc.appendChild(ctl);
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        tf.transform(new DOMSource(xmlDoc), new StreamResult(writer));
        return writer.getBuffer().toString().replaceAll("\n|\r", "");
    }

    public final Object getJsonPayload(Gson gson) {
        Map<String, Object> data = new HashMap<String, Object>();
        Object args = getJsonPayloadArgs();
        data.put("header", new JsonPayloadHeader());
        if (args != null) {
            Map<String, Object> body = new HashMap<String, Object>();
            body.put("data", args);
            data.put("body", body);
        }
        return data;
    }

    protected Object getJsonPayloadArgs() {
        return null;
    }

    protected void applyXmlPayload(Document doc, Element ctl) {
    }

    public abstract RESPONSETYPE convertResponse(AbstractPortalIotCommandResponse response, Gson gson) throws Exception;

    protected Node getFirstXPathMatch(String xml, String xpathExpression)
            throws XPathExpressionException, NoSuchElementException {
        InputSource inputXML = new InputSource(new StringReader(xml));
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodes = (NodeList) xPath.evaluate(xpathExpression, inputXML, XPathConstants.NODESET);
        if (nodes.getLength() == 0) {
            throw new NoSuchElementException();
        }
        return nodes.item(0);
    }
}
