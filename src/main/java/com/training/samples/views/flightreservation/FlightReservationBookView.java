package com.training.samples.views.flightreservation;

import java.time.LocalDate;

import com.webforj.component.Composite;
import com.webforj.component.button.Button;
import com.webforj.component.button.ButtonTheme;
import com.webforj.component.field.DateField;
import com.webforj.component.html.elements.H1;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexJustifyContent;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.layout.flexlayout.FlexWrap;
import com.webforj.component.list.ChoiceBox;
import com.webforj.component.optiondialog.ConfirmDialog;
import com.webforj.component.optiondialog.OptionDialog;
import com.webforj.component.optioninput.CheckBox;
import com.webforj.router.annotation.FrameTitle;
import com.webforj.router.annotation.Route;

@Route(value = "flightreservation/booking", outlet = FlightReservationView.class)
@FrameTitle("Flight Reservation • Book")
public class FlightReservationBookView extends Composite<FlexLayout> {

        public FlightReservationBookView() {
                FlexLayout root = getBoundComponent()
                                .setDirection(FlexDirection.COLUMN)
                                .setSpacing("var(--dwc-space-l)")
                                .setMargin("var(--dwc-space-l)");
                root.setMaxWidth(720);
                root.setStyle("padding", "var(--dwc-space-l)");

                H1 title = new H1("Book a Flight");

                title.setWidth("100%").setStyle("textAlign", "center");
                root.add(title);

                ChoiceBox fromWhere = new ChoiceBox("Where From").setWidth("220px");
                ChoiceBox toWhere = new ChoiceBox("Where To").setWidth("220px");
                FlexLayout routeRow = new FlexLayout()
                                .setDirection(FlexDirection.ROW)
                                .setWrap(FlexWrap.WRAP)
                                .setJustifyContent(FlexJustifyContent.CENTER)
                                .setSpacing("var(--dwc-space-l)");

                fromWhere.setStyle("textAlign", "center");
                toWhere.setStyle("textAlign", "center");
                routeRow.add(fromWhere, toWhere);

                DateField depart = new DateField(LocalDate.now()).setLabel("Departure Date:").setWidth("220px");
                DateField ret = new DateField(LocalDate.now()).setLabel("Return Date:").setWidth("220px");
                FlexLayout dates = new FlexLayout()
                                .setDirection(FlexDirection.ROW)
                                .setWrap(FlexWrap.WRAP)
                                .setJustifyContent(FlexJustifyContent.CENTER)
                                .setSpacing("var(--dwc-space-l)");
                dates.add(depart, ret);

                CheckBox agree = new CheckBox("I agree to the terms and conditions");
                agree.setWidth("100%").setStyle("textAlign", "center");

                Button bookBtn = new Button("Book Flight").setTheme(ButtonTheme.PRIMARY);
                bookBtn.setWidth("100%").setStyle("textAlign", "center");

                bookBtn.addClickListener(e -> {
                        String o = fromWhere.getValue() == null ? "" : ((String) fromWhere.getValue()).trim();
                        String d = toWhere.getValue() == null ? "" : ((String) toWhere.getValue()).trim();
                        LocalDate date = depart.getValue();

                        if (!agree.isChecked()) {
                                OptionDialog.showMessageDialog(
                                                "Please check “I agree to the terms and conditions” to continue.",
                                                "Action Required",
                                                "OK");
                                return;
                        }
                        if (o.isEmpty() || d.isEmpty() || date == null) {
                                OptionDialog.showMessageDialog(
                                                "Please fill Origin, Destination, and Departure Date.",
                                                "Missing Information",
                                                "OK");
                                return;
                        }

                        ConfirmDialog dialog = new ConfirmDialog(
                                        "You are about to book from " + o + " to " + d + " on " + date
                                                        + ".\nDo you want to proceed?",
                                        "Confirm Your Booking",
                                        ConfirmDialog.OptionType.CUSTOM,
                                        ConfirmDialog.MessageType.INFO);

                        dialog.setFirstButtonText("Confirm & Continue");
                        dialog.setFirstButtonTheme(ButtonTheme.PRIMARY);
                        dialog.setSecondButtonText("Cancel");
                        dialog.setSecondButtonTheme(ButtonTheme.OUTLINED_GRAY);

                        ConfirmDialog.Result result = dialog.show();

                        if (result == ConfirmDialog.Result.FIRST_CUSTOM_BUTTON) {
                                OptionDialog.showMessageDialog(
                                                "Flight successfully booked!",
                                                "Success",
                                                "Done");
                        }
                });

                String[] airports = new String[] {
                                "SFO - San Francisco International",
                                "LAX - Los Angeles International",
                                "DEN - Denver International",
                                "HND - Tokyo Haneda",
                                "ORD - O'Hare International",
                                "LHR - London Heathrow"
                };

                fromWhere.insert(airports);
                toWhere.insert(airports);

                root.add(routeRow, dates, agree, bookBtn);
        }
}
