package com.aplication.model;

import java.io.Serializable;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;

import com.aplication.conexion.Conexion;

public class AuthenticationServiceImpl extends SelectorComposer<Component>
		implements AuthenticationService, Serializable {
	
	private UserService userService = new UserServiceImpl();

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// Method to execute actions after to render a component
		super.doAfterCompose(comp);

	}

	@Override
	public UserCredential getUserCredential() {
		// TODO Auto-generated method stub
		Session sess = Sessions.getCurrent();
		UserCredential cre = (UserCredential) sess.getAttribute("userCredential");
		if (cre == null) {
			cre = new UserCredential();// new a anonymous user and set to session
			sess.setAttribute("userCredential", cre);
		}
		return cre;
	}

	@Override
	public boolean login( String mail, String pass) {
		// TODO Auto-generated method stub
		boolean value = false;
		CatUsuario usr = userService.getUser(mail, pass);
		if (usr!=null) {
			value= true;
	        Session sess = Sessions.getCurrent();
	        UserCredential cre = new UserCredential(mail,pass);
	        sess.setAttribute("userCredential",cre);
		}
		return value;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		Session sess = Sessions.getCurrent();
        sess.removeAttribute("userCredential");
	}

}
