package com.allocab.jGmap.request;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.allocab.jGmap.response.AbstractResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Fetcher<Response extends AbstractResponse, Request extends AbstractRequest<Response>> {
	
	final private static String rootUrl = "https://maps.googleapis.com/maps/api/$SERVICE$/json";
	
	public enum Status {
		NOT_FECTHED,
		FETCHED,
		FETCH_ERROR,
		PARSED,
		PARSE_ERROR
	}
	
	protected Status status;
	protected String url;
	protected String responseString;
	protected HttpMethod method = HttpMethod.GET;	
	protected Object payload;
	
	protected Request request;
	
	public static <Resp extends AbstractResponse, Req extends AbstractRequest<Resp>> Fetcher<Resp,Req> gen(Req request) {
		return new Fetcher<>(request);
	}
	
	public Fetcher(Request request) {
		this.request = request;
	}	
	
	public static String genUrl(String rootUrl, String serviceUrl, String parameters, String cryptoKey) {
		try {
			URL url = new URL(rootUrl.replace("$SERVICE$", serviceUrl)+parameters);
			if(cryptoKey != null) {
				return url.getProtocol() + "://" + url.getHost()+new UrlSigner(cryptoKey).signRequest(url.getPath(), url.getQuery());
			}
			else {
				return url.toString();
			}					
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Response call() {		
		if(this.request == null) return null;
		
		this.url = Fetcher.genUrl(rootUrl, this.request.serviceUrl, this.request.toParameters(), AbstractRequest.getApiCryptoKey());
		if(url == null) return null;
		
		return this.execute(this.request.response);
	}
	
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	@SuppressWarnings("unchecked")
	public Response execute(Class<Response> responseClass) {
		this.fetch();
		//System.out.println("resp class "+responseClass);
		if(Status.PARSED.equals(status)) {			
			try {
				java.lang.reflect.Method method = responseClass.getMethod("deserialize", String.class);
				return (Response) method.invoke(null, this.responseString);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	private void fetch() {
        try {
        	String u = this.url;
        	//System.out.println("u:"+u);
            URL url = new URL(u);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if(HttpMethod.POST.equals(method)) {
            	connection.setDoOutput(true);
            }            
            connection.setRequestMethod(method.toString());

            if(HttpMethod.POST.equals(method)) {            	
                ObjectMapper objectMapper = new ObjectMapper();         	            	
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                objectMapper.writeValue(writer, this);
                writer.close();
            }
            
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            	status = Status.FETCHED;
            }
            else {
            	status = Status.FETCH_ERROR;
            }
            
            //System.out.println("fetch status : "+status);
    
            if(Status.FETCHED.equals(status)) {
            	try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    responseString = "";
                    while ((line = reader.readLine()) != null) {
                    	responseString += line;
                    }
                    reader.close();
                    
                    status = Status.PARSED;
            	}
            	catch(Exception e) {
            		status = Status.PARSE_ERROR;
            	}            	
            }
        } catch (MalformedURLException e) {
        	status = Status.FETCH_ERROR;
            e.printStackTrace();            
        } catch (IOException e) {
        	status = Status.FETCH_ERROR;
        	e.printStackTrace();
        }		
	}
}
