package com.bgsshop.action;

import com.bgsshop.mvc.Azione;

public class AzioneLogout extends Azione {

	public int get() {
		request.getSession().invalidate();
		return redirect("/bgsshop/");
	}
}
