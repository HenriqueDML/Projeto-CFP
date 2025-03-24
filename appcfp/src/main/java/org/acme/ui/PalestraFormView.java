package org.acme.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.acme.entity.PalestraEntity;
import org.acme.service.PalestraService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Route("")
public class PalestraFormView extends VerticalLayout {

    private final PalestraService palestraService;
    private final Validator validator;

    private final TextField titulo = new TextField("Título");
    private final TextArea resumo = new TextArea("Resumo");
    private final TextField nomeAutor = new TextField("Nome do Autor");
    private final EmailField email = new EmailField("E-mail");

    private final Button submitButton = new Button("Enviar");
    private final Button clearButton = new Button("Limpar");

    private final TextField filtroTitulo = new TextField("Filtrar por Título");
    private final TextField filtroNomeAutor = new TextField("Filtrar por Autor");
    private final TextField filtroEmail = new TextField("Filtrar por E-mail");
    private final Button buscarButton = new Button("Buscar");

    private final Grid<PalestraEntity> grid = new Grid<>(PalestraEntity.class, false);

    @Inject
    public PalestraFormView(PalestraService palestraService, Validator validator) {
        this.palestraService = palestraService;
        this.validator = validator;

        // Layout do formulário
        FormLayout formLayout = new FormLayout();
        resumo.setHeight("150px");
        formLayout.add(titulo, resumo, nomeAutor, email);

        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        clearButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        submitButton.addClickListener(e -> processForm());
        clearButton.addClickListener(e -> clearForm());

        // Layout de filtros
        HorizontalLayout filtros = new HorizontalLayout(filtroTitulo, filtroNomeAutor, filtroEmail, buscarButton);
        buscarButton.addClickListener(e -> atualizarGrid());

        // Tabela
        grid.addColumn(PalestraEntity::getTitulo).setHeader("Título");
        grid.addColumn(PalestraEntity::getResumo).setHeader("Resumo");
        grid.addColumn(PalestraEntity::getNomeAutor).setHeader("Autor");
        grid.addColumn(PalestraEntity::getEmail).setHeader("Email");

        add(formLayout, new HorizontalLayout(submitButton, clearButton), filtros, grid);
        atualizarGrid();
    }

    private void atualizarGrid() {
        String titulo = filtroTitulo.getValue();
        String autor = filtroNomeAutor.getValue();
        String email = filtroEmail.getValue();

        List<PalestraEntity> resultados = palestraService.buscarComFiltros(titulo, autor, email);
        grid.setItems(resultados);
    }

    private void processForm() {
        PalestraEntity palestra = new PalestraEntity();
        palestra.setTitulo(titulo.getValue());
        palestra.setResumo(resumo.getValue());
        palestra.setNomeAutor(nomeAutor.getValue());
        palestra.setEmail(email.getValue());

        Set<ConstraintViolation<PalestraEntity>> violations = validator.validate(palestra);

        clearValidationHighlights();

        if (!violations.isEmpty()) {
            violations.forEach(v -> highlightError(v.getPropertyPath().toString()));
            Notification.show("Preencha os campos corretamente!", 3000, Notification.Position.MIDDLE);
        } else {
            palestraService.createPalestra(palestra);
            Notification.show("Palestra registrada com sucesso!", 3000, Notification.Position.TOP_CENTER);
            clearForm();
            atualizarGrid();
        }
    }

    private void clearForm() {
        titulo.clear();
        resumo.clear();
        nomeAutor.clear();
        email.clear();
        clearValidationHighlights();
    }

    private void clearValidationHighlights() {
        titulo.setInvalid(false);
        resumo.setInvalid(false);
        nomeAutor.setInvalid(false);
        email.setInvalid(false);
    }

    private void highlightError(String fieldName) {
        switch (fieldName) {
            case "titulo" -> titulo.setInvalid(true);
            case "resumo" -> resumo.setInvalid(true);
            case "nomeAutor" -> nomeAutor.setInvalid(true);
            case "email" -> email.setInvalid(true);
        }
    }
}
