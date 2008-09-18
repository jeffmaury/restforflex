/**
 * 
 */
package com.jeffmaury.restforblazeds;

import flex.messaging.messages.AcknowledgeMessage;
import flex.messaging.services.http.proxy.ProxyContext;
import flex.messaging.services.http.proxy.ProxyFilter;

/**
 * A simple proxy filter that is added at the head of the chain (executed
 * last). Its purpose is simply to transmit the HTTP status code in a
 * header of the response. The headers are serialized through the AMF
 * format, so that the Flash/Flex side can see it.
 */
public class RestResponseFilter extends ProxyFilter {

	/**
	 * The name of the header that stores the HTTP status code. 
	 */
	public static final String STATUS_CODE_HEADER_NAME = "StatusCode"; //$NON-NLS-1$
	
	/**
	 * Default constructor
	 *
	 */
	public RestResponseFilter() {
	}

	/**
	 * Invocation method. Execute the chain and stores the status code in the
	 * headers
	 * 
	 * @param context the proxy context for the request.
	 */
	public void invoke(ProxyContext context) {
		if (next != null) {
			next.invoke(context);
		}
		if (!context.getRecordHeaders()) {
			AcknowledgeMessage ack = new AcknowledgeMessage();
            ack.setBody(context.getResponse());
            ack.setHeader(STATUS_CODE_HEADER_NAME, new Integer(context.getHttpMethod().getStatusCode()));
            context.setResponse(ack);
		}
	}
}
