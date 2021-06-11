
package com.mysite.core.soapclient.gen;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "TemperatureConversionPortService", targetNamespace = "http://www.one-inside.com/blog/soapserver/gen", wsdlLocation = "file:/Users/serpico/_dev/workspace/archtype-samples/cloud/mysite/core/src/main/resources/temperature-conversion.wsdl")
public class TemperatureConversionPortService
    extends Service
{

    private final static URL TEMPERATURECONVERSIONPORTSERVICE_WSDL_LOCATION;
    private final static WebServiceException TEMPERATURECONVERSIONPORTSERVICE_EXCEPTION;
    private final static QName TEMPERATURECONVERSIONPORTSERVICE_QNAME = new QName("http://www.one-inside.com/blog/soapserver/gen", "TemperatureConversionPortService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/Users/serpico/_dev/workspace/archtype-samples/cloud/mysite/core/src/main/resources/temperature-conversion.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TEMPERATURECONVERSIONPORTSERVICE_WSDL_LOCATION = url;
        TEMPERATURECONVERSIONPORTSERVICE_EXCEPTION = e;
    }

    public TemperatureConversionPortService() {
        super(__getWsdlLocation(), TEMPERATURECONVERSIONPORTSERVICE_QNAME);
    }

    public TemperatureConversionPortService(WebServiceFeature... features) {
        super(__getWsdlLocation(), TEMPERATURECONVERSIONPORTSERVICE_QNAME, features);
    }

    public TemperatureConversionPortService(URL wsdlLocation) {
        super(wsdlLocation, TEMPERATURECONVERSIONPORTSERVICE_QNAME);
    }

    public TemperatureConversionPortService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TEMPERATURECONVERSIONPORTSERVICE_QNAME, features);
    }

    public TemperatureConversionPortService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TemperatureConversionPortService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns TemperatureConversionPort
     */
    @WebEndpoint(name = "TemperatureConversionPortSoap11")
    public TemperatureConversionPort getTemperatureConversionPortSoap11() {
        return super.getPort(new QName("http://www.one-inside.com/blog/soapserver/gen", "TemperatureConversionPortSoap11"), TemperatureConversionPort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TemperatureConversionPort
     */
    @WebEndpoint(name = "TemperatureConversionPortSoap11")
    public TemperatureConversionPort getTemperatureConversionPortSoap11(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.one-inside.com/blog/soapserver/gen", "TemperatureConversionPortSoap11"), TemperatureConversionPort.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TEMPERATURECONVERSIONPORTSERVICE_EXCEPTION!= null) {
            throw TEMPERATURECONVERSIONPORTSERVICE_EXCEPTION;
        }
        return TEMPERATURECONVERSIONPORTSERVICE_WSDL_LOCATION;
    }

}
