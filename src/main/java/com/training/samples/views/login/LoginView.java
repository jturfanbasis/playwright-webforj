package com.training.samples.views.login;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.component.login.Login;
import com.webforj.component.optiondialog.MessageDialog;
import com.webforj.component.optiondialog.OptionDialog;
import com.webforj.router.annotation.FrameTitle;
import com.webforj.router.annotation.Route;

@Route
@FrameTitle("Login")
public class LoginView extends Composite<Div> {

  public LoginView() {
    Login login = new Login();
    getBoundComponent().add(login);
    login.open();

    login.onSubmit(ev -> {
      String username = ev.getUsername();
      String password = ev.getPassword();

      if ("admin".equals(username) && "admin123".equals(password)) {
        login.close();
        OptionDialog.showMessageDialog(
            "Authenticated",
            "Welcome, admin!",
            "OK",
            MessageDialog.MessageType.INFO);
      } else {
        login.setError(true);
        login.setEnabled(true);
      }
    });
  }
}
