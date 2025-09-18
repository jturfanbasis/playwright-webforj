package com.training.samples.views.flightreservation;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.H1;
import com.webforj.router.annotation.FrameTitle;
import com.webforj.router.annotation.Route;

@Route(value = "flightreservation", outlet = FlightReservationView.class)
@FrameTitle("Flight Reservation • Home")
public class FlightReservationHomeView extends Composite<Div> {
  public FlightReservationHomeView() {
    Div root = getBoundComponent();
    root.setStyle("padding", "var(--dwc-space-l)");
    root.add(new H1("Welcome to WebforJ Flight Reservation"),
             new Div("Welcome! Use the menu to book a flight."));
  }
}
