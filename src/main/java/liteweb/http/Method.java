package liteweb.http;

/**
 * Method enum maps the HTTP/1.1 available request methods 
 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec5.html">rfc2616-sec5</a>
 */
@SuppressWarnings("unused")
public enum Method {
	GET("GET"), //
	HEAD("HEAD"), //
	POST("POST"), //
	PUT("PUT"), //
	DELETE("DELETE"), //
	TRACE("TRACE"), //
	CONNECT("CONNECT"), //
	UNRECOGNIZED(null); //

	private final String method;

	Method(String method) {
		this.method = method;
	}

	public String getMethod() {
		return method;
	}
}
