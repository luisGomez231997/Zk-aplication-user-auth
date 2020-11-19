package com.aplication.ctrl;

import java.util.List;

//import librarys
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

import com.aplication.model.AuthenticationService;
import com.aplication.model.AuthenticationServiceImpl;
import com.aplication.model.CatUsuario;
import com.aplication.model.UserCredential;
import com.aplication.model.UserService;
import com.aplication.model.UserServiceImpl;

public class IndexCtrl extends SelectorComposer<Component> {

	// Services
	private UserService userService = new UserServiceImpl();
    private AuthenticationServiceImpl authService = new AuthenticationServiceImpl();

	@Wire
	private Button botonLogin;
	@Wire
	private Button botonGoRegistrar;
	@Wire
	private Textbox emailInput;
	@Wire
	private Textbox passInput;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// Method to execute actions after to render a component
		super.doAfterCompose(comp);

	}

	@Listen("onClick = #botonLogin")
	public void search() {
		System.out.println("Correo: " + emailInput.getValue() + " pass: " + passInput.getValue());
		Executions.sendRedirect("/cat_users.zul");
		if (authService.login(emailInput.getValue(), passInput.getValue())) {
	        UserCredential cre= authService.getUserCredential();
	        System.out.println("credential " + cre);
			Executions.sendRedirect("/cat_users.zul");
			// sess.getAttribute("userCredential");
		}
	}

	@Listen("onClick = #botonGoRegistrar")
	public void goPageRegister() {
		Executions.sendRedirect("/register_page.zul");
	}

}
