package com.aplication.ctrl;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import com.aplication.model.CatUsuario;
import com.aplication.model.UserService;
import com.aplication.model.UserServiceImpl;

public class RegisterUserCtrl extends SelectorComposer<Component> {

	@Wire
	private Textbox documentInput2;
	@Wire
	private Textbox nameInput2;
	@Wire
	private Textbox lastnameInput2;
	@Wire
	private Textbox emailInput2;
	@Wire
	private Textbox directionInput2;
	@Wire
	private Textbox phoneInput2;
	@Wire
	private Textbox passwordInput2;
	@Wire
	private Button registerButton;
	@Wire
	private UserService userService = new UserServiceImpl();

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

	@Listen("onClick = #sendregisterButton")
	public void registerUser() {
		System.out.println("Enviando datos para crear usuario ...");
		CatUsuario usr = new CatUsuario(0,
				documentInput2.getValue(),
				nameInput2.getValue(),
				lastnameInput2.getValue(),
				emailInput2.getValue(),
				directionInput2.getValue(),
				phoneInput2.getValue(),
				passwordInput2.getValue());
		System.out.println("Enviando datos para crear usuario ...");
		userService.createUser(usr);
		Notification.show("Usuario Creado. ya puedes iniciar sesión", true);
        Executions.sendRedirect("/cat_users.zul");
	}

}
