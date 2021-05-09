//package com;
//
///* 
// * @author Liyange K.L.O.G
// * IT19160344
// * 
// * */
//
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.Base64;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.annotation.security.PermitAll;
//import javax.annotation.security.RolesAllowed;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.Invocation;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.container.ResourceInfo;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.Provider;
//
//import org.glassfish.jersey.client.ClientConfig;
//import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
//import org.glassfish.jersey.jackson.JacksonFeature;
//import org.json.JSONObject;
//
//@Provider
//public class NoticeServiceFilter implements ContainerRequestFilter {
//	@Context
//	private ResourceInfo resourceInfo;
//
//
//	@Override
//	public void filter(ContainerRequestContext requestContext) throws IOException {
//		Method method = resourceInfo.getResourceMethod();
//		
//		//If Allowed for all Return 
//		if (method.isAnnotationPresent(PermitAll.class)) {
//			return;
//		}
//		
//		//Get the authorization in request header
//		List<String> authHeader = requestContext.getHeaders().get("Authorization");
//		
//		//If authHeader is null or its size is not greater than zero will sent not authorized
//		if (authHeader == null || authHeader.size() <= 0) {
//			Response unauthoriazedStatus = Response.status(Response.Status.UNAUTHORIZED)
//					.entity("You are not authorized").build();
//
//			requestContext.abortWith(unauthoriazedStatus);
//		}
//		
//		//Get the userName and password for decoding
//		String authDetailsEnco = authHeader.get(0).split(" ")[1];
//		
//		String decodedString = "";
//		
//		//Decode the user name and password
//		try {
//			byte[] decodedBytes = Base64.getDecoder().decode(authDetailsEnco);
//			decodedString = new String(decodedBytes, "UTF-8");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		//Get the user name and password by splitting the decoded string
//		String userEmail = decodedString.split(":")[0];
//		String password = decodedString.split(":")[1];
//		
//		ClientConfig clientC = new ClientConfig();
//
//		Client client = ClientBuilder.newClient(clientC);
//
//		Response response = client.target("http://localhost:8081/UserManagement/UserService/user/authentication")
//			      .queryParam("userEmail", userEmail)
//			      .queryParam("password", password).request().get();
//		
//		String currentUser = response.readEntity(String.class);
//		
//		System.out.println(currentUser);
//		
//		if(response.getStatus() != 200) {
//	    	Response unauthoriazedStatus = Response.status(Response.Status.UNAUTHORIZED)
//					.entity("You are not authorized").build();
//
//			requestContext.abortWith(unauthoriazedStatus);
//	    } 
//		
//		if (method.isAnnotationPresent(RolesAllowed.class)) {
//			//Get the allowed user roles from Annotation
//			RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
//
//			Set<String> user_role = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
//			
//		    if(!user_role.contains(currentUser)) {
//		    	Response unauthoriazedStatus = Response.status(Response.Status.UNAUTHORIZED)
//						.entity("You are not authorized").build();
//
//				requestContext.abortWith(unauthoriazedStatus);
//		    }  
//		    return;
//		}
//		return;
//	}
//
//	
//}