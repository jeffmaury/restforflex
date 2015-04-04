# Introduction #

In order to use these extension, you just need to modify the BlazeDS configuration.

This is because the principle of this extension is to extend the proxy processing of BlazeDS, in order to transmit the HTTP status code in a response header.

First, you need to install the JAR file of this extension in the WEB-INF/lib directory of your BlazeDS web application. You can find this JAR file in the [http:../source/browse/repo/com/jeffmaury/restforblazeds SVN] repository of this project.

Then, you just need to modify the BlazeDS configuration.

Locate the proxy-config.xml file in you BlazeDS application, located under WEB-INF/flex.
You should have a section like the following:

```
    <adapters>
        <adapter-definition id="http-proxy" class="flex.messaging.services.http.HTTPProxyAdapter" default="true"/>
        <adapter-definition id="soap-proxy" class="flex.messaging.services.http.SOAPProxyAdapter"/>
    </adapters>

```

The modification consists simply by replacing the http-proxy adapter class name **_flex.messaging.services.http.HTTPProxyAdapter_** by the one from the extension **_com.jeffmaury.restforblazeds.flex.messaging.services.http.HTTPRestProxyAdapter_**

So, after the modification, the section should be:

```
    <adapters>
        <adapter-definition id="http-proxy" class="com.jeffmaury.restforblazeds.flex.messaging.services.http.HTTPRestProxyAdapter" default="true"/>
        <adapter-definition id="soap-proxy" class="flex.messaging.services.http.SOAPProxyAdapter"/>
    </adapters>
```

And that all folks !!!

So I'm pretty sure you're wondering where is the magic ?

Let me explain a little bit. The proxy service of BlazeDS always returns to the Flex application an HTTP status code of 200. So, on the Flex side, whatever the status code has been returned by your REST web service (with exceptions for some but not all 4XX codes), you will receive a ResultEvent. The magic of this extension is that the message associated by the ResultEvent now stores the original HTTP status code: it is stored in the headers of the message and the header name is called **StatusCode**.

If you want to access it, just refer the StatusCode property for the headers. Please find an example of a result handler:

```
  public function onResult(event:Result):void {
    trace("Original HTTP status code:" + event.message.headers.StatusCode);
  }
```