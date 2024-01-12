package Test;


import javax.xml.ws.Endpoint;

import Service.Banque_Service;
public class Testing {

	public static void main(String[] args) {

		
		Endpoint.publish("http://localhost:8088/", new Banque_Service());
		System.out.println();
		System.out.print("Connection");
	}

}



//http://localhost:8081/?wsdl