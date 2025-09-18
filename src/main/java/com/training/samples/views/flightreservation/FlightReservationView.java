package com.training.samples.views.flightreservation;

import com.webforj.App;
import com.webforj.component.Composite;
import com.webforj.component.button.Button;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.H3;
import com.webforj.component.icons.Icon;
import com.webforj.component.icons.TablerIcon;
import com.webforj.component.layout.applayout.AppDrawerToggle;
import com.webforj.component.layout.applayout.AppLayout;
import com.webforj.component.layout.appnav.AppNav;
import com.webforj.component.layout.appnav.AppNavItem;
import com.webforj.component.layout.toolbar.Toolbar;
import com.webforj.router.annotation.FrameTitle;
import com.webforj.router.annotation.Route;
import com.webforj.router.history.ParametersBag;

@Route
@FrameTitle("Flight Reservation")
public class FlightReservationView extends Composite<AppLayout> {

  public FlightReservationView() {
    AppLayout self = getBoundComponent();

    Toolbar header = new Toolbar()
        .addToStart(new AppDrawerToggle())
        .addToTitle(new H3("Application"));
    self.addToHeader(header);

    Button themeToggle = new Button();

    boolean isDark = "dark".equalsIgnoreCase(App.getTheme()) || "dark-pure".equalsIgnoreCase(App.getTheme());
    themeToggle.setPrefixComponent(TablerIcon.create(isDark ? "sun" : "moon"));

    themeToggle.addClickListener(e -> {
      String t = App.getTheme();
      boolean darkNow = "dark".equalsIgnoreCase(t) || "dark-pure".equalsIgnoreCase(t);

      if (darkNow) {
        App.setTheme("light");
        themeToggle.setPrefixComponent(TablerIcon.create("moon"));
      } else {
        App.setTheme("dark");
        themeToggle.setPrefixComponent(TablerIcon.create("sun"));
      }
    });

    header.addToEnd(themeToggle);

    Div drawer = new Div();
    self.addToDrawer(drawer);

    AppNav nav = new AppNav();
    drawer.add(nav);

    Icon homeIcon = TablerIcon.create("home");
    Icon planeIcon = TablerIcon.create("plane");

    nav.addItem(new AppNavItem("Home",
        FlightReservationHomeView.class,
        ParametersBag.of("page=home"),
        homeIcon));

    nav.addItem(new AppNavItem("Book",
        FlightReservationBookView.class,
        ParametersBag.of("page=book"),
        planeIcon));
  }
}
