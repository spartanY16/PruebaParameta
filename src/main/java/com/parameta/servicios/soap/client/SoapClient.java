package com.parameta.servicios.soap.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.parameta.servicios.enums.Constantes;
import com.parameta.servicios.soap.manager.CustomerRequest;
import com.parameta.servicios.soap.manager.Response;

@Service
public class SoapClient {

	@Autowired
	private Jaxb2Marshaller marshaller;

	private WebServiceTemplate template;

	public Response guardarEmpleadoSOAP(CustomerRequest peticion) {

		template = new WebServiceTemplate(marshaller);
		Response response = (Response) template.marshalSendAndReceive(Constantes.URL_SERVICIO_SOAP.getDescripcion(),
				peticion);
		return response;

	}

}
