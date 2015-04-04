# Introduction #

This project hosts several sub projects. These projects are:
  * **[restforblazeds](RestForBlazeDS.md)**: a Java based project providing an extension to BlazeDS
  * **restforflex**:    an ActionScript based project providing an extension to the Flex SDK


# Details #

## restforblazeds ##

When call an HTTP service, Flex may use BlazeDS proxy service to indirectly access the service.

The problem related to REST is that the BlazeDS proxy implementation hides the HTTP status code to the Flex code.

This is not a problem for a SOAP based web service, where the status code is 200 by default, and 500 otherwise; but for REST based web services, it is crucial and part of the response.

If you create a resource, 201 means the creation is successful.

The extension is based on the concept that the HTTP status code will be sent back as a header, which are accessible on the Flex side (place is reserved in the returned message but current implementation is an empty bag). Please note that this extension does not require modification of the Flex SDK at all, user code just needs to handle the header when receiving the response.

For more information on how to use this extension, please refer to [restforblazeds](RestForBlazeDS.md).