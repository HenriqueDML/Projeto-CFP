package org.acme.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.inject.Inject;
import org.acme.entity.PalestraEntity;
import org.acme.service.PalestraService;

@Route("")
public class PalestraFormView extends VerticalLayout {

    private final PalestraService palestraService;

    @Inject
    public PalestraFormView(PalestraService palestraService) {
        this.palestraService = palestraService;

        TextField titulo = new TextField("Título");
        TextArea resumo = new TextArea("Resumo");
        TextField nomeAutor = new TextField("Nome do Autor");
        EmailField email = new EmailField("E-mail");

        Button submitButton = new Button("Enviar", event -> {
            PalestraEntity palestra = new PalestraEntity();
            palestra.setTitulo(titulo.getValue());
            palestra.setResumo(resumo.getValue());
            palestra.setNomeAutor(nomeAutor.getValue());
            palestra.setEmail(email.getValue());

            palestraService.createPalestra(palestra);
            Notification.show("Proposta enviada com sucesso!");
        });

        add(titulo, resumo, nomeAutor, email, submitButton);
    }
}
