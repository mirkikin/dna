package com.marco.test;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DnaLambda implements RequestHandler<DnaRequest, DnaResponse> {

	@Override
	public DnaResponse handleRequest(DnaRequest input, Context context) {
		IDnaService service = new DnaService();
		DnaResponse res = new DnaResponse();
		if (!input.isStats()) {
			boolean result = service.hasMutation(input.getDna().toArray(new String[input.getDna().size()]));
			if (result)
				throw new RuntimeException();
		} else {
			res.setStats(service.getStats());
		}

		return res;
	}

}
