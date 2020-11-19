package com.aplication.ctrl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zkoss.zhtml.Input;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.ext.Selectable;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import com.aplication.model.AuthenticationServiceImpl;
import com.aplication.model.CatUsuario;
import com.aplication.model.UserService;
import com.aplication.model.UserServiceImpl;

public class CatUsuarioCtrl extends SelectorComposer<Component> implements Initiator{

	private static final long serialVersionUID = 1L;
	private boolean editmode = true;

	private CatUsuario selected;
	@Wire
	private Textbox keywordBox;
	@Wire
	private Listbox userListbox;
	@Wire
	private Label codeLabel;
	@Wire
	private Textbox documentInput;
	@Wire
	private Textbox nameInput;
	@Wire
	private Textbox lastnameInput;
	@Wire
	private Textbox correoInput;
	@Wire
	private Textbox directionInput;
	@Wire
	private Textbox phoneInput;
	@Wire
	private Textbox passwordInput;
	@Wire
	private Component detailBox;
	@Wire
	private Button editButton;
	@Wire
	private Button saveButton;
	@Wire
	private Button exitButton;
	@Wire
	private UserService userService = new UserServiceImpl();
    private AuthenticationServiceImpl authService = new AuthenticationServiceImpl();


	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		loadData();
	}
	


	public void loadData() {
		List<CatUsuario> result = userService.findAll();
		userListbox.setModel(new ListModelList<CatUsuario>(result));
	}

	@Listen("onClick = #searchButton")
	public void search() {
		String keyword = keywordBox.getValue();
		List<CatUsuario> result = userService.search(keyword);
		userListbox.setModel(new ListModelList<CatUsuario>(result));
	}
	
	@Listen("onClick = #exitButton")
	public void logout() {
		 Session sess = Sessions.getCurrent();
	     sess.removeAttribute("userCredential");
		 Executions.sendRedirect("/index.zul");
	}

	@Listen("onSelect = #userListbox")
	public void showDetail() {
		detailBox.setVisible(true);
		try {

			Set<CatUsuario> selection = ((Selectable<CatUsuario>) userListbox.getModel()).getSelection();
			if (selection != null && !selection.isEmpty()) {
				selected = selection.iterator().next();
				codeLabel.setValue(Integer.toString(selected.getCodigo()));
				documentInput.setValue(selected.getDocumento());
				nameInput.setValue(selected.getNombre());
				lastnameInput.setValue(selected.getApellido());
				correoInput.setValue(selected.getCorreo());
				directionInput.setValue(selected.getDireccion());
				phoneInput.setValue(selected.getTelefono());
				passwordInput.setValue(selected.getPassword());
			}
		} catch (Exception ex) {
			// void
		}
	}

	@Listen("onClick = #editButton")
	public void editDetail() {

		if (editmode) {
			documentInput.setDisabled(!editmode);
			nameInput.setDisabled(!editmode);
			lastnameInput.setDisabled(!editmode);
			correoInput.setDisabled(!editmode);
			directionInput.setDisabled(!editmode);
			phoneInput.setDisabled(!editmode);
			passwordInput.setDisabled(!editmode);
			editButton.setDisabled(editmode);
			saveButton.setDisabled(!editmode);
		}
	}

	@Listen("onClick = #saveButton")
	public void saveEditDetail() {
		userService.editUser(selected);
		Notification.show("Actialización Exitosa", true);
		editButton.setDisabled(!editmode);
		saveButton.setDisabled(editmode);
		documentInput.setDisabled(editmode);
		nameInput.setDisabled(editmode);
		lastnameInput.setDisabled(editmode);
		correoInput.setDisabled(editmode);
		directionInput.setDisabled(editmode);
		phoneInput.setDisabled(editmode);
		passwordInput.setDisabled(editmode);
	}
	
	@Listen("onClick = #deleteButton")
	public void removeUser() {
		userService.deleteUser(selected.getCodigo());
		Notification.show("Usuario Eliminado", true);
		codeLabel.setValue("");
		documentInput.setValue("");
		nameInput.setValue("");
		lastnameInput.setValue("");
		correoInput.setValue("");
		directionInput.setValue("");
		phoneInput.setValue("");
		passwordInput.setValue("");
	}

	@Override
	public void doInit(Page arg0, Map<String, Object> arg1) throws Exception {
		// TODO Auto-generated method stub
        authService.logout();
		if(authService.getUserCredential()==null){
	            Executions.sendRedirect("/index.zul");
	            return;
	        }
	}

}
