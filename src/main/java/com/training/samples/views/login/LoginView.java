package com.training.samples.views.login;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.component.login.Login;
import com.webforj.component.optiondialog.MessageDialog;
import com.webforj.component.optiondialog.OptionDialog;
import com.webforj.router.annotation.FrameTitle;
import com.webforj.router.annotation.Route;

@Route               // navigable view
@FrameTitle("Login") // browser/tab title
public class LoginView extends Composite<Div> {

  public LoginView() {
    // webforJ's built-in Login component (includes header, fields, and button)
    Login login = new Login();
    getBoundComponent().add(login);

    // show the login overlay/dialog
    login.open();

    // handle submit: check credentials and give feedback
    login.onSubmit(ev -> {
      String username = ev.getUsername();
      String password = ev.getPassword();

      if ("admin".equals(username) && "admin123".equals(password)) {
        login.close();
        OptionDialog.showMessageDialog(
            "Authenticated",
            "Welcome, admin!",
            "OK",
            MessageDialog.MessageType.INFO
        );
      } else {
        // show error state and keep dialog open
        login.setError(true);
        login.setEnabled(true);
      }
    });
  }
}
