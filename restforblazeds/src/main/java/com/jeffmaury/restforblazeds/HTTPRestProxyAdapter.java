package com.jeffmaury.restforblazeds;

import flex.messaging.services.http.HTTPProxyAdapter;
import flex.messaging.services.http.proxy.ProxyFilter;
import flex.messaging.services.http.proxy.ResponseFilter;

/**
 * 
 */

/**
 * @author Jeff
 *
 */
public class HTTPRestProxyAdapter extends HTTPProxyAdapter {

	/**
	 * 
	 */
	public HTTPRestProxyAdapter() {
		this(false);
	}

	/**
	 * @param enableManagement
	 */
	public HTTPRestProxyAdapter(boolean enableManagement) {
		super(enableManagement);
		ProxyFilter add = new RestResponseFilter();
		add.setNext(filterChain);
		filterChain = add;
	}

	/**
	 * 
	 */
	protected void updateFilterChain() {
		ProxyFilter previous = null;
		ProxyFilter current = filterChain;
		ProxyFilter replaced;
		
		while (current != null) {
			if (current instanceof ResponseFilter) {
				replaced = new RestResponseFilter();
				if (previous != null) {
					previous.setNext(replaced);
				}
				else {
					filterChain = replaced;
				}
				replaced.setNext(current.getNext());
				break;
			}
			else {
				previous = current;
				current = current.getNext();
			}
		}
	}


}
