package com.dancer.provalidation.proxy;

public class BusinessProcessorImpl implements BusinessProcessor {

	@Override
	public int processBusiness() {
		System.out.println("the real impl class processing ......");
		return 1;
	}

	@Override
	public String generateText() {
		String text="generating text......loading......";
		System.out.println("on proxy.........");
		return text;
	}

}
