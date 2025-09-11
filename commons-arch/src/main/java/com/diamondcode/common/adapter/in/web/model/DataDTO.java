package com.diamondcode.common.adapter.in.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Object> extras = new ArrayList<>();

	public List<Object> getExtras() {
		return Collections.unmodifiableList(extras);
	}

	public void setExtras(List<Object> extras) {
		this.extras = Collections.unmodifiableList(extras);
	}

}
